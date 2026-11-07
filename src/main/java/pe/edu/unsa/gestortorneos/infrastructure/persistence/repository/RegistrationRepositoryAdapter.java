package pe.edu.unsa.gestortorneos.infrastructure.persistence.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import pe.edu.unsa.gestortorneos.domain.model.Registration;
import pe.edu.unsa.gestortorneos.domain.repository.RegistrationRepository;
import pe.edu.unsa.gestortorneos.infrastructure.persistence.mapper.RegistrationMapper;

@Repository
public class RegistrationRepositoryAdapter implements RegistrationRepository {
    private final JpaRegistrationRepository jpa;
    public RegistrationRepositoryAdapter(JpaRegistrationRepository jpa) { this.jpa = jpa; }
    public Registration save(Registration r) { return RegistrationMapper.toDomain(jpa.save(RegistrationMapper.toEntity(r))); }
    public boolean existsByTournamentIdAndTeamId(Long tournamentId, Long teamId) { return jpa.existsByTournamentIdAndTeamId(tournamentId, teamId); }
    public List<Registration> findByTournamentId(Long tournamentId) { return jpa.findByTournamentId(tournamentId).stream().map(RegistrationMapper::toDomain).toList(); }
}
