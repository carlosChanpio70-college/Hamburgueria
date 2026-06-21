package com.hamburgueria.state;

import com.hamburgueria.domain.Pedido;

public interface EstadoPedido {
    void processarPedido(Pedido pedido);
    void cancelarPedido(Pedido pedido);
}
