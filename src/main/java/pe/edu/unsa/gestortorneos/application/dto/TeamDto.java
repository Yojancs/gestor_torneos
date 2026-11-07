package pe.edu.unsa.gestortorneos.application.dto;

import jakarta.validation.constraints.NotBlank;
import pe.edu.unsa.gestortorneos.domain.model.Team;

public final class TeamDto {
    private TeamDto() {}
    public record Create(@NotBlank String name, @NotBlank String city) {}
    public record Response(Long id, String name, String city) {
        public static Response from(Team t) { return new Response(t.getId(), t.getName(), t.getCity()); }
    }
}
