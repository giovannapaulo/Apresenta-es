import java.util.Scanner;

public class GerenciadorLista {
    private No primeiro;
    private No ultimo;
    private Scanner usuario;


    public GerenciadorLista() {
        this.primeiro = null;
        this.ultimo = null;
        this.usuario = new Scanner(System.in);

    }

    public void adicionar(int valor) {
        No novoNo = new No(valor);

        if (this.primeiro == null) {
            this.primeiro = novoNo;
            this.ultimo = novoNo;
        } else {
            this.ultimo.proximo = novoNo;
            this.ultimo = novoNo; 
        }
        System.out.println("O valor " + valor + " foi adicionado com sucesso!");
    }

    public void adicionarNoInicio(int valor) {
        No novoNo = new No(valor);
        if (this.primeiro == null) {
            this.primeiro = novoNo;
            this.ultimo = novoNo;
        } else {
            novoNo.proximo = this.primeiro;
            this.primeiro = novoNo;
        }
        System.out.println("O valor " + valor + " foi adicionado no início com sucesso!");
    }

    public void adicionarNaPosicao(int valor, int posicao) {
        No novoNo = new No(valor);
    
        if (posicao < 0) {
            System.out.println("Posição inválida.");
            return;
        }
    
        if (posicao == 0 || this.primeiro == null) {
            novoNo.proximo = this.primeiro;
            this.primeiro = novoNo;
            if (this.ultimo == null) {
                this.ultimo = novoNo;
            }
            System.out.println("Valor " + valor + " inserido na posição " + posicao);
            return;
        }
    
        No atual = this.primeiro;
        int indice = 0;
    
        while (atual.proximo != null && indice < posicao - 1) {
            atual = atual.proximo;
            indice++;
        }
    
        novoNo.proximo = atual.proximo;
        atual.proximo = novoNo;
    
        if (novoNo.proximo == null) {
            this.ultimo = novoNo; 
        }
    
        System.out.println("Valor " + valor + " inserido na posição " + posicao);
    }
    

    public void remover(int valor) {
        if (this.primeiro == null) {
            System.out.println("A lista está vazia. Nada a remover.");
            return;
        }

        if (this.primeiro.valor == valor) {
            this.primeiro = this.primeiro.proximo;
            if (this.primeiro == null) {
                this.ultimo = null;
            }
            System.out.println("O valor " + valor + " foi removido com sucesso!");
            return;
        }

        No noAtual = this.primeiro;
        No noAnterior = null;

        while (noAtual != null && noAtual.valor != valor) {
            noAnterior = noAtual;
            noAtual = noAtual.proximo;
        }

        if (noAtual != null) {
            noAnterior.proximo = noAtual.proximo;
            if (noAtual == this.ultimo) {
                this.ultimo = noAnterior; 
            }
            System.out.println("O valor " + valor + " foi removido com sucesso!");
        } else {
            System.out.println("O valor " + valor + " não foi encontrado na lista.");
        }
    }

    public void exibirLista() {
        if (this.primeiro == null) {
            System.out.println("A lista está vazia.");
            return;
        }

        No noAtual = this.primeiro;
        System.out.println("\n--- Lista de Valores ---");
        while (noAtual != null) {
            System.out.println("- " + noAtual.valor);
            noAtual = noAtual.proximo;
        }
        System.out.println("------------------------\n");
    }

    public void iniciar() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Adicionar valor");
            System.out.println("2. Adicionar valor no inicio");
            System.out.println("3. Adicionar valor na posição desejada");
            System.out.println("4. Remover valor");
            System.out.println("5. Exibir lista");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = usuario.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o valor que deseja adicionar: ");
                        int valorAdicionar = usuario.nextInt();
                        adicionar(valorAdicionar);
                        break;
                    case 2:
                        System.out.print("Digite o valor a ser adicionado no início: ");
                        int valorAdicionarInicio = usuario.nextInt();
                        adicionarNoInicio(valorAdicionarInicio);
                        break;
                    case 3:
                        System.out.print("Digite o valor a ser adicionado: ");
                        int valorMeio = usuario.nextInt();
                        System.out.print("Digite a posição onde deseja inserir: ");
                        int posicao = usuario.nextInt();
                        adicionarNaPosicao(valorMeio, posicao);
                        break;
                    case 4:
                        System.out.print("Digite o valor que deseja remover: ");
                        int valorRemover = usuario.nextInt();
                        remover(valorRemover);
                        break;
                    case 5:
                        exibirLista();
                        break;
                    case 0:
                        System.out.println("Encerrando o programa...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                usuario.next();
                opcao = -1;
            }
        }
        usuario.close();
    }
}
