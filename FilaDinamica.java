// primeiro elemento inserido e o primeiro a ser removido
package tdel_pilhasfilas;

import java.util.Scanner;

public class FilaDinamica {
    private No inicio;
    private No fim;
    
    private class No {
        int valor;
        No proximo;
        
        No(int valor) {
            this.valor = valor;
            this.proximo = null;
        }
    }
    
    public void insere(int valor) {
        No novoNo = new No(valor);
        
        if (inicio == null) {
            inicio = novoNo;
            fim = novoNo;
        } else {
            fim.proximo = novoNo;
            fim = novoNo;
        }
        
        System.out.println("Elemento " + valor + " inserido na fila.");
    }
    
    public void remove() {
        if (inicio == null) {
            System.out.println("Fila vazia! Nao ha elementos para remover.");
            return;
        }
        
        int valorRemovido = inicio.valor;
        inicio = inicio.proximo;
        
        if (inicio == null) {
            fim = null;
        }
        
        System.out.println("Elemento " + valorRemovido + " removido da fila.");
    }
    
    public void imprime() {
        if (inicio == null) {
            System.out.println("Fila vazia!");
            return;
        }
        
        System.out.print("Fila (inicio -> fim): ");
        No atual = inicio;
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        FilaDinamica fila = new FilaDinamica();
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        do {
            System.out.println("\n=== MENU FILA DINAMICA ===");
            System.out.println("1 - Inserir elemento");
            System.out.println("2 - Remover elemento");
            System.out.println("3 - Imprimir fila");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");
            
            opcao = scanner.nextInt();
            
            switch(opcao) {
                case 1:
                    System.out.print("Digite o valor a ser inserido: ");
                    int valorInserir = scanner.nextInt();
                    fila.insere(valorInserir);
                    break;
                case 2:
                    fila.remove();
                    break;
                case 3:
                    fila.imprime();
                    break;
                case 0:
                    System.out.println("Encerrando programa...");
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        } while (opcao != 0);
        
        scanner.close();
    }
}