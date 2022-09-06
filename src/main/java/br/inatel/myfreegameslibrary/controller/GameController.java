package br.inatel.myfreegameslibrary.controller;

import br.inatel.myfreegameslibrary.model.dto.GameDTO;
import br.inatel.myfreegameslibrary.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameDTO>> getGames(@RequestParam(required = false) Optional<String> title) {
        List<GameDTO> gameList;
        if (title.isPresent()) {
            gameList = gameService.getGameByTitle(title.get());
        } else {
            gameList = gameService.getAllGames();
        }

        return ResponseEntity.ok(gameList);
    }

//    @PostMapping
//    public ResponseEntity<GameDTO> saveGame(@Valid @RequestBody GameDTO gameDTO) {
//        return ResponseEntity.created(null).body(gameService.saveGame(gameDTO));
//    }

//    @DeleteMapping("/{title}")
//    public ResponseEntity<Object> deleteGame(@PathVariable  String title){
//
//        return ResponseEntity
//    }

}
