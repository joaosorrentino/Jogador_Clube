package com.example.Jogador_Clube.Service;

import com.example.Jogador_Clube.Model.Clube;
import com.example.Jogador_Clube.Model.Jogador;
import com.example.Jogador_Clube.Repository.ClubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClubeService {

    @Autowired
    private ClubeRepository clubeRepository;

    // Retorna a lista de todos os nomes dos clubes
    public List<String> findAllClubes() {
        return clubeRepository.findAll().stream()
                .map(Clube::getNome)
                .toList();
    }

    // Retorna um clube pelo ID
    public Clube getClubeById(Integer id) {
        return clubeRepository.findById(id).orElse(null);
    }

    // Retorna um clube pelo nome
    public Clube getClubeByNome(String nome) {
        return clubeRepository.findByNomeIgnoreCase(nome);
    }

    // Salva um clube no banco
    public void saveClube(Integer id, String nome) {
        Clube clube = new Clube(id, nome, null);
        clubeRepository.save(clube);
    }

    // Edita os dados de um clube existente
    public Clube editClube(String nome, Integer id) {
        Optional<Clube> optionalClube = clubeRepository.findById(id);
        if (optionalClube.isPresent()) {
            Clube clube = optionalClube.get();
            clube.setNome(nome);
            return clubeRepository.save(clube);
        }
        return null;
    }

    // Deleta um clube pelo ID
    public void deleteClube(Integer id) {
        Optional<Clube> optionalClube = clubeRepository.findById(id);
        if (optionalClube.isPresent()) {
            Clube clube = optionalClube.get();
            clubeRepository.delete(clube);
        }
    }

    // Retorna a lista de jogadores de um clube pelo nome do clube
    public List<Jogador> getJogadoresByClube(String nome) {
        Clube clube = clubeRepository.findByNomeIgnoreCase(nome);
        return clube != null ? clube.getJogador() : null;
    }

    // Adiciona um jogador a um clube
    public void addJogadorInClube(Integer idClube, String nomeJogador, Integer idJogador) {
        Clube clube = clubeRepository.findById(idClube).orElse(null);
        if (clube != null) {
            List<Jogador> jogadores = clube.getJogador();
            if (jogadores == null) {
                jogadores = new ArrayList<>();
                clube.setJogador(jogadores);
            }

            boolean jogadorExiste = jogadores.stream()
                    .anyMatch(j -> j.getNome().equalsIgnoreCase(nomeJogador));

            if (!jogadorExiste) {
                Jogador novoJogador = new Jogador(idJogador, nomeJogador, clube);
                jogadores.add(novoJogador);
                clubeRepository.save(clube); // Persistir a atualização no banco
            }
        }
    }
}
