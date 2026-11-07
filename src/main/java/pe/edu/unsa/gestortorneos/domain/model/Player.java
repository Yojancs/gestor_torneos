package pe.edu.unsa.gestortorneos.domain.model;

import pe.edu.unsa.gestortorneos.domain.exception.DomainException;

public class Player {
    private final Long id;
    private final String fullName;
    private final int age;
    private final Long teamId;

    public Player(String fullName, int age, Long teamId) { this(null, fullName, age, teamId); }

    public Player(Long id, String fullName, int age, Long teamId) {
        if (fullName == null || fullName.isBlank()) throw new DomainException("El nombre del jugador es obligatorio");
        if (age < 16 || age > 60) throw new DomainException("La edad debe estar entre 16 y 60 años");
        if (teamId == null) throw new DomainException("El equipo es obligatorio");
        this.id = id;
        this.fullName = fullName.trim();
        this.age = age;
        this.teamId = teamId;
    }

    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public int getAge() { return age; }
    public Long getTeamId() { return teamId; }
}
