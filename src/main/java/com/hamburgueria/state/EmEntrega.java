package com.hamburgueria.state;

import com.hamburgueria.domain.Pedido;

public class EmEntrega implements EstadoPedido {
    @Override
    public void processarPedido(Pedido pedido) {
        pedido.setEstado(new Entregue());
    }

    @Override
    public void cancelarPedido(Pedido pedido) {
        pedido.setEstado(new Cancelado());
    }
}
