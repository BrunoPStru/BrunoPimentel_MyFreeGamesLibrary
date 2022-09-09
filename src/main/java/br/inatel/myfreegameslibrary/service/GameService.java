package br.inatel.myfreegameslibrary.service;

import br.inatel.myfreegameslibrary.exception.GameNotFoundException;
import br.inatel.myfreegameslibrary.mapper.GameMapper;
import br.inatel.myfreegameslibrary.model.dto.GameDTO;
import br.inatel.myfreegameslibrary.model.entity.Game;
import br.inatel.myfreegameslibrary.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class GameService {

    @Autowired
    private WebClientAdapter webClientAdapter;

    @Autowired
    private GameRepository gameRepository;


    @Cacheable(value = "gamesCache")
    public List<GameDTO> getAllGames() {
        return GameMapper.toGameDTOList(gameRepository.findAll());
    }

    @Cacheable(value = "gamesCache")
    public List<GameDTO> getGameByTitle(String title) {
        List<Game> game = gameRepository.findGamesByTitle(title);

        return GameMapper.toGameDTOList(game);
    }

    @CacheEvict(value = "gamesCache", allEntries = true)
    public GameDTO saveGame(GameDTO gameDTO) {

        Game game = GameMapper.toGame(gameDTO);

        if (game != null && isGameValid(game)) {
            return GameMapper.toGameDTO(gameRepository.save(game));
        }

        throw new GameNotFoundException();

    }

    @CacheEvict(value = "gamesCache", allEntries = true)
    public void deleteGame(String title) {
        List<Game> game = gameRepository.findGamesByTitle(title);

        if (game.isEmpty()) {
            throw new GameNotFoundException();
        }
        gameRepository.delete(game.get(0));
    }

    private Boolean isGameValid(Game game) {
        Boolean validate = false;

//        validate = webClientAdapter.getFlux()
//                .stream()
//                .anyMatch(freeGame -> freeGame.getTitle().equals(game.getTitle()));
//
        validate = webClientAdapter.getAllGames()
                .stream()
                .anyMatch(freeGame -> freeGame.getTitle().equals(game.getTitle()));

        return validate;
    }

}
