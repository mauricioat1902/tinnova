package br.com.tinnova.exercicio4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SomaMultiplos {
    private int limite;

    // Premissa: múltiplos de 3 ou 5
    public long somaMultiplos() {
        long soma = 0;
        for (int numero = 0; numero < limite; numero++) {
            if (numero % 3 == 0 || numero % 5 == 0)
                soma += numero;
        }
        return soma;
    }

}
