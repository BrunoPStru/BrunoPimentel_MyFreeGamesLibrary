package br.inatel.myfreegameslibrary.service;

import br.inatel.myfreegameslibrary.exception.GameNotFoundException;
import br.inatel.myfreegameslibrary.model.dto.GameDTO;
import br.inatel.myfreegameslibrary.model.entity.Game;
import br.inatel.myfreegameslibrary.model.entity.Genre;
import br.inatel.myfreegameslibrary.repository.GameRepository;
import br.inatel.myfreegameslibrary.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class GameServiceTest {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private GameDTO gameDTO;

    private Genre genre;

    private Game game;

    private List<Game> gamesList;

    private List<GameDTO> gamesDTOList;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private WebClientAdapter webClientAdapter;

    @InjectMocks
    private GameService gameService = new GameService(webClientAdapter, gameRepository, genreRepository);

    @BeforeEach
    public void init() {
        gameDTO = GameDTO.builder()
                .id(1L)
                .title("PUBG: BATTLEGROUNDS")
                .thumbnail("https://www.freetogame.com/g/516/thumbnail.jpg")
                .short_description("Get into the action in one of the longest running battle royale games PUBG Battlegrounds.")
                .game_url("https://www.freetogame.com/open/pubg")
                .genre("Shooter")
                .platform("PC (Windows)")
                .publisher("KRAFTON, Inc.")
                .developer("KRAFTON, Inc.")
                .release_date(LocalDate.parse("2022-01-12", DTF))
                .freetogame_profile_url("https://www.freetogame.com/pubg")
                .build();

        genre = Genre.builder()
                .id(1L)
                .genre("Shooter")
                .build();

        game = Game.builder()
                .id(1L)
                .title("PUBG: BATTLEGROUNDS")
                .thumbnail("https://www.freetogame.com/g/516/thumbnail.jpg")
                .short_description("Get into the action in one of the longest running battle royale games PUBG Battlegrounds.")
                .game_url("https://www.freetogame.com/open/pubg")
                .genre(genre)
                .platform("PC (Windows)")
                .publisher("KRAFTON, Inc.")
                .developer("KRAFTON, Inc.")
                .release_date(LocalDate.parse("2022-01-12", DTF))
                .freetogame_profile_url("https://www.freetogame.com/pubg")
                .build();
    }

    @Test
    public void givenGetAllGames_whenGetAllGames_shouldReturnsGameDTOList() {
        when(gameRepository.findAll()).thenReturn(Arrays.asList(game));

        List<GameDTO> gameDTOList = gameService.getAllGames();

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
    public void givenGetGameByTitle_whenGetGameByValidTitle_shouldReturnsGameDTOList() {
        when(gameRepository.findGamesByTitle(any(String.class))).thenReturn(Arrays.asList(game));

        List<GameDTO> gameDTOList = gameService.getGameByTitle("PUBG: BATTLEGROUNDS");

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
    public void givenGetGameByTitle_whenGetGameByInvalidTitle_shouldReturnsEmptyGameDTOList() {
        when(gameRepository.findGamesByTitle(any(String.class))).thenReturn(Arrays.asList());

        List<GameDTO> gameDTOList = gameService.getGameByTitle("Invalid");

        assertNotNull(gameDTOList);
        assertEquals(0, gameDTOList.size());
    }

    @Test
    public void givenGetGameByTitle_whenGetGameByNoTitle_shouldReturnsEmptyGameDTOList() {
        when(gameRepository.findGamesByTitle(null)).thenReturn(Arrays.asList());

        List<GameDTO> gameDTOList = gameService.getGameByTitle(null);

        assertNotNull(gameDTOList);
        assertEquals(0, gameDTOList.size());
    }

    @Test
    public void givenSaveGame_whenSaveValidGameAndGenre_shouldReturnsGameDTO() {
        when(gameRepository.save(any(Game.class))).thenReturn(game);

        GameDTO gameDTOAux = GameDTO.builder()
                .id(516L)
                .title("PUBG: BATTLEGROUNDS")
                .thumbnail("https://www.freetogame.com/g/516/thumbnail.jpg")
                .short_description("Get into the action in one of the longest running battle royale games PUBG Battlegrounds.")
                .game_url("https://www.freetogame.com/open/pubg")
                .genre("Shooter")
                .platform("PC (Windows)")
                .publisher("KRAFTON, Inc.")
                .developer("KRAFTON, Inc.")
                .release_date(LocalDate.parse("2022-01-12", DTF))
                .freetogame_profile_url("https://www.freetogame.com/pubg")
                .build();

        when(webClientAdapter.getGameById(516L)).thenReturn(gameDTOAux);

        GameDTO saveGame = gameService.saveGame(516L);

        assertNotNull(saveGame);
        assertEquals(1, saveGame.getId());
        assertEquals("PUBG: BATTLEGROUNDS", saveGame.getTitle());
        assertEquals("https://www.freetogame.com/g/516/thumbnail.jpg", saveGame.getThumbnail());
        assertEquals("Get into the action in one of the longest running battle royale games PUBG Battlegrounds.", saveGame.getShort_description());
        assertEquals("https://www.freetogame.com/open/pubg", saveGame.getGame_url());
        assertEquals("Shooter", saveGame.getGenre());
        assertEquals("PC (Windows)", saveGame.getPlatform());
        assertEquals("KRAFTON, Inc.", saveGame.getPublisher());
        assertEquals("KRAFTON, Inc.", saveGame.getDeveloper());
        assertEquals(LocalDate.parse("2022-01-12", DTF), saveGame.getRelease_date());
        assertEquals("https://www.freetogame.com/pubg", saveGame.getFreetogame_profile_url());
    }

    @Test
    public void givenSaveGame_whenSaveNoGame_shouldReturnsGameNotFoundException() {
        assertThrows(GameNotFoundException.class, () -> gameService.saveGame(null));
    }

    @Test
    public void givenDeleteGameByTitle_whenDeleteGameByValidTitle_shouldDeleteGame() {
        when(gameRepository.findGamesByTitle(any(String.class))).thenReturn(Arrays.asList(game));

        gameService.deleteGame("PUBG: BATTLEGROUNDS");
        Game emptyGame = gameRepository.findGameByTitle("PUBG: BATTLEGROUNDS");
        assertEquals(true, emptyGame == null);
    }

    @Test
    public void givenDeleteGameByTitle_whenDeleteGameByInvalidTitle_shouldReturnsGameNotFoundException() {
        assertThrows(GameNotFoundException.class, () -> gameService.deleteGame("Invalid"));
    }

    @Test
    public void givenDeleteGameByTitle_whenDeleteGameByNoTitle_shouldReturnsGameNotFoundException() {
        assertThrows(GameNotFoundException.class, () -> gameService.deleteGame(null));
    }

}
