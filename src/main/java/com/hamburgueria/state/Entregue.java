package com.hamburgueria.state;

import com.hamburgueria.domain.Pedido;

public class Entregue implements EstadoPedido {
    @Override
    public void processarPedido(Pedido pedido) {
        // Pedido já entregue.
    }

    @Override
    public void cancelarPedido(Pedido pedido) {
        // Não é possível cancelar após entrega.
    }
}
