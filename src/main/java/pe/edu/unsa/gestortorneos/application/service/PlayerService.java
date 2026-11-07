package pe.edu.unsa.gestortorneos.application.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.unsa.gestortorneos.domain.exception.ResourceNotFoundException;
import pe.edu.unsa.gestortorneos.domain.model.Player;
import pe.edu.unsa.gestortorneos.domain.repository.PlayerRepository;
import pe.edu.unsa.gestortorneos.domain.repository.TeamRepository;

@Service
public class PlayerService {
    private final PlayerRepository players;
    private final TeamRepository teams;
    public PlayerService(PlayerRepository players, TeamRepository teams) { this.players = players; this.teams = teams; }
    @Transactional
    public Player register(String fullName, int age, Long teamId) {
        Player player = new Player(fullName, age, teamId);
        teams.findById(teamId).orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado: " + teamId));
        return players.save(player);
    }
    @Transactional(readOnly = true)
    public Player findById(Long id) {
        return players.findById(id).orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado: " + id));
    }
    @Transactional(readOnly = true)
    public List<Player> findByTeamId(Long teamId) {
        teams.findById(teamId).orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado: " + teamId));
        return players.findByTeamId(teamId);
    }
}
