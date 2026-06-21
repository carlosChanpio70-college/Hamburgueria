package com.hamburgueria.decorator;

public class ExtraMolho extends AdicionalDecorator {
    public ExtraMolho(com.hamburgueria.domain.Lanche lanche) {
        super(lanche);
    }

    @Override
    public String getDescricao() {
        return lanche.getDescricao() + " + Extra Molho";
    }

    @Override
    public double getPreco() {
        return lanche.getPreco() + 1.75;
    }
}
