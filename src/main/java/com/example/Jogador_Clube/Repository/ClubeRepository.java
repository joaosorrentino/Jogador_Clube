package com.example.Jogador_Clube.Repository;

import com.example.Jogador_Clube.Model.Clube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubeRepository extends JpaRepository<Clube, Integer> {
    // Consulta personalizada para buscar clube pelo nome, ignorando maiúsculas/minúsculas
    Clube findByNomeIgnoreCase(String nome);
}
