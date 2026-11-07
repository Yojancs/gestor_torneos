package pe.edu.unsa.gestortorneos.infrastructure.persistence.mapper;

import java.util.Locale;
import pe.edu.unsa.gestortorneos.domain.model.Team;
import pe.edu.unsa.gestortorneos.infrastructure.persistence.entity.TeamEntity;

public final class TeamMapper {
    private TeamMapper() {}
    public static String normalize(String name) { return name.trim().toLowerCase(Locale.ROOT); }
    public static Team toDomain(TeamEntity e) { return new Team(e.id, e.name, e.city); }
    public static TeamEntity toEntity(Team t) { return new TeamEntity(t.getId(), t.getName(), t.getCity(), normalize(t.getName())); }
}
