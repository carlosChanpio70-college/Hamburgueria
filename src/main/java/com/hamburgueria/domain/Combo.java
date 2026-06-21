package com.hamburgueria.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Combo implements Lanche {
    private final String nome;
    private final List<Lanche> items = new ArrayList<>();

    public Combo(String nome) {
        this.nome = nome;
    }

    public void adicionarItem(Lanche lanche) {
        items.add(lanche);
    }

    public void removerItem(Lanche lanche) {
        items.remove(lanche);
    }

    public List<Lanche> getItems() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public String getDescricao() {
        return nome;
    }

    @Override
    public double getPreco() {
        return items.stream().mapToDouble(Lanche::getPreco).sum();
    }
}
