package pe.edu.unsa.gestortorneos.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unsa.gestortorneos.infrastructure.persistence.entity.TeamEntity;

public interface JpaTeamRepository extends JpaRepository<TeamEntity, Long> {
    boolean existsByNormalizedName(String normalizedName);
}
