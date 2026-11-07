package pe.edu.unsa.gestortorneos.infrastructure.persistence.mapper;

import pe.edu.unsa.gestortorneos.domain.model.Registration;
import pe.edu.unsa.gestortorneos.infrastructure.persistence.entity.RegistrationEntity;

public final class RegistrationMapper {
    private RegistrationMapper() {}
    public static Registration toDomain(RegistrationEntity e) { return new Registration(e.id, e.tournamentId, e.teamId, e.registeredAt); }
    public static RegistrationEntity toEntity(Registration r) { return new RegistrationEntity(r.getId(), r.getTournamentId(), r.getTeamId(), r.getRegisteredAt()); }
}
