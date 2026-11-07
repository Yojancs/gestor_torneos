package pe.edu.unsa.gestortorneos.domain.repository;

import java.util.List;
import java.util.Optional;
import pe.edu.unsa.gestortorneos.domain.model.Player;

public interface PlayerRepository {
    Player save(Player player);
    Optional<Player> findById(Long id);
    List<Player> findByTeamId(Long teamId);
}
