package com.pizzasho;

import com.pizzashop.model.Pizza;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- SISTEMA DE GERENCIAMENTO DE PIZZARIA ---");
        System.out.println("1 - Pedido | 2 - Faturamento | 3 - Listar Pedidos | 4 - Limpar Histórico");
        System.out.print("Escolha uma opção: ");

        try {
            int opcao = scanner.nextInt(); // Lê a escolha do usuário
            scanner.nextLine(); // Consome a quebra de linha após o número

            // Direciona para a funcionalidade escolhida
            switch(opcao) {
                case 1: processarPedidos(scanner); break;
                case 2: RelatorioService.exibirFaturamentoTotal(); break;
                case 3: RelatorioService.listarPedidos(); break;
                case 4: RelatorioService.limparHistorico(); break;
                default: System.out.println("Opção inválida.");
            }
        } catch (InputMismatchException e) {
            // Trata caso o usuário digite texto em vez de número
            RelatorioService.logError("Entrada inválida no menu.");
            System.out.println("Erro: Digite apenas números!");
        }
        scanner.close(); // Fecha o Scanner para liberar a memória
    }

    // Método que gerencia a criação dos pedidos
    private static void processarPedidos(Scanner scanner) {
        List<Pizza> pedidos = new ArrayList<>();
        String continuar = "s";

        // Loop que permite criar vários pedidos em sequência
        while (continuar.equalsIgnoreCase("s")) {
            System.out.print("Tamanho (Grande/Média): ");
            String tamanho = scanner.nextLine();

            try {
                // Usa o Builder para criar a pizza
                Pizza.Builder builder = new Pizza.Builder(tamanho);
                System.out.print("Queijo? (s/n): ");
                if (scanner.nextLine().equalsIgnoreCase("s")) builder.addCheese();
                System.out.print("Pepperoni? (s/n): ");
                if (scanner.nextLine().equalsIgnoreCase("s")) builder.addPepperoni();
                System.out.print("Bacon? (s/n): ");
                if (scanner.nextLine().equalsIgnoreCase("s")) builder.addBacon();

                pedidos.add(builder.build()); // Adiciona a pizza pronta à lista
            } catch (IllegalArgumentException e) {
                System.out.println("Pizza inválida: " + e.getMessage());
            }

            System.out.print("Adicionar outro? (s/n): ");
            continuar = scanner.nextLine();
        }

        // Salva os pedidos processados no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pedidos.txt", true))) {
            for (Pizza p : pedidos) {
                p.assar(); // Executa o comportamento de assar
                writer.write(p.toString() + " | Valor: R$" + String.format("%.2f", p.calcularPreco()));
                writer.newLine(); // Pula linha no arquivo
            }
        } catch (IOException e) {
            RelatorioService.logError("Erro ao salvar pedido: " + e.getMessage());
        }
    }
}