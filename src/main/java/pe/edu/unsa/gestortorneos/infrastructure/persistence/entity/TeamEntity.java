package pe.edu.unsa.gestortorneos.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "teams", uniqueConstraints = @UniqueConstraint(name = "uk_teams_normalized_name", columnNames = "normalized_name"))
public class TeamEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    @Column(nullable = false) public String name;
    @Column(nullable = false) public String city;
    @Column(name = "normalized_name", nullable = false) public String normalizedName;
    protected TeamEntity() {}
    public TeamEntity(Long id, String name, String city, String normalizedName) {
        this.id = id; this.name = name; this.city = city; this.normalizedName = normalizedName;
    }
}
