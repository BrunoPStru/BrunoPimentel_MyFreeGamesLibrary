package br.inatel.myfreegameslibrary.service;

import br.inatel.myfreegameslibrary.mapper.GenreMapper;
import br.inatel.myfreegameslibrary.model.dto.GenreDTO;
import br.inatel.myfreegameslibrary.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class is responsible for all actions related to Genres.
 */
@Service
@Transactional
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    /**
     * This method lists all the genres that are in the library.
     *
     * @return all genres.
     */
    @Cacheable(value = "genreCache")
    public List<GenreDTO> getAllGenres() {
        return GenreMapper.toGenreDTOList(genreRepository.findAll());
    }
}
