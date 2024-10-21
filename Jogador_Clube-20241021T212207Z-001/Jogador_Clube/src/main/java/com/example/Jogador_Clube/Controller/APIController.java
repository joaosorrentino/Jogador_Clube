package com.example.Jogador_Clube.Controller;

import com.example.Jogador_Clube.Model.Clube;
import com.example.Jogador_Clube.Model.Jogador;
import com.example.Jogador_Clube.Service.ClubeService;
import com.example.Jogador_Clube.Service.JogadorService;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class APIController {
    private JogadorService jogadorService;
    private ClubeService clubeService;

    @Autowired
    public APIController(JogadorService jogadorService, ClubeService clubeService) {
        this.jogadorService = jogadorService;
        this.clubeService = clubeService;
    }
    @GetMapping(value = "/getAllJogadores",  produces = {"application/json","application/xml"})
    public List<Jogador> getAllJogadores() {
        return jogadorService.findAllJogadores();
    }

    @GetMapping(value = "/getAllClubes",  produces = {"application/json","application/xml"})
    public ResponseEntity<List<String>> getAllClubes() {
        return ResponseEntity.ok(clubeService.findAllClubes());
    }

    @GetMapping(value = "/getJogadorById",  produces = {"application/json","application/xml"})
    public ResponseEntity<Jogador> getJogadorById(@RequestParam Integer id) {
        return ResponseEntity.ok(jogadorService.getJogadorById(id));
    }

    @GetMapping(value = "/getClubeById",  produces = {"application/json","application/xml"})
    public ResponseEntity<Clube> getClubeById(@RequestParam Integer id) {
        return ResponseEntity.ok(clubeService.getClubeById(id));
    }

    @PostMapping(value = "/saveJogador",  consumes = {"application/json","application/xml"})
    public ResponseEntity saveJogador(@RequestBody Jogador jogador){
        Jogador jogadores = jogadorService.saveJogador(jogador);
        if(jogadores == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Clube não encontrado");
        } else {
            return ResponseEntity.ok().body("Jogador salvo com sucesso");
        }
    }

    @PostMapping(value = "/saveClube",  consumes = {"application/json","application/xml"})
    public ResponseEntity saveClube(@RequestParam String nome, Integer id){
        clubeService.saveClube(id,nome);
        return ResponseEntity.ok().body("Clube salvo com sucesso");
    }

    @PutMapping(value = "/editJogador",  consumes = {"application/json","application/xml"})
    public ResponseEntity<Jogador> editJogador(@RequestBody Jogador jogador){
        Jogador jogadorNovo = jogadorService.editJogador(jogador);
        if (jogadorNovo == null){
            return ResponseEntity.notFound().build();
        } else{
            return ResponseEntity.ok().body(jogadorNovo);
        }

    }

    @PutMapping(value = "/editClube",  consumes = {"application/json","application/xml"})
    public ResponseEntity<Clube> editClube(@RequestBody String nome, @RequestParam Integer Id){
        Clube clubeNovo = clubeService.editClube(nome,Id);
        if (clubeNovo == null){
            return ResponseEntity.notFound().build();
        } else{
            return ResponseEntity.ok().body(clubeNovo);
        }
    }

    @DeleteMapping(value = "/deleteClube", produces = {"application/json","application/xml"})
    public ResponseEntity deleteClube(@RequestParam Integer id){
        if(getClubeById(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Clube não encontrado");
        } else{
            clubeService.deleteClube(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Clube deletado com sucesso");
        }
    }

    @DeleteMapping(value = "/deleteJogador", produces = {"application/json","application/xml"})
    public ResponseEntity deleteJogador(@RequestParam Integer id){
        if(getJogadorById(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador não encontrado");
        } else{
            jogadorService.deleteJogador(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Jogador deletado com sucesso");
        }
    }
}
