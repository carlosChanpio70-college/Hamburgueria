package com.hamburgueria.state;

import com.hamburgueria.domain.Pedido;

public class Cancelado implements EstadoPedido {
    @Override
    public void processarPedido(Pedido pedido) {
        // Pedido cancelado não pode ser processado.
    }

    @Override
    public void cancelarPedido(Pedido pedido) {
        // Pedido já cancelado.
    }
}
