package br.com.tinnova.exercicio2;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
@Setter
public class BubbleSort {
    // Valores de Premissa
    private int[] valores = {5, 3, 2, 4, 7, 1, 0, 6};

    public void ordenar() {
        int tamanhoVetor = valores.length;
        boolean estaOrdenado = false;
        int temp = 0;
        int cont = 0;
        while (!estaOrdenado) {
            estaOrdenado = true;
            for (int j = 1; j < tamanhoVetor - cont; j++) {
                if (valores[j-1] > valores[j]) {
                    temp = valores[j-1];
                    valores[j - 1] = valores[j];
                    valores[j] = temp;
                    estaOrdenado = false;
                }
            }
            cont++;
        }
        System.out.println("Iterações: " + cont);
//        for (int i = 0; i < tamanhoVetor; i++) {
//            for (int j = 1; j < tamanhoVetor - i; j++) {
//                if (valores[j-1] > valores[j]) {
//                    temp = valores[j-1];
//                    valores[j - 1] = valores[j];
//                    valores[j] = temp;
//                    estaOrdenado = false;
//                }
//            }
//
//        }
    }

    public String obterValoresFormatados() {
        String resultado = Arrays.stream(valores)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
        return resultado;
    }
}
