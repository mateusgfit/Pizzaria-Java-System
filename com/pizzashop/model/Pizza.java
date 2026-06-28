package com.pizzashop.model;

public class Pizza {
    // Declaração das variáveis da classe (atributos)
    private final String size;
    private final boolean cheese;
    private final boolean pepperoni;
    private final boolean bacon;

    // Construtor privado: obriga o uso do Builder para criar o objeto
    private Pizza(Builder builder) {
        // Validação: verifica se o tamanho não é nulo ou vazio antes de criar
        if (builder.size == null || builder.size.trim().isEmpty()) {
            throw new IllegalArgumentException("O tamanho da pizza é obrigatório!");
        }
        this.size = builder.size;
        this.cheese = builder.cheese;
        this.pepperoni = builder.pepperoni;
        this.bacon = builder.bacon;
    }

    // Método para calcular o valor total, aplicando regras de negócio
    public double calcularPreco() {
        double preco = 20.0; // Preço inicial base
        if (cheese) preco += 5.0; // Soma adicional se tiver queijo
        if (pepperoni) preco += 7.0; // Soma adicional se tiver pepperoni
        if (bacon) preco += 8.0; // Soma adicional se tiver bacon

        // Aplica 10% de desconto se a pizza for Grande
        if ("Grande".equalsIgnoreCase(size)) {
            preco = preco * 0.9;
        }
        return preco;
    }

    // Exibe o status da pizza sendo preparada
    public void assar() {
        System.out.println("Assando uma pizza " + this.size + "...");
        if (cheese) System.out.println("- Adicionando queijo.");
        if (pepperoni) System.out.println("- Adicionando pepperoni.");
        if (bacon) System.out.println("- Adicionando bacon.");
        // Formata o preço com duas casas decimais
        System.out.println("Pizza pronta! Valor total: R$" + String.format("%.2f", calcularPreco()));
    }

    // Sobrescreve o método padrão para imprimir a pizza como texto legível
    @Override
    public String toString() {
        return "Pizza [Tamanho=" + size + ", Queijo=" + cheese + ", Pepperoni=" + pepperoni + ", Bacon=" + bacon + "]";
    }

    // Classe estática interna: implementa o padrão Builder para construção flexível
    public static class Builder {
        private final String size;
        private boolean cheese = false;
        private boolean pepperoni = false;
        private boolean bacon = false;

        // Construtor do Builder exige o tamanho
        public Builder(String size) { this.size = size; }
        // Métodos que alteram o status dos ingredientes e retornam o próprio builder para encadeamento
        public Builder addCheese() { this.cheese = true; return this; }
        public Builder addPepperoni() { this.pepperoni = true; return this; }
        public Builder addBacon() { this.bacon = true; return this; }
        // Método que finaliza a construção e cria o objeto Pizza
        public Pizza build() { return new Pizza(this); }
    }
}