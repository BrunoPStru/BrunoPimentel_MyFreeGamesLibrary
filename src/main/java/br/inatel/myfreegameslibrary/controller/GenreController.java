package br.inatel.myfreegameslibrary.controller;

import br.inatel.myfreegameslibrary.model.dto.GenreDTO;
import br.inatel.myfreegameslibrary.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getGenres() {
        List<GenreDTO> genreList;
        genreList = genreService.getAllGenres();

        return ResponseEntity.ok(genreList);
    }

}
