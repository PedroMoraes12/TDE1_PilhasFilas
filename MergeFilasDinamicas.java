package tdel_pilhasfilas;

import java.util.Scanner;

public class MergeFilasDinamicas {
    
    private static class Fila {
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
        }
        
        public int remove() {
            if (inicio == null) {
                return -1;
            }
            
            int valorRemovido = inicio.valor;
            inicio = inicio.proximo;
            
            if (inicio == null) {
                fim = null;
            }
            
            return valorRemovido;
        }
        
        public boolean vazia() {
            return inicio == null;
        }
        
        public void imprime() {
            if (inicio == null) {
                System.out.println("Fila vazia!");
                return;
            }
            
            System.out.print("Fila: ");
            No atual = inicio;
            while (atual != null) {
                System.out.print(atual.valor + " ");
                atual = atual.proximo;
            }
            System.out.println();
        }
        
        public boolean estaOrdenada() {
            if (inicio == null || inicio.proximo == null) {
                return true;
            }
            
            No atual = inicio;
            while (atual.proximo != null) {
                if (atual.valor > atual.proximo.valor) {
                    return false;
                }
                atual = atual.proximo;
            }
            return true;
        }
        
        public void preencherFila(Scanner scanner, String nomeFila) {
            System.out.println("\nPreenchendo a " + nomeFila + " (valores devem estar ordenados)");
            System.out.print("Quantos elementos deseja inserir? ");
            int n = scanner.nextInt();
            
            int anterior = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                System.out.print("Digite o valor " + (i+1) + " (deve ser >= " + anterior + "): ");
                int valor = scanner.nextInt();
                
                if (valor < anterior) {
                    System.out.println("Erro: Os valores devem estar em ordem crescente!");
                    i--;
                    continue;
                }
                
                this.insere(valor);
                anterior = valor;
            }
            
            System.out.print(nomeFila + " preenchida: ");
            this.imprime();
        }
    }
    
    private static Fila copiarFila(Fila original) {
        Fila copia = new Fila();
        Fila temp = new Fila();
        
        int elemento;
        while ((elemento = original.remove()) != -1) {
            temp.insere(elemento);
        }
        
        while ((elemento = temp.remove()) != -1) {
            original.insere(elemento);
            copia.insere(elemento); 
        }
        
        return copia;
    }
    
    public static Fila merge(Fila A, Fila B) {
        Fila C = new Fila();
        
        Fila copiaA = copiarFila(A);
        Fila copiaB = copiarFila(B);
        
        int elemA = copiaA.remove();
        int elemB = copiaB.remove();
        
        while (elemA != -1 && elemB != -1) {
            if (elemA <= elemB) {
                C.insere(elemA);
                elemA = copiaA.remove();
            } else {
                C.insere(elemB);
                elemB = copiaB.remove();
            }
        }
        
        while (elemA != -1) {
            C.insere(elemA);
            elemA = copiaA.remove();
        }
        
        while (elemB != -1) {
            C.insere(elemB);
            elemB = copiaB.remove();
        }
        
        return C;
    }
    
    public static void main(String[] args) {
        Fila A = new Fila();
        Fila B = new Fila();
        Fila C = null;
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        do {
            System.out.println("\n=== MENU MERGE DE FILAS ===");
            System.out.println("1 - Preencher Fila A");
            System.out.println("2 - Preencher Fila B");
            System.out.println("3 - Realizar Merge (A + B → C)");
            System.out.println("4 - Mostrar Fila A");
            System.out.println("5 - Mostrar Fila B");
            System.out.println("6 - Mostrar Fila C (resultado)");
            System.out.println("7 - Verificar se filas estao ordenadas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");
            
            opcao = scanner.nextInt();
            
            switch(opcao) {
                case 1:
                    A.preencherFila(scanner, "Fila A");
                    break;
                case 2:
                    B.preencherFila(scanner, "Fila B");
                    break;
                case 3:
                    if (A.vazia() || B.vazia()) {
                        System.out.println("Erro: Ambas as filas devem estar preenchidas!");
                    } else if (!A.estaOrdenada() || !B.estaOrdenada()) {
                        System.out.println("Erro: As filas devem estar ordenadas!");
                        System.out.println("Fila A ordenada: " + A.estaOrdenada());
                        System.out.println("Fila B ordenada: " + B.estaOrdenada());
                    } else {
                        C = merge(A, B);
                        System.out.println("Merge realizado com sucesso!");
                    }
                    break;
                case 4:
                    System.out.print("Fila A: ");
                    A.imprime();
                    System.out.println("Ordenada: " + A.estaOrdenada());
                    break;
                case 5:
                    System.out.print("Fila B: ");
                    B.imprime();
                    System.out.println("Ordenada: " + B.estaOrdenada());
                    break;
                case 6:
                    if (C == null) {
                        System.out.println("Execute primeiro o merge para gerar a Fila C.");
                    } else {
                        System.out.print("Fila C (resultado do merge): ");
                        C.imprime();
                    }
                    break;
                case 7:
                    System.out.println("Fila A ordenada: " + A.estaOrdenada());
                    System.out.println("Fila B ordenada: " + B.estaOrdenada());
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