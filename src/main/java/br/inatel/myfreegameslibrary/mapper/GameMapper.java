package br.inatel.myfreegameslibrary.mapper;

import br.inatel.myfreegameslibrary.model.dto.GameDTO;
import br.inatel.myfreegameslibrary.model.entity.Game;

import java.util.List;
import java.util.stream.Collectors;

public class GameMapper {
    public static List<GameDTO> toGameDTOList(List<Game> gameList) {
        return gameList.stream().map(game -> toGameDTO(game)).collect(Collectors.toList());
    }

    public static GameDTO toGameDTO(Game game) {

        GameDTO gameDTO = GameDTO.builder()
                .id(game.getId())
                .genre_id(game.getGenre_id())
                .build();

        return gameDTO;
    }

    public static Game toGame(GameDTO gameDTO) {

        Game game = Game.builder()
                .id(gameDTO.getId())
                .genre_id(gameDTO.getGenre_id())
                .build();

        return game;
    }
}
