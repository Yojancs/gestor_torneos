package pe.edu.unsa.gestortorneos.application.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import pe.edu.unsa.gestortorneos.domain.model.Registration;

public final class RegistrationDto {
    private RegistrationDto() {}
    public record Create(@NotNull Long tournamentId, @NotNull Long teamId) {}
    public record Response(Long id, Long tournamentId, Long teamId, LocalDateTime registeredAt) {
        public static Response from(Registration r) { return new Response(r.getId(), r.getTournamentId(), r.getTeamId(), r.getRegisteredAt()); }
    }
}
