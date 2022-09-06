package br.inatel.myfreegameslibrary.service;

import br.inatel.myfreegameslibrary.exception.GameNotFoundException;
import br.inatel.myfreegameslibrary.exception.GameUnprocessableEntityException;
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
        List<Game> game = gameRepository.findByTitle(title);

        if (title.isBlank()) {
            throw new GameUnprocessableEntityException();
        } else if (game.isEmpty()) {
            throw new GameNotFoundException(title);
        }

        return GameMapper.toGameDTOList(game);
    }

    @CacheEvict(value = "gamesCache", allEntries = true)
    public GameDTO saveGame(GameDTO gameDTO) {

        Game game = GameMapper.toGame(gameDTO);

        if (isGameValid(game)) {
            return GameMapper.toGameDTO(gameRepository.save(game));
        }

        throw new GameNotFoundException(game.getTitle());

    }

//    @CacheEvict(value = "gamesCache", allEntries = true)
//    public GameDTO DeleteGame(GameDTO gameDTO) {
//
//    }

    private Boolean isGameValid(Game gameTitle) {
        return webClientAdapter.getAllGame().stream()
                .anyMatch(freeGame -> freeGame.getTitle().equals(gameTitle.getTitle()));
    }

}
