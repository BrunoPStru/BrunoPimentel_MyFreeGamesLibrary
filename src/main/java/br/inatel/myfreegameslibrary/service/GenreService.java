package br.inatel.myfreegameslibrary.service;

import br.inatel.myfreegameslibrary.mapper.GenreMapper;
import br.inatel.myfreegameslibrary.model.dto.GenreDTO;
import br.inatel.myfreegameslibrary.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<GenreDTO> getAllGenres() {
        return GenreMapper.toGenreDTOList(genreRepository.findAll());
    }
}
