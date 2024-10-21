package com.example.Jogador_Clube.Model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Jogador {

    private Integer id;
    private String nome;
    private String clube;


    public Jogador(Integer id, String nome, String clube) {
        this.id = id;
        this.nome = nome;
        this.clube = clube;
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

    public String getClube() {
        return clube;
    }

    public void setClube(String clube) {
        this.clube = clube;
    }
}
