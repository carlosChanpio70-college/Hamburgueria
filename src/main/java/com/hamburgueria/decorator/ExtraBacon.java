package com.hamburgueria.decorator;

public class ExtraBacon extends AdicionalDecorator {
    public ExtraBacon(com.hamburgueria.domain.Lanche lanche) {
        super(lanche);
    }

    @Override
    public String getDescricao() {
        return lanche.getDescricao() + " + Extra Bacon";
    }

    @Override
    public double getPreco() {
        return lanche.getPreco() + 3.50;
    }
}
