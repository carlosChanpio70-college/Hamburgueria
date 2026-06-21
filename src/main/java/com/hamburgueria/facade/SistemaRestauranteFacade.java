package com.hamburgueria.facade;

import com.hamburgueria.builder.PedidoBuilder;
import com.hamburgueria.builder.PedidoConcretoBuilder;
import com.hamburgueria.domain.Cliente;
import com.hamburgueria.domain.Lanche;
import com.hamburgueria.domain.Pedido;
import com.hamburgueria.state.Preparando;

public class SistemaRestauranteFacade {
    public Pedido criarPedido(Cliente cliente, String endereco) {
        PedidoBuilder builder = new PedidoConcretoBuilder();
        return builder.definirCliente(cliente)
                .definirEndereco(endereco)
                .build();
    }

    public void adicionarItem(Pedido pedido, Lanche lanche) {
        pedido.adicionarLanche(lanche);
        pedido.calcularTotal();
    }

    public void finalizarPedido(Pedido pedido) {
        pedido.alterarEstado(new Preparando());
        pedido.calcularTotal();
        pedido.notificarObservers();
    }

    public String acompanharPedido(Pedido pedido) {
        return pedido.getEstado().getClass().getSimpleName();
    }
}
