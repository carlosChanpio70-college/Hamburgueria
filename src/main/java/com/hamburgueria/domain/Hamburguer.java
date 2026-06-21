package com.hamburgueria.domain;

public class Hamburguer implements Lanche {
    private final String nome;
    private final double precoBase;

    public Hamburguer(String nome, double precoBase) {
        this.nome = nome;
        this.precoBase = precoBase;
    }

    @Override
    public String getDescricao() {
        return nome;
    }

    @Override
    public double getPreco() {
        return precoBase;
    }
}
