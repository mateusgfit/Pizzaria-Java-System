package com.pizzasho;

import java.io.*;
import java.time.LocalDateTime;

public class RelatorioService {

    // Método que lê o arquivo e calcula a soma dos valores vendidos
    public static void exibirFaturamentoTotal() {
        double total = 0.0;
        // Tenta abrir o arquivo para leitura
        try (BufferedReader reader = new BufferedReader(new FileReader("pedidos.txt"))) {
            String linha;
            // Lê o arquivo linha por linha
            while ((linha = reader.readLine()) != null) {
                // Filtra apenas as linhas que contêm informações de preço
                if (linha.contains("Valor: R$")) {
                    String valorStr = linha.split("Valor: R\\$")[1]; // Extrai o número
                    total += Double.parseDouble(valorStr.replace(",", ".")); // Soma ao total
                }
            }
            System.out.printf("\nFaturamento total acumulado: R$ %.2f%n", total);
        } catch (IOException e) {
            logError("Erro ao ler relatórios: " + e.getMessage());
        }
    }

    // Método que imprime cada linha do arquivo de pedidos
    public static void listarPedidos() {
        System.out.println("\n--- LISTAGEM DE PEDIDOS ---");
        try (BufferedReader reader = new BufferedReader(new FileReader("pedidos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha); // Imprime a linha encontrada
            }
        } catch (IOException e) {
            logError("Erro ao listar pedidos: " + e.getMessage());
        }
    }

    // Limpa o conteúdo do arquivo de texto, resetando o histórico
    public static void limparHistorico() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pedidos.txt"))) {
            writer.write(""); // Sobrescreve com vazio
            System.out.println("Histórico de pedidos limpo!");
        } catch (IOException e) {
            logError("Erro ao limpar histórico: " + e.getMessage());
        }
    }

    // Centraliza o registro de falhas em um arquivo separado (log)
    public static void logError(String mensagem) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("error.log", true))) {
            // Registra a mensagem com data e hora atual
            writer.write(LocalDateTime.now() + " - " + mensagem);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Falha fatal ao gravar log de erro.");
        }
    }
}