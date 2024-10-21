package com.example.Jogador_Clube.Model;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement
public class Clube {
    private Integer id;
    private String nome;
    private List<Jogador> Jogador;

    public Clube(Integer id, String nome, List<com.example.Jogador_Clube.Model.Jogador> jogador) {
        this.id = id;
        this.nome = nome;
        Jogador = jogador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Jogador> getJogador() {
        return Jogador;
    }

    public void setJogador(List<Jogador> jogador) {
        Jogador = jogador;
    }
}
