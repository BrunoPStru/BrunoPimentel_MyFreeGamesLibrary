package br.inatel.myfreegameslibrary.mapper;

import br.inatel.myfreegameslibrary.model.dto.GameDTO;
import br.inatel.myfreegameslibrary.model.entity.Game;
import br.inatel.myfreegameslibrary.model.entity.Genre;
import br.inatel.myfreegameslibrary.model.entity.Platform;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GameMapper {
    public static List<GameDTO> toGameDTOList(List<Game> gameList) {
        return gameList.stream().map(game -> toGameDTO(game)).collect(Collectors.toList());
    }

    private static GameDTO toGameDTO(Game game) {

        GameDTO gameDTO = GameDTO.builder()
                .id(game.getId())
                .genres(new HashMap<>())
                .platforms(new HashMap<>())
                .build();

        gameDTO.setGenres(game.getGenres().stream()
                .collect(Collectors.toMap(Genre::getId, Genre::getGenre)));

        gameDTO.setPlatforms(game.getPlatforms().stream()
                .collect(Collectors.toMap(Platform::getId, Platform::getPlatform)));

        return gameDTO;
    }
}
