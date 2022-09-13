package br.inatel.myfreegameslibrary.controller;

import br.inatel.myfreegameslibrary.model.dto.GameDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GameControllerTest {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void givenGetAllGames_whenCallGetMethod_shouldReturnsGameDTOList() {
        List<GameDTO> gameDTOList = webTestClient.get()
                .uri("/game")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(GameDTO.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(gameDTOList);
        assertEquals(1, gameDTOList.get(0).getId());
        assertEquals("PUBG: BATTLEGROUNDS", gameDTOList.get(0).getTitle());
        assertEquals("https://www.freetogame.com/g/516/thumbnail.jpg", gameDTOList.get(0).getThumbnail());
        assertEquals("Get into the action in one of the longest running battle royale games PUBG Battlegrounds.", gameDTOList.get(0).getShort_description());
        assertEquals("https://www.freetogame.com/open/pubg", gameDTOList.get(0).getGame_url());
        assertEquals("Shooter", gameDTOList.get(0).getGenre());
        assertEquals("PC (Windows)", gameDTOList.get(0).getPlatform());
        assertEquals("KRAFTON, Inc.", gameDTOList.get(0).getPublisher());
        assertEquals("KRAFTON, Inc.", gameDTOList.get(0).getDeveloper());
        assertEquals(LocalDate.parse("2022-01-12", DTF), gameDTOList.get(0).getRelease_date());
        assertEquals("https://www.freetogame.com/pubg", gameDTOList.get(0).getFreetogame_profile_url());
    }

    @Test
    public void givenGetGameByValidTitle_whenCallGetMethod_shouldReturnsGameDTOList() {
        List<GameDTO> gameDTOList = webTestClient.get()
                .uri("/game?title=PUBG: BATTLEGROUNDS")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(GameDTO.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(gameDTOList);
        assertEquals(1, gameDTOList.size());
        assertEquals(1, gameDTOList.get(0).getId());
        assertEquals("PUBG: BATTLEGROUNDS", gameDTOList.get(0).getTitle());
        assertEquals("https://www.freetogame.com/g/516/thumbnail.jpg", gameDTOList.get(0).getThumbnail());
        assertEquals("Get into the action in one of the longest running battle royale games PUBG Battlegrounds.", gameDTOList.get(0).getShort_description());
        assertEquals("https://www.freetogame.com/open/pubg", gameDTOList.get(0).getGame_url());
        assertEquals("Shooter", gameDTOList.get(0).getGenre());
        assertEquals("PC (Windows)", gameDTOList.get(0).getPlatform());
        assertEquals("KRAFTON, Inc.", gameDTOList.get(0).getPublisher());
        assertEquals("KRAFTON, Inc.", gameDTOList.get(0).getDeveloper());
        assertEquals(LocalDate.parse("2022-01-12", DTF), gameDTOList.get(0).getRelease_date());
        assertEquals("https://www.freetogame.com/pubg", gameDTOList.get(0).getFreetogame_profile_url());
    }

    @Test
    public void givenGetGameByInvalidTitle_whenCallGetMethod_shouldReturnsEmptyList() {
        List<GameDTO> gameDTOList = webTestClient.get()
                .uri("/game?title=Invalid")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(GameDTO.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(gameDTOList);
        assertEquals(0, gameDTOList.size());
    }

    @Test
    public void givenGetGameByNoTitle_whenCallGetMethod_shouldReturnsEmptyList() {
        List<GameDTO> gameDTOList = webTestClient.get()
                .uri("/game?title=")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(GameDTO.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(gameDTOList);
        assertEquals(0, gameDTOList.size());
    }

    @Test
    public void givenSaveValidGame_whenCallSaveGame_shouldReturnsGameDTO() {
        webTestClient.post()
                .uri("/game?id=340")
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(String.class);
    }

    @Test
    public void givenSaveInvalidGame_whenCallSaveGame_shouldReturnsFreeToPlayConnectionException() {
        webTestClient.post()
                .uri("/game?id=999")
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

    @Test
    public void givenSaveNoGame_whenCallSaveGame_shouldReturnsGameNotFoundException() {
        webTestClient.post()
                .uri("/game?id=")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    public void givenDeleteGameByValidTitle_whenCallDeleteMethod_shouldDeleteGame() {
        webTestClient.delete()
                .uri("/game?title=PUBG: BATTLEGROUNDS")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("Game PUBG: BATTLEGROUNDS deleted successfully!");
    }

    @Test
    public void givenDeleteGameByInvalidTitle_whenCallDeleteMethod_shouldReturnsGameNotFoundException() {
        webTestClient.delete()
                .uri("/game?title=Invalid")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    public void givenDeleteGameByNoTitle_whenCallDeleteMethod_shouldReturnsGameNotFoundException() {
        webTestClient.delete()
                .uri("/game?title=")
                .exchange()
                .expectStatus()
                .isNotFound();
    }
}
