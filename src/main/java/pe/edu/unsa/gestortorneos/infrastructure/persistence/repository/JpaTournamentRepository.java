package pe.edu.unsa.gestortorneos.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unsa.gestortorneos.infrastructure.persistence.entity.TournamentEntity;

public interface JpaTournamentRepository extends JpaRepository<TournamentEntity, Long> {}
