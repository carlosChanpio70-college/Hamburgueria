package com.hamburgueria.facade;

import com.hamburgueria.domain.Cliente;
import com.hamburgueria.domain.Hamburguer;
import com.hamburgueria.domain.Pedido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SistemaRestauranteFacadeTest {

    @Test
    void deveCriarPedidoComClienteEEndereco() {
        SistemaRestauranteFacade facade = new SistemaRestauranteFacade();
        Cliente cliente = new Cliente(1L, "João", "98888-1111", "Av. Central");

        Pedido pedido = facade.criarPedido(cliente, "Av. Central");

        assertNotNull(pedido);
        assertEquals(cliente, pedido.getCliente());
        assertEquals("Av. Central", pedido.getEndereco());
    }

    @Test
    void deveAdicionarItemAoPedidoPeloFacade() {
        SistemaRestauranteFacade facade = new SistemaRestauranteFacade();
        Pedido pedido = criarPedidoBasico(facade);
        Hamburguer hamburguer = new Hamburguer("X-Salada", 18.0);

        facade.adicionarItem(pedido, hamburguer);

        assertEquals(18.0, pedido.getValorTotal(), 0.001);
    }

    @Test
    void deveFinalizarPedidoEacompanharSituacao() {
        SistemaRestauranteFacade facade = new SistemaRestauranteFacade();
        Pedido pedido = criarPedidoBasico(facade);

        facade.finalizarPedido(pedido);

        assertEquals("Preparando", pedido.getEstado().getClass().getSimpleName());
        assertEquals("Preparando", facade.acompanharPedido(pedido));
    }

    private Pedido criarPedidoBasico(SistemaRestauranteFacade facade) {
        Cliente cliente = new Cliente(1L, "João", "98888-1111", "Av. Central");
        return facade.criarPedido(cliente, "Av. Central");
    }
}
