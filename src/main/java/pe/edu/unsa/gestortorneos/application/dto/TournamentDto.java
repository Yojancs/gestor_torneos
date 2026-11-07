package pe.edu.unsa.gestortorneos.application.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pe.edu.unsa.gestortorneos.domain.model.Tournament;
import pe.edu.unsa.gestortorneos.domain.model.TournamentStatus;

public final class TournamentDto {
    private TournamentDto() {}
    public record Create(@NotBlank String name, @NotNull LocalDate startDate, @NotNull LocalDate endDate) {}
    public record Response(Long id, String name, LocalDate startDate, LocalDate endDate, TournamentStatus status) {
        public static Response from(Tournament t) { return new Response(t.getId(), t.getName(), t.getStartDate(), t.getEndDate(), t.getStatus()); }
    }
}
