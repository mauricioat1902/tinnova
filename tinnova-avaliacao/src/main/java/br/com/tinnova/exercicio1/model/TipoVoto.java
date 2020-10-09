package br.com.tinnova.exercicio1.model;

public enum TipoVoto {
    VALIDO(1),
    BRANCO(2),
    NULO(3);

    int tipoVoto;

    TipoVoto(int tipoVoto) {
        this.tipoVoto = tipoVoto;
    }

    public int getValue() {
        return this.tipoVoto;
    }
}
