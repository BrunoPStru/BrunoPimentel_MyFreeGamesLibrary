package br.inatel.myfreegameslibrary.service;

import br.inatel.myfreegameslibrary.exception.GameAlredyExistsException;
import br.inatel.myfreegameslibrary.exception.GameNotFoundException;
import br.inatel.myfreegameslibrary.mapper.GameMapper;
import br.inatel.myfreegameslibrary.model.dto.GameDTO;
import br.inatel.myfreegameslibrary.model.entity.Game;
import br.inatel.myfreegameslibrary.model.entity.Genre;
import br.inatel.myfreegameslibrary.repository.GameRepository;
import br.inatel.myfreegameslibrary.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class is responsible for all actions related to Games.
 */
@Service
@Transactional
@AllArgsConstructor
public class GameService {

    @Autowired
    private WebClientAdapter webClientAdapter;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GenreRepository genreRepository;


    /**
     * This method lists all the games that are in the library.
     *
     * @return all games.
     */
    public List<GameDTO> getAllGames() {
        return GameMapper.toGameDTOList(gameRepository.findAll());
    }

    /**
     * This method shows the searched game.
     *
     * @param title The Title of the Game.
     * @return the searched game.
     */
    public List<GameDTO> getGameByTitle(String title) {
        List<Game> game = gameRepository.findGamesByTitle(title);

        return GameMapper.toGameDTOList(game);
    }

    /**
     * This method save a new game and genre.
     *
     * @param id The game id in external API.
     * @return the game registered.
     */
    @CacheEvict(value = "gamesCache", allEntries = true)
    public GameDTO saveGame(Long id) {

        if (id != null) {
            GameDTO gameDTO = webClientAdapter.getGameById(id);

            if (gameDTO == null) {
                throw new GameNotFoundException();
            }

            Game gameAux = gameRepository.findGameByTitle(gameDTO.getTitle());

            if (gameAux == null) {
                Genre genre = checkGenre(gameDTO.getGenre());

                Game game = GameMapper.toGame(gameDTO);

                game.setGenre(genre);

                return GameMapper.toGameDTO(gameRepository.save(game));
            }

            throw new GameAlredyExistsException();
        }

        throw new GameNotFoundException();
    }

    /**
     * This method deletes a game.
     *
     * @param title The Title of the Game.
     */
    @CacheEvict(value = "gamesCache", allEntries = true)
    public void deleteGame(String title) {
        List<Game> game = gameRepository.findGamesByTitle(title);

        if (game.isEmpty()) {
            throw new GameNotFoundException();
        }
        gameRepository.delete(game.get(0));
    }

    /**
     * This method saves a new genre.
     *
     * @param genreName The genre of the game.
     * @return the genre.
     */
    private Genre checkGenre(String genreName) {
        Genre genre = genreRepository.findGenreByGenre(genreName);

        if (genre == null) {
            Genre genreAux = new Genre(genreName);
            genre = genreRepository.save(genreAux);
            webClientAdapter.ClearGenreCache();
        }

        return genre;
    }

}
