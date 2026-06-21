package com.hamburgueria.domain;

import com.hamburgueria.decorator.ExtraBacon;
import com.hamburgueria.decorator.ExtraMolho;
import com.hamburgueria.decorator.ExtraQueijo;
import com.hamburgueria.observer.Observer;
import com.hamburgueria.state.Preparando;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    void deveCalcularTotalComLanchesEDecoradores() {
        Pedido pedido = criarPedidoComLancheCheio();
        assertEquals(22.75, pedido.getValorTotal(), 0.001);
    }

    @Test
    void deveTerUmItemNoPedido() {
        Pedido pedido = criarPedidoComLancheCheio();
        assertEquals(1, pedido.getItens().size());
    }

    @Test
    void deveDescreverLancheComExtraQueijo() {
        Pedido pedido = criarPedidoComLancheCheio();
        assertTrue(pedido.getItens().get(0).getDescricao().contains("Extra Queijo"));
    }

    @Test
    void deveMudarParaPreparandoAoAlterarEstado() {
        Pedido pedido = new Pedido();
        Cliente cliente = new Cliente(1L, "Maria", "99999-0000", "Rua 1");
        pedido.setCliente(cliente);
        pedido.setEndereco("Rua 1");

        TestObserver observer = new TestObserver();
        pedido.registrarObserver(observer);

        pedido.alterarEstado(new Preparando());
        assertEquals("Preparando", pedido.getEstado().getClass().getSimpleName());
    }

    @Test
    void deveNotificarObservadorQuandoPedidoEntrarEmPreparando() {
        Pedido pedido = new Pedido();
        Cliente cliente = new Cliente(1L, "Maria", "99999-0000", "Rua 1");
        pedido.setCliente(cliente);
        pedido.setEndereco("Rua 1");

        TestObserver observer = new TestObserver();
        pedido.registrarObserver(observer);

        pedido.alterarEstado(new Preparando());
        assertTrue(observer.getUltimaMensagem().contains("Preparando"));
    }

    @Test
    void deveMudarParaEmEntregaAoProcessarPedido() {
        Pedido pedido = new Pedido();
        pedido.alterarEstado(new Preparando());

        pedido.processarPedido();
        assertEquals("EmEntrega", pedido.getEstado().getClass().getSimpleName());
    }

    @Test
    void deveCancelarPedido() {
        Pedido pedido = new Pedido();
        pedido.alterarEstado(new Preparando());
        pedido.cancelarPedido();

        assertEquals("Cancelado", pedido.getEstado().getClass().getSimpleName());
    }

    private Pedido criarPedidoComLancheCheio() {
        Hamburguer hamburguer = new Hamburguer("X-Burguer", 15.0);
        Lanche lancheCheio = new ExtraMolho(new ExtraBacon(new ExtraQueijo(hamburguer)));

        Pedido pedido = new Pedido();
        pedido.adicionarLanche(lancheCheio);
        return pedido;
    }

    private static class TestObserver implements Observer {
        private String ultimaMensagem;

        @Override
        public void atualizar(String mensagem) {
            this.ultimaMensagem = mensagem;
        }

        public String getUltimaMensagem() {
            return ultimaMensagem;
        }
    }
}
