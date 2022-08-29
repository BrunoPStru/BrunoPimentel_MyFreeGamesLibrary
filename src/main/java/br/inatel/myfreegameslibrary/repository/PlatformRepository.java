package br.inatel.myfreegameslibrary.repository;

import br.inatel.myfreegameslibrary.model.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, String> {
}
