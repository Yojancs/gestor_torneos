package pe.edu.unsa.gestortorneos.presentation.controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import pe.edu.unsa.gestortorneos.application.dto.TournamentDto;
import pe.edu.unsa.gestortorneos.application.service.TournamentService;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {
    private final TournamentService service;
    public TournamentController(TournamentService service) { this.service = service; }
    @PostMapping
    public ResponseEntity<TournamentDto.Response> create(@Valid @RequestBody TournamentDto.Create request) {
        var result = TournamentDto.Response.from(service.create(request.name(), request.startDate(), request.endDate()));
        return ResponseEntity.created(URI.create("/api/tournaments/" + result.id())).body(result);
    }
    @GetMapping
    public List<TournamentDto.Response> list() { return service.findAll().stream().map(TournamentDto.Response::from).toList(); }
    @GetMapping("/{id}")
    public TournamentDto.Response get(@PathVariable Long id) { return TournamentDto.Response.from(service.findById(id)); }
    @PatchMapping("/{id}/activate")
    public TournamentDto.Response activate(@PathVariable Long id) { return TournamentDto.Response.from(service.activate(id)); }
    @PatchMapping("/{id}/finish")
    public TournamentDto.Response finish(@PathVariable Long id) { return TournamentDto.Response.from(service.finish(id)); }
}
