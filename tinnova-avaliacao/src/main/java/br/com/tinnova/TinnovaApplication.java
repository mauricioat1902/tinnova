package br.com.tinnova;

import br.com.tinnova.exercicio1.CalculoVotos;
import br.com.tinnova.exercicio2.BubbleSort;
import br.com.tinnova.exercicio3.Fatorial;
import br.com.tinnova.exercicio4.SomaMultiplos;

import java.io.IOException;
import java.util.Scanner;

public class TinnovaApplication {

    public static void main(String[] args) throws IOException {
        boolean finalizar = false;
        while (!finalizar) {
            exibirOpcoesExercicios();
            Scanner scanner = new Scanner(System.in);
            int numeroExercicio = scanner.nextInt();
            executarExercicio(numeroExercicio);
            if (numeroExercicio == 5)
                finalizar = true;
        }
    }

    private static void executarExercicio(int numeroExercicio) {
        switch (numeroExercicio) {
            case 1:
                executarExercicio1();
                break;
            case 2:
                executarExercicio2();
                break;
            case 3:
                executarExercicio3();
                break;
            case 4:
                executarExercicio4();
                break;
            case 5:
                break;
            default:
                System.out.println("Valor inexistente");
                break;
        }
    }

    private static void exibirOpcoesExercicios() throws IOException {
        System.out.println();
        System.out.println("---------------------------------- ### ----------------------------------");
        System.out.println("Qual exercício deseja executar? (Digite o número)");
        System.out.println("(1) - Votos em relação ao total de eleitores");
        System.out.println("(2) - Algoritmo de odenação Bubble Sorte");
        System.out.println("(3) - Fatorial");
        System.out.println("(4) - Soma dos múltiplos de 3 ou 5");
        System.out.println("(5) - Sair");
    }

    private static void executarExercicio1() {
        System.out.println();
        CalculoVotos calculoVotos = new CalculoVotos();
        System.out.println("VALIDOS: " + calculoVotos.percentualVotosValidos() + "%");
        System.out.println("BRANCOS: " + calculoVotos.percentualVotosBrancos() + "%");
        System.out.println("NULOS: " + calculoVotos.percentualVotosNulos() + "%");
    }

    private static void executarExercicio2() {
        System.out.println();
        BubbleSort bubbleSort = new BubbleSort();
        System.out.printf("Vetor não ordenado: %s%n", bubbleSort.obterValoresFormatados());
        bubbleSort.ordenar();
        System.out.printf("Vetor ordenado: %s%n", bubbleSort.obterValoresFormatados());
    }

    private static void executarExercicio3() {
        System.out.println();
        Fatorial fatorial = new Fatorial();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número que você pretende obter o fatorial: ");
        int numero = scanner.nextInt();
        System.out.printf("Fatorial: %d%n", fatorial.calcular(numero));
    }

    private static void executarExercicio4() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número limite para buscar os múltiplos de 3 ou 5: ");
        int limite = scanner.nextInt();
        SomaMultiplos somaMultiplos = new SomaMultiplos(limite);
        System.out.printf("A soma dos múltipls de 3 ou 5 no intervalor 1 a %d é %d%n", limite, somaMultiplos.somaMultiplos());
    }
}
