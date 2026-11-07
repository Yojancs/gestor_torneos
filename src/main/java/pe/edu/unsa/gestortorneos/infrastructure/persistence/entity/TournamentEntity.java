package pe.edu.unsa.gestortorneos.infrastructure.persistence.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import pe.edu.unsa.gestortorneos.domain.model.TournamentStatus;

@Entity
@Table(name = "tournaments")
public class TournamentEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    @Column(nullable = false) public String name;
    @Column(nullable = false) public LocalDate startDate;
    @Column(nullable = false) public LocalDate endDate;
    @Enumerated(EnumType.STRING) @Column(nullable = false) public TournamentStatus status;
    protected TournamentEntity() {}
    public TournamentEntity(Long id, String name, LocalDate startDate, LocalDate endDate, TournamentStatus status) {
        this.id = id; this.name = name; this.startDate = startDate; this.endDate = endDate; this.status = status;
    }
}
