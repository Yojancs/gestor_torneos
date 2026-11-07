package pe.edu.unsa.gestortorneos.application.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.unsa.gestortorneos.domain.exception.BusinessConflictException;
import pe.edu.unsa.gestortorneos.domain.exception.ResourceNotFoundException;
import pe.edu.unsa.gestortorneos.domain.model.Team;
import pe.edu.unsa.gestortorneos.domain.repository.TeamRepository;

@Service
public class TeamService {
    private final TeamRepository repository;
    public TeamService(TeamRepository repository) { this.repository = repository; }
    @Transactional
    public Team create(String name, String city) {
        Team team = new Team(name, city);
        if (repository.existsByNameIgnoringCaseAndOuterSpaces(team.getName()))
            throw new BusinessConflictException("Ya existe un equipo con ese nombre");
        return repository.save(team);
    }
    @Transactional(readOnly = true)
    public Team findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado: " + id));
    }
    @Transactional(readOnly = true)
    public List<Team> findAll() { return repository.findAll(); }
}
