package br.inatel.myfreegameslibrary.service;

import br.inatel.myfreegameslibrary.exception.FreeToPlayConnectionException;
import br.inatel.myfreegameslibrary.model.entity.Game;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.Arrays;
import java.util.List;

@Service
public class WebClientAdapter {

    private String freeToPlayBaseUrl;

    private WebClient webClient;

    @Cacheable(cacheNames = "game")
    public List<Game> getAllGame() {

        try {
            Game[] gameArr = this.webClient.get().uri("game").retrieve().bodyToMono(Game[].class).block();
            return Arrays.asList(gameArr);
        } catch (WebClientException webClientException) {
            throw new FreeToPlayConnectionException(this.freeToPlayBaseUrl);
        }
    }
}
