package tdel_pilhasfilas;

import java.util.Scanner;

public class PilhaDinamica {
    private No topo;
    
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
        novoNo.proximo = topo;
        topo = novoNo;
        System.out.println("Elemento " + valor + " inserido na pilha.");
    }
    
    public void remove() {
        if (topo == null) {
            System.out.println("Pilha vazia! Nao ha elementos para remover.");
            return;
        }
        
        int valorRemovido = topo.valor;
        topo = topo.proximo;
        System.out.println("Elemento " + valorRemovido + " removido da pilha.");
    }
    
    public void imprime() {
        if (topo == null) {
            System.out.println("Pilha vazia!");
            return;
        }
        
        System.out.print("Pilha (topo -> base): ");
        No atual = topo;
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        PilhaDinamica pilha = new PilhaDinamica();
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        do {
            System.out.println("\n=== MENU PILHA DINAMICA ===");
            System.out.println("1 - Inserir elemento");
            System.out.println("2 - Remover elemento");
            System.out.println("3 - Imprimir pilha");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");
            
            opcao = scanner.nextInt();
            
            switch(opcao) {
                case 1:
                    System.out.print("Digite o valor a ser inserido: ");
                    int valorInserir = scanner.nextInt();
                    pilha.insere(valorInserir);
                    break;
                case 2:
                    pilha.remove();
                    break;
                case 3:
                    pilha.imprime();
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
