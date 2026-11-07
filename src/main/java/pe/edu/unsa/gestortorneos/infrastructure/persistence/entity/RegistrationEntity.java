package pe.edu.unsa.gestortorneos.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "registrations", uniqueConstraints = @UniqueConstraint(name = "uk_registration_tournament_team", columnNames = {"tournament_id", "team_id"}))
public class RegistrationEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    @Column(name = "tournament_id", nullable = false) public Long tournamentId;
    @Column(name = "team_id", nullable = false) public Long teamId;
    @Column(nullable = false) public LocalDateTime registeredAt;
    protected RegistrationEntity() {}
    public RegistrationEntity(Long id, Long tournamentId, Long teamId, LocalDateTime registeredAt) {
        this.id = id; this.tournamentId = tournamentId; this.teamId = teamId; this.registeredAt = registeredAt;
    }
}
