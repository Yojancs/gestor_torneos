package pe.edu.unsa.gestortorneos.infrastructure.persistence.mapper;

import pe.edu.unsa.gestortorneos.domain.model.Player;
import pe.edu.unsa.gestortorneos.infrastructure.persistence.entity.PlayerEntity;

public final class PlayerMapper {
    private PlayerMapper() {}
    public static Player toDomain(PlayerEntity e) { return new Player(e.id, e.fullName, e.age, e.teamId); }
    public static PlayerEntity toEntity(Player p) { return new PlayerEntity(p.getId(), p.getFullName(), p.getAge(), p.getTeamId()); }
}
