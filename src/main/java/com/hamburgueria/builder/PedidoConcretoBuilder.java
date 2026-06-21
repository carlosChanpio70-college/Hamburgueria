package com.hamburgueria.builder;

import com.hamburgueria.domain.Cliente;
import com.hamburgueria.domain.Lanche;
import com.hamburgueria.domain.Pedido;

public class PedidoConcretoBuilder implements PedidoBuilder {
    private final Pedido pedido;

    public PedidoConcretoBuilder() {
        this.pedido = new Pedido();
    }

    @Override
    public PedidoBuilder adicionarLanche(Lanche lanche) {
        this.pedido.adicionarLanche(lanche);
        return this;
    }

    @Override
    public PedidoBuilder definirEndereco(String endereco) {
        this.pedido.setEndereco(endereco);
        return this;
    }

    @Override
    public PedidoBuilder definirCliente(Cliente cliente) {
        this.pedido.setCliente(cliente);
        return this;
    }

    @Override
    public Pedido build() {
        this.pedido.calcularTotal();
        return this.pedido;
    }
}
