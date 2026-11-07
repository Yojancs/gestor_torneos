package pe.edu.unsa.gestortorneos.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import pe.edu.unsa.gestortorneos.domain.model.Player;
import pe.edu.unsa.gestortorneos.domain.repository.PlayerRepository;
import pe.edu.unsa.gestortorneos.infrastructure.persistence.mapper.PlayerMapper;

@Repository
public class PlayerRepositoryAdapter implements PlayerRepository {
    private final JpaPlayerRepository jpa;
    public PlayerRepositoryAdapter(JpaPlayerRepository jpa) { this.jpa = jpa; }
    public Player save(Player p) { return PlayerMapper.toDomain(jpa.save(PlayerMapper.toEntity(p))); }
    public Optional<Player> findById(Long id) { return jpa.findById(id).map(PlayerMapper::toDomain); }
    public List<Player> findByTeamId(Long teamId) { return jpa.findByTeamId(teamId).stream().map(PlayerMapper::toDomain).toList(); }
}
