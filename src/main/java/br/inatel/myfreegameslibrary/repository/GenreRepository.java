package br.inatel.myfreegameslibrary.repository;

import br.inatel.myfreegameslibrary.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String> {

    Genre findGenreByGenre(String genre);
}
