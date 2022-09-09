package br.inatel.myfreegameslibrary.controller;

import br.inatel.myfreegameslibrary.model.dto.GameDTO;
import br.inatel.myfreegameslibrary.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    public ResponseEntity<GameDTO> saveGame(@Valid @RequestBody GameDTO gameDTO) {
        return ResponseEntity.created(null).body(gameService.saveGame(gameDTO));
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteGame(@RequestParam(required = false) String title) {
        gameService.deleteGame(title);
        return ResponseEntity.status(HttpStatus.OK).body("Game " + title + " deleted successfully!");
    }

}
