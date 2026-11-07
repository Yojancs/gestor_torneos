package pe.edu.unsa.gestortorneos.application.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.unsa.gestortorneos.domain.exception.ResourceNotFoundException;
import pe.edu.unsa.gestortorneos.domain.model.Tournament;
import pe.edu.unsa.gestortorneos.domain.repository.TournamentRepository;

@Service
public class TournamentService {
    private final TournamentRepository repository;
    public TournamentService(TournamentRepository repository) { this.repository = repository; }

    @Transactional
    public Tournament create(String name, LocalDate startDate, LocalDate endDate) {
        return repository.save(new Tournament(name, startDate, endDate));
    }
    @Transactional(readOnly = true)
    public Tournament findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Torneo no encontrado: " + id));
    }
    @Transactional(readOnly = true)
    public List<Tournament> findAll() { return repository.findAll(); }
    @Transactional
    public Tournament activate(Long id) { Tournament t = findById(id); t.activate(); return repository.save(t); }
    @Transactional
    public Tournament finish(Long id) { Tournament t = findById(id); t.finish(); return repository.save(t); }
}
