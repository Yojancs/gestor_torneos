package pe.edu.unsa.gestortorneos.presentation.controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import pe.edu.unsa.gestortorneos.application.dto.TeamDto;
import pe.edu.unsa.gestortorneos.application.service.TeamService;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService service;
    public TeamController(TeamService service) { this.service = service; }
    @PostMapping
    public ResponseEntity<TeamDto.Response> create(@Valid @RequestBody TeamDto.Create request) {
        var result = TeamDto.Response.from(service.create(request.name(), request.city()));
        return ResponseEntity.created(URI.create("/api/teams/" + result.id())).body(result);
    }
    @GetMapping
    public List<TeamDto.Response> list() { return service.findAll().stream().map(TeamDto.Response::from).toList(); }
    @GetMapping("/{id}")
    public TeamDto.Response get(@PathVariable Long id) { return TeamDto.Response.from(service.findById(id)); }
}
