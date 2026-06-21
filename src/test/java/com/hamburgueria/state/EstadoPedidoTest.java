package com.hamburgueria.state;

import com.hamburgueria.domain.Pedido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadoPedidoTest {

    @Test
    void devePassarParaEmEntregaAoProcessarPedido() {
        Pedido pedido = new Pedido();
        pedido.alterarEstado(new Preparando());

        pedido.processarPedido();

        assertEquals("EmEntrega", pedido.getEstado().getClass().getSimpleName());
    }

    @Test
    void devePassarParaEntregueAoProcessarPedidoNovamente() {
        Pedido pedido = new Pedido();
        pedido.alterarEstado(new Preparando());

        pedido.processarPedido();
        pedido.processarPedido();

        assertEquals("Entregue", pedido.getEstado().getClass().getSimpleName());
    }

    @Test
    void deveCancelarPedidoDePreparando() {
        Pedido pedido = new Pedido();
        pedido.alterarEstado(new Preparando());
        pedido.cancelarPedido();

        assertEquals("Cancelado", pedido.getEstado().getClass().getSimpleName());
    }
}
