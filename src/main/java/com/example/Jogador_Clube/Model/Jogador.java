package com.example.Jogador_Clube.Model;

import jakarta.xml.bind.annotation.XmlRootElement;

import jakarta.persistence.*;

@XmlRootElement
@Entity
@Table(name = "jogagdor") // Nome da tabela no banco de dados
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do ID
    private Integer id;

    @Column(nullable = false) // Nome obrigatório
    private String nome;

    @ManyToOne // Relacionamento muitos-para-um com a classe Clube
    @JoinColumn(name = "clube_id", nullable = false) // Chave estrangeira para o clube
    private Clube clube;

    public Jogador() {
    }
    public Jogador(Integer id, String nome, Clube clube) {
        this.id = id;
        this.nome = nome;
        this.clube = clube;
    }

    // Getters e Setters
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

    public Clube getClube() {
        return clube;
    }

    public void setClube(Clube clube) {
        this.clube = clube;
    }
}
