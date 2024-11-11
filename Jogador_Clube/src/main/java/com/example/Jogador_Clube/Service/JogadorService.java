    package com.example.Jogador_Clube.Service;

    import com.example.Jogador_Clube.Model.Clube;
    import com.example.Jogador_Clube.Model.Jogador;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;
    import java.util.List;

    @Service
    public class JogadorService {
        private List<Jogador> jogadores;
        private ClubeService clubeService;
        public JogadorService(ClubeService clubeService) {
            jogadores = new ArrayList<>();
            this.clubeService = clubeService;
        }

        public List<Jogador> findAllJogadores(){
            return jogadores;
        }

        public Jogador getJogadorById(Integer id){
            for(Jogador jogador : jogadores){
                if (jogador.getId().equals(id)){
                    return jogador;
                }
            }
            return null;
        }

        public Jogador saveJogador(Jogador jogador) {
            Clube clube = clubeService.getClubeByNome(jogador.getClube());
            if (clube != null){
                jogadores.add(jogador);
                clubeService.addJogadorInClube(clube.getId(), jogador.getNome(), jogador.getId());
                return jogador;
            } else {
                return null;
            }
        }

        public Jogador editJogador(Jogador jogador) {
            Jogador jogadorExistente = getJogadorById(jogador.getId());
            if (jogadorExistente != null) {
                String oldClubeNome = jogadorExistente.getClube();

                jogadorExistente.setNome(jogador.getNome());
                jogadorExistente.setClube(jogador.getClube());


                if (!oldClubeNome.equals(jogador.getClube())) {
                    Clube oldClube = clubeService.getClubeByNome(oldClubeNome);
                    if (oldClube != null) {
                        oldClube.getJogador().removeIf(j -> j.getId().equals(jogador.getId()));
                    }
                }


                Clube newClube = clubeService.getClubeByNome(jogador.getClube());
                if (newClube != null) {
                    newClube.getJogador().removeIf(j -> j.getId().equals(jogador.getId()));
                    clubeService.addJogadorInClube(newClube.getId(), jogador.getNome(), jogador.getId());
                }

                return jogadorExistente;
            }
            return null;
        }

        public void deleteJogador(Integer id) {
            Jogador jogadorToRemove = null;
            for (Jogador jogador : jogadores) {
                if (jogador.getId().equals(id)) {
                    jogadorToRemove = jogador;
                    break;
                }
            }

            if (jogadorToRemove != null) {
                jogadores.remove(jogadorToRemove);
                Clube clube = clubeService.getClubeByNome(jogadorToRemove.getClube());
                if (clube != null) {
                    clube.getJogador().removeIf(j -> j.getId().equals(id));
                }
            }
        }
        public void deleteAllJogador(List<Jogador> jogador) {
            jogadores.removeAll(jogador);
        }
    }
