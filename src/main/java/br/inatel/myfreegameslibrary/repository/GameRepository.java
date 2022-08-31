package br.inatel.myfreegameslibrary.repository;

import br.inatel.myfreegameslibrary.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {
    List<Game> findByTitle(String title);

}
