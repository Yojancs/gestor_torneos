package pe.edu.unsa.gestortorneos.application.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pe.edu.unsa.gestortorneos.domain.model.Player;

public final class PlayerDto {
    private PlayerDto() {}
    public record Create(@NotBlank String fullName, @Min(16) @Max(60) int age, @NotNull Long teamId) {}
    public record Response(Long id, String fullName, int age, Long teamId) {
        public static Response from(Player p) { return new Response(p.getId(), p.getFullName(), p.getAge(), p.getTeamId()); }
    }
}
