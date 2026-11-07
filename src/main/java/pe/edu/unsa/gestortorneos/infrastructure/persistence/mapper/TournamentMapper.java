package pe.edu.unsa.gestortorneos.infrastructure.persistence.mapper;

import pe.edu.unsa.gestortorneos.domain.model.Tournament;
import pe.edu.unsa.gestortorneos.infrastructure.persistence.entity.TournamentEntity;

public final class TournamentMapper {
    private TournamentMapper() {}
    public static Tournament toDomain(TournamentEntity e) { return new Tournament(e.id, e.name, e.startDate, e.endDate, e.status); }
    public static TournamentEntity toEntity(Tournament t) { return new TournamentEntity(t.getId(), t.getName(), t.getStartDate(), t.getEndDate(), t.getStatus()); }
}
