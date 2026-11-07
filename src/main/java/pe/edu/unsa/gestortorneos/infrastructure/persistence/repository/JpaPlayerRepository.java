package pe.edu.unsa.gestortorneos.infrastructure.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unsa.gestortorneos.infrastructure.persistence.entity.PlayerEntity;

public interface JpaPlayerRepository extends JpaRepository<PlayerEntity, Long> {
    List<PlayerEntity> findByTeamId(Long teamId);
}
