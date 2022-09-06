package br.inatel.myfreegameslibrary.service;

import br.inatel.myfreegameslibrary.model.dto.GenreDTO;
import br.inatel.myfreegameslibrary.model.entity.Genre;
import br.inatel.myfreegameslibrary.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenreServiceTest {

    private GenreDTO genreDTO;

    private Genre genre;

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService genreService = new GenreService();

    @BeforeEach
    public void init() {
        genreDTO = GenreDTO.builder()
                .id(1L)
                .genre("Shooter")
                .build();

        genre = Genre.builder()
                .id(1L)
                .genre("Shooter")
                .build();
    }

    @Test
    public void givenGetAllGenres_whenGetAllGenres_shouldReturnGenreDTOList() {
        when(genreRepository.findAll()).thenReturn(Arrays.asList(genre));

        List<GenreDTO> genreDTOList = genreService.getAllGenres();

        assertNotNull(genreDTOList);
        assertEquals(1, genreDTOList.size());
        assertEquals(1, genreDTOList.get(0).getId());
        assertEquals("Shooter", genreDTOList.get(0).getGenre());
    }

}
