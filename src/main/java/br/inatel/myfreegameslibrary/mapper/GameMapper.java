package br.inatel.myfreegameslibrary.mapper;

import br.inatel.myfreegameslibrary.model.dto.GameDTO;
import br.inatel.myfreegameslibrary.model.entity.Game;
import br.inatel.myfreegameslibrary.model.entity.Genre;

import java.util.List;
import java.util.stream.Collectors;

public class GameMapper {

    public static List<GameDTO> toGameDTOList(List<Game> gameList) {
        return gameList.stream().map(game -> toGameDTO(game)).collect(Collectors.toList());
    }

    public static GameDTO toGameDTO(Game game) {

        GameDTO gameDTO = GameDTO.builder()
                .id(game.getId())
                .title(game.getTitle())
                .thumbnail(game.getThumbnail())
                .short_description(game.getShort_description())
                .game_url(game.getGame_url())
                .genre(game.getGenre())
                .platform(game.getPlatform())
                .publisher(game.getPublisher())
                .developer(game.getDeveloper())
                .release_date(game.getRelease_date())
                .freetogame_profile_url(game.getFreetogame_profile_url())
                .build();

        return gameDTO;
    }

    public static Game toGame(GameDTO gameDTO) {

        Game game = Game.builder()
                .id(gameDTO.getId())
                .title(gameDTO.getTitle())
                .thumbnail(gameDTO.getThumbnail())
                .short_description(gameDTO.getShort_description())
                .game_url(gameDTO.getGame_url())
                .genre(new Genre())
                .platform(gameDTO.getPlatform())
                .publisher(gameDTO.getPublisher())
                .developer(gameDTO.getDeveloper())
                .release_date(gameDTO.getRelease_date())
                .freetogame_profile_url(gameDTO.getFreetogame_profile_url())
                .build();

        return game;
    }
}
