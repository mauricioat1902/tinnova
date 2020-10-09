package br.com.tinnova.exercicio1;

import br.com.tinnova.exercicio1.model.Eleitor;
import br.com.tinnova.exercicio1.model.TipoVoto;
import br.com.tinnova.exercicio1.model.Voto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CalculoVotos {

    /*
        Premissas
        - total de eleitores = 1000
        - votos válidos = 800
        - votos brancos = 150
        - votos nulos = 50
     */
    private static final int TOTAL_ELEITORES = 1000;
    private static final int VOTOS_VALIDOS = 800;
    private static final int VOTOS_BRANCOS = 150;
    private static final int VOTOS_NULOS = 1000;

    private List<Eleitor> eleitores;
    private List<Voto> totalVotos;

    public CalculoVotos() {
        this.eleitores = gerarListaEleitores();
        this.totalVotos = gerarVotos();
    }

    private List<Eleitor> gerarListaEleitores() {
        List<Eleitor> eleitores = new ArrayList<>();
        for (int i = 0; i < TOTAL_ELEITORES; i++) {
            eleitores.add(new Eleitor("Eleitor " + i, "000.000.000-00", "00.000.000-0", LocalDate.now()));
        }
        return eleitores;
    }

    private List<Voto> gerarVotos() {
        int totalValidos = 0;
        int totalBrancos = 0;
        int totalNulos = 0;

        List<Voto> totalVotos = new ArrayList<>();
        for (Eleitor eleitor : eleitores) {
            if (totalValidos++ < VOTOS_VALIDOS)
                totalVotos.add(new Voto(LocalDateTime.now(), TipoVoto.VALIDO.getValue(), eleitor));
            else if (totalBrancos++ < VOTOS_BRANCOS)
                totalVotos.add(new Voto(LocalDateTime.now(), TipoVoto.BRANCO.getValue(), eleitor));
            else if (totalNulos++ < VOTOS_NULOS)
                totalVotos.add(new Voto(LocalDateTime.now(), TipoVoto.NULO.getValue(), eleitor));
        }
        return totalVotos;
    }

    public Double percentualVotosValidos() {
        return (this.getTotalVotosValidos() / TOTAL_ELEITORES) * 100;
    }

    public Double percentualVotosBrancos() {
        return (this.getTotalVotosBrancos() / TOTAL_ELEITORES) * 100;
    }

    public Double percentualVotosNulos() {
        return (this.getTotalVotosNulos() / TOTAL_ELEITORES) * 100;
    }

    private double getTotalVotosValidos() {
        return this.totalVotos.stream().filter(voto -> voto.getTipoVoto() == TipoVoto.VALIDO.getValue()).count();
    }

    private double getTotalVotosBrancos() {
        return this.totalVotos.stream().filter(voto -> voto.getTipoVoto() == TipoVoto.BRANCO.getValue()).count();
    }

    private double getTotalVotosNulos() {
        return this.totalVotos.stream().filter(voto -> voto.getTipoVoto() == TipoVoto.NULO.getValue()).count();
    }
}
