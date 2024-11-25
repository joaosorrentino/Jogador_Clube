package com.example.Jogador_Clube.Model;
import jakarta.xml.bind.annotation.XmlRootElement;

import jakarta.persistence.*;
import java.util.List;

@XmlRootElement
@Entity
@Table(name = "Clube") // Nome da tabela no banco de dados
public class Clube {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do ID
    private Integer id;

    @Column(nullable = false, unique = true) // Nome é obrigatório e único
    private String nome;

    @OneToMany(mappedBy = "clube", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jogador> jogadores;

    public Clube() {
    }

    public Clube(Integer id, String nome, List<Jogador> jogadores) {
        this.id = id;
        this.nome = nome;
        this.jogadores = jogadores;
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

    public List<Jogador> getJogador() {
        return jogadores;
    }

    public void setJogador(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
}
