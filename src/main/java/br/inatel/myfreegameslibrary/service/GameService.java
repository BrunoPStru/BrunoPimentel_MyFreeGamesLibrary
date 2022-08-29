package br.inatel.myfreegameslibrary.service;

import br.inatel.myfreegameslibrary.model.dto.GameDTO;
import br.inatel.myfreegameslibrary.model.entity.Game;
import br.inatel.myfreegameslibrary.repository.GameRepository;
import br.inatel.myfreegameslibrary.repository.GenreRepository;
import br.inatel.myfreegameslibrary.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private PlatformRepository platformRepository;

//    public List<GameDTO> getAllGames() {
//        return
//    }
//
//    public List<GameDTO> getGameByTitle(String title) {
//        return
//    }
//
//    public GameDTO saveGame(GameDTO gameDTO) {
//
//    }
//
//    public GameDTO DeleteGame(GameDTO gameDTO) {
//
//    }
//
//    private Boolean isGameValid(Game game) {
//        return
//    }

}
