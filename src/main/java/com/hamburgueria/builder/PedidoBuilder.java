package com.hamburgueria.builder;

import com.hamburgueria.domain.Cliente;
import com.hamburgueria.domain.Pedido;
import com.hamburgueria.domain.Lanche;

public interface PedidoBuilder {
    PedidoBuilder adicionarLanche(Lanche lanche);
    PedidoBuilder definirEndereco(String endereco);
    PedidoBuilder definirCliente(Cliente cliente);
    Pedido build();
}
