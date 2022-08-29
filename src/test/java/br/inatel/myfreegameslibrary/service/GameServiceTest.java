package br.inatel.myfreegameslibrary.service;

import br.inatel.myfreegameslibrary.model.dto.GameDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class GameServiceTest {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private GameService service;

    @Test
    void givenNoParameter_WhenFindAllMethod_ThenMustReturnListOfStockAndQuotes() {

        List<GameDTO> listGameDTO = service.getAllGames();

        assertTrue(!listGameDTO.isEmpty());
    }

}
