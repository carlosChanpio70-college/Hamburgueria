package com.hamburgueria.builder;

import com.hamburgueria.domain.Cliente;
import com.hamburgueria.domain.Hamburguer;
import com.hamburgueria.domain.Pedido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoBuilderTest {

    @Test
    void deveConstruirPedidoValido() {
        Pedido pedido = criarPedidoComBuilder();
        assertNotNull(pedido);
    }

    @Test
    void deveAssociarClienteAoPedido() {
        Pedido pedido = criarPedidoComBuilder();
        assertEquals(new Cliente(1L, "Ana", "97777-8888", "Rua 2"), pedido.getCliente());
    }

    @Test
    void deveDefinirEnderecoNoPedido() {
        Pedido pedido = criarPedidoComBuilder();
        assertEquals("Rua 2", pedido.getEndereco());
    }

    @Test
    void deveAdicionarLancheAoPedido() {
        Pedido pedido = criarPedidoComBuilder();
        assertEquals(1, pedido.getItens().size());
    }

    @Test
    void deveCalcularTotalDoPedido() {
        Pedido pedido = criarPedidoComBuilder();
        assertEquals(20.0, pedido.getValorTotal(), 0.001);
    }

    private Pedido criarPedidoComBuilder() {
        PedidoBuilder builder = new PedidoConcretoBuilder();
        Cliente cliente = new Cliente(1L, "Ana", "97777-8888", "Rua 2");
        Hamburguer hamburguer = new Hamburguer("X-Frango", 20.0);

        return builder.definirCliente(cliente)
                .definirEndereco("Rua 2")
                .adicionarLanche(hamburguer)
                .build();
    }
}
