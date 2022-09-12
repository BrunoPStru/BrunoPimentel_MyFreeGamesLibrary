package br.inatel.myfreegameslibrary.service;

import br.inatel.myfreegameslibrary.model.dto.GameDTO;
import br.inatel.myfreegameslibrary.model.entity.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class WebClientAdapter {
    @Value("${my.free.games.host}")
    private String myFreeGamesHost;

    @Value("${my.free.games.port}")
    private String myFreeGamesPort;

    @Value("${my.free.games.url}")
    private String myFreeGamesUrl;

    @Value("${free.to.play.url}")
    private String freeToPlayUrl;

    private String freeToPlayBaseUrl;

    private WebClient webClient;

    @PostConstruct
    @Cacheable(cacheNames = "gameCache")
    public void buildFreeToPlayBaseUrl() {
        this.freeToPlayBaseUrl = String.format(myFreeGamesUrl + "/api/games");
        this.webClient = WebClient.builder()
                .baseUrl(this.freeToPlayBaseUrl)
                .build();
    }

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
        } catch (WebClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Cacheable(cacheNames = "gameCache")
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();

        try {
            Flux<Game> fluxGame = WebClient.create(myFreeGamesUrl)
                    .get()
                    .uri("/game")
                    .retrieve()
                    .bodyToFlux(Game.class);

            fluxGame.subscribe(a -> games.add(a));
            fluxGame.blockLast();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return games;
    }

    @CacheEvict(cacheNames = "gamesCache")
    public void ClearGameCache() {
        log.info("Cache cleared");
    }
}
