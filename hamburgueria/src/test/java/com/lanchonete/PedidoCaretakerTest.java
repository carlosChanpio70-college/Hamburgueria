package com.lanchonete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.lanchonete.entrega.Loja;
import com.lanchonete.lanche.Hamburguer;
import com.lanchonete.pedido.Pedido;
import com.lanchonete.pedido.memento.PedidoCaretaker;
import com.lanchonete.pedido.memento.PedidoMomento;

@DisplayName("Testes de PedidoCaretaker - Memento Pattern")
class PedidoCaretakerTest {

    @Nested
    @DisplayName("Operações Básicas")
    class OperacoesBasicasTest {

        @Test
        @DisplayName("deve adicionar um momento")
        void deveAdicionarMomento() {
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            PedidoMomento momento = pedido.salvarMomento();
            PedidoCaretaker caretaker = new PedidoCaretaker();
            
            caretaker.adicionarMomento(momento);
            
            assertEquals(1, caretaker.getTotalMomentos());
        }

        @Test
        @DisplayName("deve lançar exceção ao adicionar momento nulo")
        void deveLancarExcecaoMomentoNulo() {
            PedidoCaretaker caretaker = new PedidoCaretaker();
            
            assertThrows(IllegalArgumentException.class, 
                () -> caretaker.adicionarMomento(null));
        }

        @Test
        @DisplayName("deve buscar um momento por índice")
        void deveBuscarMomentoPorIndice() {
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            PedidoMomento momento = pedido.salvarMomento();
            PedidoCaretaker caretaker = new PedidoCaretaker();
            
            caretaker.adicionarMomento(momento);
            PedidoMomento recuperado = caretaker.buscarMomento(0);
            
            assertNotNull(recuperado);
            assertEquals(momento.getTotal(), recuperado.getTotal(), 0.001);
        }

        @Test
        @DisplayName("deve lançar exceção ao buscar índice inválido")
        void deveLancarExcecaoIndiceInvalido() {
            PedidoCaretaker caretaker = new PedidoCaretaker();
            
            assertThrows(IndexOutOfBoundsException.class, 
                () -> caretaker.buscarMomento(0));
        }

        @Test
        @DisplayName("deve retornar lista imutável de momentos")
        void deveRetornarListaImutavel() {
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            PedidoMomento momento = pedido.salvarMomento();
            PedidoCaretaker caretaker = new PedidoCaretaker();
            
            caretaker.adicionarMomento(momento);
            List<PedidoMomento> momentos = caretaker.getMomentos();
            
            assertThrows(UnsupportedOperationException.class, 
                () -> momentos.add(pedido.salvarMomento()));
        }
    }

    @Nested
    @DisplayName("Novos Métodos Utilitários")
    class MetodosUtilitariosTest {

        @Test
        @DisplayName("getTotalMomentos deve retornar 0 inicialmente")
        void getTotalMomentosZeroInicialmente() {
            PedidoCaretaker caretaker = new PedidoCaretaker();
            assertEquals(0, caretaker.getTotalMomentos());
        }

        @Test
        @DisplayName("getTotalMomentos deve retornar a quantidade de momentos")
        void getTotalMomentosRetornaQuantidade() {
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            PedidoCaretaker caretaker = new PedidoCaretaker();
            
            caretaker.adicionarMomento(pedido.salvarMomento());
            caretaker.adicionarMomento(pedido.salvarMomento());
            caretaker.adicionarMomento(pedido.salvarMomento());
            
            assertEquals(3, caretaker.getTotalMomentos());
        }

        @Test
        @DisplayName("removerMomento deve remover um momento válido")
        void removerMomentoValido() {
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            PedidoCaretaker caretaker = new PedidoCaretaker();
            
            caretaker.adicionarMomento(pedido.salvarMomento());
            caretaker.adicionarMomento(pedido.salvarMomento());
            
            boolean removido = caretaker.removerMomento(0);
            
            assertTrue(removido);
            assertEquals(1, caretaker.getTotalMomentos());
        }

        @Test
        @DisplayName("removerMomento deve retornar false para índice inválido")
        void removerMomentoIndiceInvalido() {
            PedidoCaretaker caretaker = new PedidoCaretaker();
            
            boolean removido = caretaker.removerMomento(0);
            
            assertFalse(removido);
        }

        @Test
        @DisplayName("removerMomento deve retornar false para índice negativo")
        void removerMomentoIndiceNegativo() {
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            PedidoCaretaker caretaker = new PedidoCaretaker();
            caretaker.adicionarMomento(pedido.salvarMomento());
            
            boolean removido = caretaker.removerMomento(-1);
            
            assertFalse(removido);
        }

        @Test
        @DisplayName("limparMomentos deve remover todos os momentos")
        void limparMomentosRemoveTodos() {
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            PedidoCaretaker caretaker = new PedidoCaretaker();
            
            caretaker.adicionarMomento(pedido.salvarMomento());
            caretaker.adicionarMomento(pedido.salvarMomento());
            caretaker.adicionarMomento(pedido.salvarMomento());
            
            assertEquals(3, caretaker.getTotalMomentos());
            
            caretaker.limparMomentos();
            
            assertEquals(0, caretaker.getTotalMomentos());
        }

        @Test
        @DisplayName("limparMomentos deve deixar lista vazia para futuras adições")
        void limparMomentosPermiteNovasAdicoes() {
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            PedidoCaretaker caretaker = new PedidoCaretaker();
            
            caretaker.adicionarMomento(pedido.salvarMomento());
            caretaker.limparMomentos();
            caretaker.adicionarMomento(pedido.salvarMomento());
            
            assertEquals(1, caretaker.getTotalMomentos());
        }
    }

    @Nested
    @DisplayName("Cenários Complexos")
    class CenariosComplexosTest {

        @Test
        @DisplayName("deve gerenciar múltiplos estados de pedidos")
        void gerenciarMultiplosEstados() {
            Pedido pedido1 = new Pedido(List.of(new Hamburguer()), new Loja());
            Pedido pedido2 = new Pedido(List.of(new Hamburguer(), new Hamburguer()), new Loja());
            
            PedidoCaretaker caretaker = new PedidoCaretaker();
            
            caretaker.adicionarMomento(pedido1.salvarMomento());
            caretaker.adicionarMomento(pedido2.salvarMomento());
            
            assertEquals(2, caretaker.getTotalMomentos());
            assertEquals(15.00, caretaker.buscarMomento(0).getTotal(), 0.001);
            assertEquals(30.00, caretaker.buscarMomento(1).getTotal(), 0.001);
        }

        @Test
        @DisplayName("deve remover momento do meio da lista")
        void removerMomentoDaMeio() {
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            PedidoCaretaker caretaker = new PedidoCaretaker();
            
            caretaker.adicionarMomento(pedido.salvarMomento());
            caretaker.adicionarMomento(pedido.salvarMomento());
            caretaker.adicionarMomento(pedido.salvarMomento());
            
            caretaker.removerMomento(1);
            
            assertEquals(2, caretaker.getTotalMomentos());
        }

        @Test
        @DisplayName("deve remover momento do final da lista")
        void removerMomentoFinal() {
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            PedidoCaretaker caretaker = new PedidoCaretaker();
            
            caretaker.adicionarMomento(pedido.salvarMomento());
            caretaker.adicionarMomento(pedido.salvarMomento());
            caretaker.adicionarMomento(pedido.salvarMomento());
            
            caretaker.removerMomento(2);
            
            assertEquals(2, caretaker.getTotalMomentos());
        }

        @Test
        @DisplayName("deve adicionar após limpar e remover")
        void adicionarAposLimparERemover() {
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            PedidoCaretaker caretaker = new PedidoCaretaker();
            
            caretaker.adicionarMomento(pedido.salvarMomento());
            caretaker.removerMomento(0);
            caretaker.adicionarMomento(pedido.salvarMomento());
            
            assertEquals(1, caretaker.getTotalMomentos());
            assertNotNull(caretaker.buscarMomento(0));
        }
    }
}
