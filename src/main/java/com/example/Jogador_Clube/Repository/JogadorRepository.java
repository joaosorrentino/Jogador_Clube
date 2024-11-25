package com.example.Jogador_Clube.Repository;

import com.example.Jogador_Clube.Model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Integer> {
    // Consulta personalizada para buscar jogador pelo nome
    Jogador findByNomeIgnoreCase(String nome);
}
