package br.com.tinnova.exercicio3;

public class Fatorial {

    public long calcular(long numero) {
        if (numero == 0)
            return 1;

        return calcular(numero - 1) * numero;
    }

}
