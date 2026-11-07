package pe.edu.unsa.gestortorneos.domain.model;

import pe.edu.unsa.gestortorneos.domain.exception.DomainException;

public class Team {
    private final Long id;
    private final String name;
    private final String city;

    public Team(String name, String city) { this(null, name, city); }

    public Team(Long id, String name, String city) {
        if (name == null || name.isBlank()) throw new DomainException("El nombre del equipo es obligatorio");
        if (city == null || city.isBlank()) throw new DomainException("La ciudad es obligatoria");
        this.id = id;
        this.name = name.trim();
        this.city = city.trim();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
}
