package br.inatel.myfreegameslibrary.controller;

import br.inatel.myfreegameslibrary.model.dto.GenreDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GenreControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void givenGetAllGenres_whenCallGetMethod_shouldReturnGenreDTOList() {
        List<GenreDTO> genreDTOList = webTestClient.get()
                .uri("/genre")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(GenreDTO.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(genreDTOList);
        assertEquals(1, genreDTOList.get(0).getId());
        assertEquals("Shooter", genreDTOList.get(0).getGenre());
    }
}
