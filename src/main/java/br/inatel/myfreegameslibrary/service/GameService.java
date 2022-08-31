package br.inatel.myfreegameslibrary.service;

import br.inatel.myfreegameslibrary.exception.GameNotFoundException;
import br.inatel.myfreegameslibrary.mapper.GameMapper;
import br.inatel.myfreegameslibrary.model.dto.GameDTO;
import br.inatel.myfreegameslibrary.model.entity.Game;
import br.inatel.myfreegameslibrary.repository.GameRepository;
import br.inatel.myfreegameslibrary.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    WebClientAdapter webClientAdapter;

    public List<GameDTO> getAllGames() {
        return GameMapper.toGameDTOList(gameRepository.findAll());
    }

    public List<GameDTO> getGameByTitle(String title) {
        return GameMapper.toGameDTOList(gameRepository.findByTitle(title));
    }

    public GameDTO saveGame(GameDTO gameDTO) {

        Game game = GameMapper.toGame(gameDTO);

        if (true){
            return GameMapper.toGameDTO(gameRepository.save(game));
        }

        throw new GameNotFoundException(game);

    }

//    public GameDTO DeleteGame(GameDTO gameDTO) {
//
//    }
//
//    private Boolean isGameValid(Game game) {
//        return webClientAdapter.getAllGame().stream();
//    }

}
