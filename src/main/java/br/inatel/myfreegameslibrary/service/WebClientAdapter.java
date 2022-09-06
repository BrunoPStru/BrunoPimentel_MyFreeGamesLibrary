package br.inatel.myfreegameslibrary.service;

import br.inatel.myfreegameslibrary.exception.FreeToPlayConnectionException;
import br.inatel.myfreegameslibrary.model.entity.Game;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class WebClientAdapter {
    @Value("${my.free.games.host}")
    private String freeToPlayHost;

    @Value("${my.free.games.port}")
    private String freeToPlayPort;

    private String freeToPlayBaseUrl;

    private WebClient webClient;

    @PostConstruct
    public void buildFreeToPlayBaseUrl() {
        this.freeToPlayBaseUrl = String.format("https://%s:%s", this.freeToPlayHost, this.freeToPlayPort);
        this.webClient = WebClient.builder()
                .baseUrl(this.freeToPlayBaseUrl)
                .build();
    }

    @Cacheable(cacheNames = "gameCache")
    public List<Game> getAllGame() {

        try {
            Game[] gameArr = this.webClient.get().uri("/game").retrieve().bodyToMono(Game[].class).block();
            return Arrays.asList(gameArr);
        } catch (WebClientException webClientException) {
            throw new FreeToPlayConnectionException(this.freeToPlayBaseUrl);
        }
    }
}
