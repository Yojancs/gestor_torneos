package pe.edu.unsa.gestortorneos.domain.repository;

import java.util.List;
import java.util.Optional;
import pe.edu.unsa.gestortorneos.domain.model.Tournament;

public interface TournamentRepository {
    Tournament save(Tournament tournament);
    Optional<Tournament> findById(Long id);
    List<Tournament> findAll();
}
