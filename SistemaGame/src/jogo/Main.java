package jogo;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;


/*
 * SISTEMA DE GERENCIAMENTO DE JOGOS
 * 
 * INTEGRANTES DO GRUPO:
 * 1. RA 425100268 - GABRIEL DA SILVA BITENCOURT
 * 2. RA 925105727 - GABRIEL HALIM KWAPIS DE OLIVEIRA
 * 3. RA 925101671 - GUSTAVO OLIVEIRA SILVA
 * 4. RA 925100618 - INGRYD DE OLIVEIRA MARTINS
 * 5. RA 925108594 - THIAGO GOIS ANTIQUEIRA DA COSTA
 * 6. RA 924205252 - BUSNASSUM BUDA NHADA
 * 
 */

public class Main implements Serializable {
	private static final long serialVersionUID = 1L;
    private static ArrayList<Jogo> jogos = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<Venda> vendas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        
        System.out.println("===========================================");
        System.out.println("   SISTEMA DE GERENCIAMENTO DE JOGOS");
        System.out.println("===========================================\n");

        do {
            exibirMenu();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarJogo();
                    break;
                case 2:
                    cadastrarCliente();
                    break;
                case 3:
                    editarJogo();
                    break;
                case 4:
                    editarCliente();
                    break;
                case 5:
                    atualizarJogo();
                    break;
                case 6:
                    atualizarCliente();
                    break;
                case 7:
                    deletarJogo();
                    break;
                case 8:
                    deletarCliente();
                    break;
                case 9:
                    listarJogos();
                    break;
                case 10:
                    listarClientes();
                    break;
                case 11:
                    realizarVenda();
                    break;
                case 12:
                    exibirHistoricoCompras();
                    break;
                case 13:
                    System.out.println("\nEncerrando o sistema. Ate logo!");
                    break;
                default:
                    System.out.println("\nOpcao invalida! Tente novamente.");
            }
            
            if (opcao != 13) {
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }

        } while (opcao != 13);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n===========================================");
        System.out.println("                  MENU");
        System.out.println("===========================================");
        System.out.println("1  - Cadastrar Jogo");
        System.out.println("2  - Cadastrar Cliente");
        System.out.println("3  - Editar Jogo");
        System.out.println("4  - Editar Cliente");
        System.out.println("5  - Atualizar Jogo");
        System.out.println("6  - Atualizar Cliente");
        System.out.println("7  - Deletar Jogo");
        System.out.println("8  - Deletar Cliente");
        System.out.println("9  - Listar Jogos");
        System.out.println("10 - Listar Clientes");
        System.out.println("11 - Realizar Venda");
        System.out.println("12 - Exibir Historico de Compras");
        System.out.println("13 - Sair");
        System.out.println("===========================================");
        System.out.print("Escolha uma opcao: ");
    }

    private static int lerOpcao() {
        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            return opcao;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void cadastrarJogo() {
        System.out.println("\n--- CADASTRAR JOGO ---");
        try {
            System.out.print("Titulo: ");
            String titulo = scanner.nextLine();

            System.out.print("Genero: ");
            String genero = scanner.nextLine();

            System.out.print("Ano de Lancamento: ");
            int ano = Integer.parseInt(scanner.nextLine());

            System.out.print("Preco: R$ ");
            double preco = Double.parseDouble(scanner.nextLine());

            Jogo jogo = new Jogo(titulo, genero, ano, preco);
            jogos.add(jogo);
            System.out.println("\nJogo cadastrado com sucesso! ID: " + jogo.getId());
        } catch (Exception e) {
            System.out.println("\nErro ao cadastrar jogo: " + e.getMessage());
        }
    }

    private static void cadastrarCliente() {
        System.out.println("\n--- CADASTRAR CLIENTE ---");
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Saldo inicial: R$ ");
            double saldo = Double.parseDouble(scanner.nextLine());

            System.out.println("\nTipo de Cliente:");
            System.out.println("1 - Regular");
            System.out.println("2 - Premium (15% de desconto)");
            System.out.print("Escolha: ");
            int tipo = Integer.parseInt(scanner.nextLine());

            Cliente cliente;
            if (tipo == 2) {
                cliente = new ClientePremium(nome, email, saldo);
            } else {
                cliente = new ClienteRegular(nome, email, saldo);
            }

            clientes.add(cliente);
            System.out.println("\nCliente cadastrado com sucesso! ID: " + cliente.getIdUsuario());
        } catch (Exception e) {
            System.out.println("\nErro ao cadastrar cliente: " + e.getMessage());
        }
    }

    private static void editarJogo() {
        System.out.println("\n--- EDITAR JOGO ---");
        listarJogos();
        
        if (jogos.isEmpty()) return;

        try {
            System.out.print("\nDigite o ID do jogo a editar: ");
            int id = Integer.parseInt(scanner.nextLine());

            Jogo jogo = buscarJogoPorId(id);
            if (jogo == null) {
                System.out.println("Jogo nao encontrado!");
                return;
            }

            System.out.print("Novo Titulo (atual: " + jogo.getTitulo() + "): ");
            String titulo = scanner.nextLine();
            if (!titulo.isEmpty()) jogo.setTitulo(titulo);

            System.out.print("Novo Genero (atual: " + jogo.getGenero() + "): ");
            String genero = scanner.nextLine();
            if (!genero.isEmpty()) jogo.setGenero(genero);

            System.out.print("Novo Ano (atual: " + jogo.getAnoLancamento() + "): ");
            String anoStr = scanner.nextLine();
            if (!anoStr.isEmpty()) {
                jogo.setAnoLancamento(Integer.parseInt(anoStr));
            }

            System.out.print("Novo Preco (atual: R$" + jogo.getPreco() + "): ");
            String precoStr = scanner.nextLine();
            if (!precoStr.isEmpty()) {
                jogo.setPreco(Double.parseDouble(precoStr));
            }

            System.out.println("\nJogo editado com sucesso!");
        } catch (Exception e) {
            System.out.println("\nErro ao editar jogo: " + e.getMessage());
        }
    }

    private static void editarCliente() {
        System.out.println("\n--- EDITAR CLIENTE ---");
        listarClientes();
        
        if (clientes.isEmpty()) return;

        try {
            System.out.print("\nDigite o ID do cliente a editar: ");
            int id = Integer.parseInt(scanner.nextLine());

            Cliente cliente = buscarClientePorId(id);
            if (cliente == null) {
                System.out.println("Cliente nao encontrado!");
                return;
            }

            System.out.print("Novo Nome (atual: " + cliente.getNome() + "): ");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) cliente.setNome(nome);

            System.out.print("Novo Email (atual: " + cliente.getEmail() + "): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) cliente.setEmail(email);

            System.out.println("\nCliente editado com sucesso!");
        } catch (Exception e) {
            System.out.println("\nErro ao editar cliente: " + e.getMessage());
        }
    }

    private static void atualizarJogo() {
        System.out.println("\n--- ATUALIZAR STATUS DO JOGO ---");
        listarJogos();
        
        if (jogos.isEmpty()) return;

        try {
            System.out.print("\nDigite o ID do jogo: ");
            int id = Integer.parseInt(scanner.nextLine());

            Jogo jogo = buscarJogoPorId(id);
            if (jogo == null) {
                System.out.println("Jogo nao encontrado!");
                return;
            }

            System.out.println("\nStatus atual: " + (jogo.isDisponivel() ? "Disponivel" : "Indisponivel"));
            System.out.print("Alterar para (1-Disponivel / 2-Indisponivel): ");
            int status = Integer.parseInt(scanner.nextLine());

            jogo.setDisponivel(status == 1);
            System.out.println("\nStatus atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("\nErro ao atualizar jogo: " + e.getMessage());
        }
    }

    private static void atualizarCliente() {
        System.out.println("\n--- ATUALIZAR SALDO DO CLIENTE ---");
        listarClientes();
        
        if (clientes.isEmpty()) return;

        try {
            System.out.print("\nDigite o ID do cliente: ");
            int id = Integer.parseInt(scanner.nextLine());

            Cliente cliente = buscarClientePorId(id);
            if (cliente == null) {
                System.out.println("Cliente nao encontrado!");
                return;
            }

            System.out.println("\nSaldo atual: R$ " + String.format("%.2f", cliente.getSaldo()));
            System.out.print("Valor a adicionar: R$ ");
            double valor = Double.parseDouble(scanner.nextLine());

            cliente.adicionarSaldo(valor);
            System.out.println("\nSaldo atualizado! Novo saldo: R$ " + String.format("%.2f", cliente.getSaldo()));
        } catch (Exception e) {
            System.out.println("\nErro ao atualizar saldo: " + e.getMessage());
        }
    }

    private static void deletarJogo() {
        System.out.println("\n--- DELETAR JOGO ---");
        listarJogos();
        
        if (jogos.isEmpty()) return;

        try {
            System.out.print("\nDigite o ID do jogo a deletar: ");
            int id = Integer.parseInt(scanner.nextLine());

            Jogo jogo = buscarJogoPorId(id);
            if (jogo == null) {
                System.out.println("Jogo nao encontrado!");
                return;
            }

            System.out.print("Confirma exclusao do jogo '" + jogo.getTitulo() + "'? (S/N): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                jogos.remove(jogo);
                System.out.println("\nJogo deletado com sucesso!");
            } else {
                System.out.println("\nOperacao cancelada.");
            }
        } catch (Exception e) {
            System.out.println("\nErro ao deletar jogo: " + e.getMessage());
        }
    }

    private static void deletarCliente() {
        System.out.println("\n--- DELETAR CLIENTE ---");
        listarClientes();
        
        if (clientes.isEmpty()) return;

        try {
            System.out.print("\nDigite o ID do cliente a deletar: ");
            int id = Integer.parseInt(scanner.nextLine());

            Cliente cliente = buscarClientePorId(id);
            if (cliente == null) {
                System.out.println("Cliente nao encontrado!");
                return;
            }

            System.out.print("Confirma exclusao do cliente '" + cliente.getNome() + "'? (S/N): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                clientes.remove(cliente);
                System.out.println("\nCliente deletado com sucesso!");
            } else {
                System.out.println("\nOperacao cancelada.");
            }
        } catch (Exception e) {
            System.out.println("\nErro ao deletar cliente: " + e.getMessage());
        }
    }

    private static void listarJogos() {
        System.out.println("\n--- LISTA DE JOGOS ---");
        if (jogos.isEmpty()) {
            System.out.println("Nenhum jogo cadastrado.");
            return;
        }

        for (Jogo jogo : jogos) {
            System.out.println(jogo);
        }
    }

    private static void listarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    private static void realizarVenda() {
        System.out.println("\n--- REALIZAR VENDA ---");
        
        if (jogos.isEmpty() || clientes.isEmpty()) {
            System.out.println("E necessario ter jogos e clientes cadastrados!");
            return;
        }

        try {
            listarClientes();
            System.out.print("\nDigite o ID do cliente: ");
            int idCliente = Integer.parseInt(scanner.nextLine());

            Cliente cliente = buscarClientePorId(idCliente);
            if (cliente == null) {
                System.out.println("Cliente nao encontrado!");
                return;
            }

            listarJogos();
            System.out.print("\nDigite o ID do jogo: ");
            int idJogo = Integer.parseInt(scanner.nextLine());

            Jogo jogo = buscarJogoPorId(idJogo);
            if (jogo == null) {
                System.out.println("Jogo nao encontrado!");
                return;
            }

            if (!jogo.isDisponivel()) {
                System.out.println("Jogo indisponivel para venda!");
                return;
            }

            double precoOriginal = jogo.getPreco();
            double precoFinal = cliente.calcularDesconto(precoOriginal);

            System.out.println("\n--- RESUMO DA COMPRA ---");
            System.out.println("Cliente: " + cliente.getNome() + " (" + cliente.getTipoCliente() + ")");
            System.out.println("Jogo: " + jogo.getTitulo());
            System.out.println("Preco original: R$ " + String.format("%.2f", precoOriginal));
            
            if (precoFinal < precoOriginal) {
                System.out.println("Desconto aplicado: R$ " + String.format("%.2f", precoOriginal - precoFinal));
            }
            
            System.out.println("Preco final: R$ " + String.format("%.2f", precoFinal));
            System.out.println("Saldo atual: R$ " + String.format("%.2f", cliente.getSaldo()));

            System.out.print("\nConfirmar compra? (S/N): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                cliente.debitarSaldo(precoFinal);
                jogo.setDisponivel(false);
                
                Venda venda = new Venda(cliente, jogo, precoFinal);
                vendas.add(venda);

                System.out.println("\nVenda realizada com sucesso!");
                System.out.println("Novo saldo: R$ " + String.format("%.2f", cliente.getSaldo()));
            } else {
                System.out.println("\nVenda cancelada.");
            }
        } catch (Exception e) {
            System.out.println("\nErro ao realizar venda: " + e.getMessage());
        }
    }

    private static void exibirHistoricoCompras() {
        System.out.println("\n--- HISTORICO DE COMPRAS ---");
        
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda realizada ainda.");
            return;
        }

        listarClientes();
        System.out.print("\nDigite o ID do cliente (ou 0 para ver todas as vendas): ");
        
        try {
            int idCliente = Integer.parseInt(scanner.nextLine());

            if (idCliente == 0) {
                System.out.println("\n--- TODAS AS VENDAS ---");
                for (Venda venda : vendas) {
                    System.out.println(venda);
                }
            } else {
                Cliente cliente = buscarClientePorId(idCliente);
                if (cliente == null) {
                    System.out.println("Cliente nao encontrado!");
                    return;
                }

                System.out.println("\n--- COMPRAS DE " + cliente.getNome() + " ---");
                boolean encontrou = false;
                double total = 0;

                for (Venda venda : vendas) {
                    if (venda.getCliente().getIdUsuario() == idCliente) {
                        System.out.println(venda);
                        total += venda.getValorPago();
                        encontrou = true;
                    }
                }

                if (encontrou) {
                    System.out.println("\nTotal gasto: R$ " + String.format("%.2f", total));
                } else {
                    System.out.println("Este cliente ainda nao realizou compras.");
                }
            }
        } catch (Exception e) {
            System.out.println("\nErro ao exibir historico: " + e.getMessage());
        }
    }

    private static Jogo buscarJogoPorId(int id) {
        for (Jogo jogo : jogos) {
            if (jogo.getId() == id) {
                return jogo;
            }
        }
        return null;
    }

    private static Cliente buscarClientePorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdUsuario() == id) {
                return cliente;
            }
        }
        return null;
    }
}