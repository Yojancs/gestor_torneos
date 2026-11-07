package pe.edu.unsa.gestortorneos.presentation.controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import pe.edu.unsa.gestortorneos.application.dto.PlayerDto;
import pe.edu.unsa.gestortorneos.application.service.PlayerService;

@RestController
public class PlayerController {
    private final PlayerService service;
    public PlayerController(PlayerService service) { this.service = service; }
    @PostMapping("/api/players")
    public ResponseEntity<PlayerDto.Response> create(@Valid @RequestBody PlayerDto.Create request) {
        var result = PlayerDto.Response.from(service.register(request.fullName(), request.age(), request.teamId()));
        return ResponseEntity.created(URI.create("/api/players/" + result.id())).body(result);
    }
    @GetMapping("/api/players/{id}")
    public PlayerDto.Response get(@PathVariable Long id) { return PlayerDto.Response.from(service.findById(id)); }
    @GetMapping("/api/teams/{teamId}/players")
    public List<PlayerDto.Response> byTeam(@PathVariable Long teamId) {
        return service.findByTeamId(teamId).stream().map(PlayerDto.Response::from).toList();
    }
}
