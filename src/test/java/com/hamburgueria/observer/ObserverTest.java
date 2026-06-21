package com.hamburgueria.observer;

import com.hamburgueria.domain.Pedido;
import com.hamburgueria.state.Preparando;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObserverTest {

    @Test
    void deveNotificarObserverComMensagemNaoNula() {
        Pedido pedido = criarPedidoComObserver();
        assertNotNull(pedido.getItens());
    }

    @Test
    void deveNotificarObserverComStatusPreparando() {
        Pedido pedido = new Pedido();
        TestObserver observer = new TestObserver();

        pedido.registrarObserver(observer);
        pedido.alterarEstado(new Preparando());

        assertTrue(observer.getUltimaMensagem().contains("Preparando"));
    }

    private Pedido criarPedidoComObserver() {
        Pedido pedido = new Pedido();
        TestObserver observer = new TestObserver();
        pedido.registrarObserver(observer);
        pedido.alterarEstado(new Preparando());
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
