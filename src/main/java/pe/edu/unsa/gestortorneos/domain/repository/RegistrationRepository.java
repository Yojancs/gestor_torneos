package pe.edu.unsa.gestortorneos.domain.repository;

import java.util.List;
import pe.edu.unsa.gestortorneos.domain.model.Registration;

public interface RegistrationRepository {
    Registration save(Registration registration);
    boolean existsByTournamentIdAndTeamId(Long tournamentId, Long teamId);
    List<Registration> findByTournamentId(Long tournamentId);
}
