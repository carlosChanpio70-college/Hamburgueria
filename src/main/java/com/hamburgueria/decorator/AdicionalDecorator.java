package com.hamburgueria.decorator;

import com.hamburgueria.domain.Lanche;

public abstract class AdicionalDecorator implements Lanche {
    protected final Lanche lanche;

    protected AdicionalDecorator(Lanche lanche) {
        this.lanche = lanche;
    }
}
