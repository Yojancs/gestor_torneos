package pe.edu.unsa.gestortorneos.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class PlayerEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) public Long id;
    @Column(nullable = false) public String fullName;
    @Column(nullable = false) public int age;
    @Column(nullable = false) public Long teamId;
    protected PlayerEntity() {}
    public PlayerEntity(Long id, String fullName, int age, Long teamId) {
        this.id = id; this.fullName = fullName; this.age = age; this.teamId = teamId;
    }
}
