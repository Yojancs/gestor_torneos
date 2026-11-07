package pe.edu.unsa.gestortorneos.domain.model;

import java.time.LocalDate;
import pe.edu.unsa.gestortorneos.domain.exception.BusinessConflictException;
import pe.edu.unsa.gestortorneos.domain.exception.DomainException;

public class Tournament {
    private final Long id;
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private TournamentStatus status;

    public Tournament(String name, LocalDate startDate, LocalDate endDate) {
        this(null, name, startDate, endDate, TournamentStatus.PLANNED);
    }

    public Tournament(Long id, String name, LocalDate startDate, LocalDate endDate, TournamentStatus status) {
        if (name == null || name.isBlank()) throw new DomainException("El nombre del torneo es obligatorio");
        if (startDate == null || endDate == null) throw new DomainException("Las fechas del torneo son obligatorias");
        if (endDate.isBefore(startDate)) throw new DomainException("La fecha final no puede ser anterior a la inicial");
        this.id = id;
        this.name = name.trim();
        this.startDate = startDate;
        this.endDate = endDate;
        if (status == null) throw new DomainException("El estado es obligatorio");
        this.status = status;
    }

    public void activate() {
        if (status != TournamentStatus.PLANNED) throw new BusinessConflictException("Solo se puede activar un torneo planificado");
        status = TournamentStatus.ACTIVE;
    }

    public void finish() {
        if (status != TournamentStatus.ACTIVE) throw new BusinessConflictException("Solo se puede finalizar un torneo activo");
        status = TournamentStatus.FINISHED;
    }

    public void ensureRegistrationOpen() {
        if (status != TournamentStatus.PLANNED)
            throw new BusinessConflictException("Solo se permiten inscripciones en torneos planificados");
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public TournamentStatus getStatus() { return status; }
}
