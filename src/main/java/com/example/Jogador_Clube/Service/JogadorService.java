package com.example.Jogador_Clube.Service;

import com.example.Jogador_Clube.Model.Clube;
import com.example.Jogador_Clube.Model.Jogador;
import com.example.Jogador_Clube.Repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private ClubeService clubeService;

    // Retorna todos os jogadores
    public List<Jogador> findAllJogadores() {
        return jogadorRepository.findAll();
    }

    // Retorna um jogador pelo ID
    public Jogador getJogadorById(Integer id) {
        return jogadorRepository.findById(id).orElse(null);
    }

    // Salva um jogador
    public Jogador saveJogador(Jogador jogador) {
        Clube clube = clubeService.getClubeByNome(jogador.getClube().getNome()); // Acessa o nome do clube
        if (clube != null) {
            jogador.setClube(clube); // Associa o clube diretamente ao jogador
            Jogador novoJogador = jogadorRepository.save(jogador);
            clubeService.addJogadorInClube(clube.getId(), jogador.getNome(), jogador.getId());
            return novoJogador;
        }
        return null; // Clube não encontrado
    }

    // Edita um jogador
    public Jogador editJogador(Jogador jogador) {
        Jogador jogadorExistente = jogadorRepository.findById(jogador.getId()).orElse(null);
        if (jogadorExistente != null) {
            Clube oldClube = jogadorExistente.getClube(); // Clube antigo
            jogadorExistente.setNome(jogador.getNome());
            jogadorExistente.setClube(jogador.getClube()); // Atualiza o clube

            // Se o clube foi alterado, atualize os relacionamentos
            if (!oldClube.equals(jogador.getClube())) {
                oldClube.getJogador().removeIf(j -> j.getId().equals(jogador.getId())); // Remove o jogador do clube antigo

                Clube newClube = clubeService.getClubeByNome(jogador.getClube().getNome());
                if (newClube != null) {
                    clubeService.addJogadorInClube(newClube.getId(), jogador.getNome(), jogador.getId());
                }
            }

            return jogadorRepository.save(jogadorExistente); // Salva as alterações
        }
        return null; // Jogador não encontrado
    }

    // Deleta um jogador pelo ID
    public void deleteJogador(Integer id) {
        Jogador jogadorToRemove = jogadorRepository.findById(id).orElse(null);
        if (jogadorToRemove != null) {
            Clube clube = jogadorToRemove.getClube();
            if (clube != null) {
                clube.getJogador().removeIf(j -> j.getId().equals(id)); // Remove o jogador do clube
            }
            jogadorRepository.delete(jogadorToRemove); // Deleta o jogador
        }
    }

    // Deleta todos os jogadores fornecidos
    public void deleteAllJogador(List<Jogador> jogadores) {
        jogadorRepository.deleteAll(jogadores); // Deleta todos os jogadores
    }
}
