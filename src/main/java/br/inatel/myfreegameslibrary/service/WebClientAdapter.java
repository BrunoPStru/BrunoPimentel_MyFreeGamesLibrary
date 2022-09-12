package br.inatel.myfreegameslibrary.service;

import br.inatel.myfreegameslibrary.exception.FreeToPlayConnectionException;
import br.inatel.myfreegameslibrary.model.dto.GameDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import javax.annotation.PostConstruct;

/**
 * This class is an adapter and comunicate with external API.
 */
@Service
@Slf4j
public class WebClientAdapter {

    @Value("${free.to.play.url}")
    private String freeToPlayUrl;

    private String freeToPlayBaseUrl;

    private WebClient webClient;

    /**
     * This method creates the connection to the external API.
     */
    @PostConstruct
    public void buildFreeToPlayBaseUrl() {
        this.freeToPlayBaseUrl = String.format(freeToPlayUrl + "/api/games");
        this.webClient = WebClient.builder()
                .baseUrl(this.freeToPlayBaseUrl)
                .build();
    }

    /**
     * This method gets the game by the id in the external API.
     *
     * @return the external API game.
     */
    public GameDTO getGameById(Long id) {
        try {
            GameDTO gameDTO = WebClient.create(freeToPlayUrl)
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/game")
                            .queryParam("id", "{id}")
                            .build(id))
                    .retrieve()
                    .bodyToMono(GameDTO.class)
                    .block();

            return gameDTO;
        } catch (WebClientException webClientException) {
            throw new FreeToPlayConnectionException(this.freeToPlayBaseUrl);
        }
    }

    /**
     * This method clears the cache.
     */
    @CacheEvict(cacheNames = "genreCache")
    public void ClearGenreCache() {
        log.info("Cache cleared");
    }
}
