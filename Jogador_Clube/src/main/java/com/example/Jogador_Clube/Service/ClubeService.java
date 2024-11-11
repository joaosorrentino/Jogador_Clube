package com.example.Jogador_Clube.Service;

import com.example.Jogador_Clube.Model.Clube;
import com.example.Jogador_Clube.Model.Jogador;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClubeService {
    public List<Clube> clubes;


    public ClubeService() {
        clubes = new ArrayList<>();
    }


    public List<String> findAllClubes(){
       List<String> nomesClubes = new ArrayList<>();
       for(Clube clube : clubes){
           nomesClubes.add(clube.getNome());
       }
       return nomesClubes;
    }

    public Clube getClubeById(Integer id){
        for (Clube clube : clubes) {
            if (clube.getId().equals(id)) {
                return clube;
            }
        }
        return null;
    }

    public Clube getClubeByNome(String nome){
        for (Clube clube : clubes) {
            if (clube.getNome().equalsIgnoreCase(nome)) {
                return clube;
            }
        }
        return null;
    }

    public void saveClube(Integer id,String nome) {
        Clube clube = new Clube(id,nome,null);
        clubes.add(clube);
    }

    public Clube editClube(String nome,Integer Id) {
        Clube clube = getClubeById(Id);
        for(Clube c : clubes) {
            if (c.getId().equals(Id)) {
                c.setId(Id);
                c.setNome(nome);
            } else {
                return null;
            }
        }
        return clube;
    }

    public void deleteClube(Integer Id) {
        for(Clube clube : clubes){
            if (clube.getId().equals(Id)){
                List<Jogador> jogador = getJogadoresByClube(clube.getNome());
                clubes.remove(clube);
            }
        }
    }

    public List<Jogador> getJogadoresByClube(String nome){
        for(Clube clube : clubes){
            if(clube.getNome().equalsIgnoreCase(nome)){
                return clube.getJogador();
            }
        }
        return null;
    }

    public void addJogadorInClube(Integer id,String jogador, Integer idjogador){
        Clube clube = getClubeById(id);
        if (clube != null) {
            List<Jogador> jogadores = clube.getJogador();
            if (jogadores == null) {
                jogadores = new ArrayList<>();
                clube.setJogador(jogadores);
            }

            boolean jogadorExiste = jogadores.stream().anyMatch(j -> j.getNome().equalsIgnoreCase(jogador));
            if (!jogadorExiste) {
                Jogador novoJogador = new Jogador(idjogador, jogador, clube.getNome());
                jogadores.add(novoJogador);
            }
        }
    }
}
