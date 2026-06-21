package com.hamburgueria.decorator;

public class ExtraQueijo extends AdicionalDecorator {
    public ExtraQueijo(com.hamburgueria.domain.Lanche lanche) {
        super(lanche);
    }

    @Override
    public String getDescricao() {
        return lanche.getDescricao() + " + Extra Queijo";
    }

    @Override
    public double getPreco() {
        return lanche.getPreco() + 2.50;
    }
}
