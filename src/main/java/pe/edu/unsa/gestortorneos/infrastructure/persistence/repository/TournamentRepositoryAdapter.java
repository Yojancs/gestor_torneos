package pe.edu.unsa.gestortorneos.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import pe.edu.unsa.gestortorneos.domain.model.Tournament;
import pe.edu.unsa.gestortorneos.domain.repository.TournamentRepository;
import pe.edu.unsa.gestortorneos.infrastructure.persistence.mapper.TournamentMapper;

@Repository
public class TournamentRepositoryAdapter implements TournamentRepository {
    private final JpaTournamentRepository jpa;
    public TournamentRepositoryAdapter(JpaTournamentRepository jpa) { this.jpa = jpa; }
    public Tournament save(Tournament t) { return TournamentMapper.toDomain(jpa.save(TournamentMapper.toEntity(t))); }
    public Optional<Tournament> findById(Long id) { return jpa.findById(id).map(TournamentMapper::toDomain); }
    public List<Tournament> findAll() { return jpa.findAll().stream().map(TournamentMapper::toDomain).toList(); }
}
