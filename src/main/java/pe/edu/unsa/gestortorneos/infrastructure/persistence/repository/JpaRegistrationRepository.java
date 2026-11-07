package pe.edu.unsa.gestortorneos.infrastructure.persistence.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unsa.gestortorneos.infrastructure.persistence.entity.RegistrationEntity;

public interface JpaRegistrationRepository extends JpaRepository<RegistrationEntity, Long> {
    boolean existsByTournamentIdAndTeamId(Long tournamentId, Long teamId);
    List<RegistrationEntity> findByTournamentId(Long tournamentId);
}
