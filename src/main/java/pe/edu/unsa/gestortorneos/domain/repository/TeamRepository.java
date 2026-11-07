package pe.edu.unsa.gestortorneos.domain.repository;

import java.util.List;
import java.util.Optional;
import pe.edu.unsa.gestortorneos.domain.model.Team;

public interface TeamRepository {
    Team save(Team team);
    Optional<Team> findById(Long id);
    List<Team> findAll();
    boolean existsByNameIgnoringCaseAndOuterSpaces(String name);
}
