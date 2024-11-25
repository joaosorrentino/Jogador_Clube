package com.example.Jogador_Clube.Controller;

import com.example.Jogador_Clube.Model.Clube;
import com.example.Jogador_Clube.Model.Jogador;
import com.example.Jogador_Clube.Service.ClubeService;
import com.example.Jogador_Clube.Service.JogadorService;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class APIController {

    private final JogadorService jogadorService;
    private final ClubeService clubeService;

    @GetMapping(value = "/getAllJogadores", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<Jogador>> getAllJogadores() {
        List<Jogador> jogadores = jogadorService.findAllJogadores();
        return jogadores.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(jogadores);
    }

    @GetMapping(value = "/getAllClubes", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<String>> getAllClubes() {
        List<String> clubes = clubeService.findAllClubes();
        return clubes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(clubes);
    }

    @GetMapping(value = "/getJogadorById", produces = {"application/json", "application/xml"})
    public ResponseEntity<Jogador> getJogadorById(@RequestParam Integer id) {
        Jogador jogador = jogadorService.getJogadorById(id);
        return jogador == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(jogador);
    }

    @GetMapping(value = "/getClubeById", produces = {"application/json", "application/xml"})
    public ResponseEntity<Clube> getClubeById(@RequestParam Integer id) {
        Clube clube = clubeService.getClubeById(id);
        return clube == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(clube);
    }

    @PostMapping(value = "/saveJogador", consumes = {"application/json", "application/xml"})
    public ResponseEntity<String> saveJogador(@RequestBody Jogador jogador) {
        Jogador novoJogador = jogadorService.saveJogador(jogador);
        if (novoJogador == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Clube não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Jogador salvo com sucesso.");
    }

    @PostMapping(value = "/saveClube", consumes = {"application/json", "application/xml"})
    public ResponseEntity<String> saveClube(@RequestBody Clube clube) {
        clubeService.saveClube(clube.getId(), clube.getNome());
        return ResponseEntity.status(HttpStatus.CREATED).body("Clube salvo com sucesso.");
    }

    @PutMapping(value = "/editJogador", consumes = {"application/json", "application/xml"})
    public ResponseEntity<Jogador> editJogador(@RequestBody Jogador jogador) {
        Jogador jogadorEditado = jogadorService.editJogador(jogador);
        return jogadorEditado == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(jogadorEditado);
    }

    @PutMapping(value = "/editClube", consumes = {"application/json", "application/xml"})
    public ResponseEntity<Clube> editClube(@RequestBody Clube clube) {
        Clube clubeEditado = clubeService.editClube(clube.getNome(), clube.getId());
        return clubeEditado == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(clubeEditado);
    }

    @DeleteMapping(value = "/deleteClube", produces = {"application/json", "application/xml"})
    public ResponseEntity<String> deleteClube(@RequestParam Integer id) {
        Clube clube = clubeService.getClubeById(id);
        if (clube == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Clube não encontrado.");
        }
        clubeService.deleteClube(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Clube deletado com sucesso.");
    }

    @DeleteMapping(value = "/deleteJogador", produces = {"application/json", "application/xml"})
    public ResponseEntity<String> deleteJogador(@RequestParam Integer id) {
        Jogador jogador = jogadorService.getJogadorById(id);
        if (jogador == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador não encontrado.");
        }
        jogadorService.deleteJogador(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Jogador deletado com sucesso.");
    }
}
