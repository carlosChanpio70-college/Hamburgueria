package com.hamburgueria.state;

import com.hamburgueria.domain.Pedido;

public class Preparando implements EstadoPedido {
    @Override
    public void processarPedido(Pedido pedido) {
        pedido.setEstado(new EmEntrega());
    }

    @Override
    public void cancelarPedido(Pedido pedido) {
        pedido.setEstado(new Cancelado());
    }
}
