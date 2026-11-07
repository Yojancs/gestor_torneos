package pe.edu.unsa.gestortorneos.domain.model;

import java.time.LocalDateTime;
import pe.edu.unsa.gestortorneos.domain.exception.DomainException;

public class Registration {
    private final Long id;
    private final Long tournamentId;
    private final Long teamId;
    private final LocalDateTime registeredAt;

    public Registration(Long tournamentId, Long teamId, LocalDateTime registeredAt) {
        this(null, tournamentId, teamId, registeredAt);
    }

    public Registration(Long id, Long tournamentId, Long teamId, LocalDateTime registeredAt) {
        if (tournamentId == null || teamId == null) throw new DomainException("El torneo y el equipo son obligatorios");
        if (registeredAt == null) throw new DomainException("La fecha de inscripción es obligatoria");
        this.id = id;
        this.tournamentId = tournamentId;
        this.teamId = teamId;
        this.registeredAt = registeredAt;
    }

    public Long getId() { return id; }
    public Long getTournamentId() { return tournamentId; }
    public Long getTeamId() { return teamId; }
    public LocalDateTime getRegisteredAt() { return registeredAt; }
}
