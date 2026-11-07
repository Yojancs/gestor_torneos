package pe.edu.unsa.gestortorneos.application.service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.unsa.gestortorneos.domain.exception.BusinessConflictException;
import pe.edu.unsa.gestortorneos.domain.exception.ResourceNotFoundException;
import pe.edu.unsa.gestortorneos.domain.model.Registration;
import pe.edu.unsa.gestortorneos.domain.model.Tournament;
import pe.edu.unsa.gestortorneos.domain.repository.RegistrationRepository;
import pe.edu.unsa.gestortorneos.domain.repository.TeamRepository;
import pe.edu.unsa.gestortorneos.domain.repository.TournamentRepository;

@Service
public class RegistrationService {
    private final RegistrationRepository registrations;
    private final TournamentRepository tournaments;
    private final TeamRepository teams;
    private final Clock clock;
    public RegistrationService(RegistrationRepository registrations, TournamentRepository tournaments, TeamRepository teams, Clock clock) {
        this.registrations = registrations; this.tournaments = tournaments; this.teams = teams; this.clock = clock;
    }
    @Transactional
    public Registration register(Long tournamentId, Long teamId) {
        Tournament tournament = tournaments.findById(tournamentId)
            .orElseThrow(() -> new ResourceNotFoundException("Torneo no encontrado: " + tournamentId));
        teams.findById(teamId).orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado: " + teamId));
        tournament.ensureRegistrationOpen();
        if (registrations.existsByTournamentIdAndTeamId(tournamentId, teamId))
            throw new BusinessConflictException("El equipo ya está inscrito en el torneo");
        return registrations.save(new Registration(tournamentId, teamId, LocalDateTime.now(clock)));
    }
    @Transactional(readOnly = true)
    public List<Registration> findByTournamentId(Long tournamentId) {
        tournaments.findById(tournamentId).orElseThrow(() -> new ResourceNotFoundException("Torneo no encontrado: " + tournamentId));
        return registrations.findByTournamentId(tournamentId);
    }
}
