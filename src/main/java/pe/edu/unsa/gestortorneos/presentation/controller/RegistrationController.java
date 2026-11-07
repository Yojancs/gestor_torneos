package pe.edu.unsa.gestortorneos.presentation.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import pe.edu.unsa.gestortorneos.application.dto.RegistrationDto;
import pe.edu.unsa.gestortorneos.application.service.RegistrationService;

@RestController
public class RegistrationController {
    private final RegistrationService service;
    public RegistrationController(RegistrationService service) { this.service = service; }
    @PostMapping("/api/registrations")
    public ResponseEntity<RegistrationDto.Response> create(@Valid @RequestBody RegistrationDto.Create request) {
        var result = RegistrationDto.Response.from(service.register(request.tournamentId(), request.teamId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @GetMapping("/api/tournaments/{tournamentId}/registrations")
    public List<RegistrationDto.Response> byTournament(@PathVariable Long tournamentId) {
        return service.findByTournamentId(tournamentId).stream().map(RegistrationDto.Response::from).toList();
    }
}
