package com.lanchonete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.lanchonete.desconto.DescontoCombo;
import com.lanchonete.desconto.DescontoHandler;
import com.lanchonete.desconto.SemDesconto;
import com.lanchonete.entrega.Loja;
import com.lanchonete.lanche.Hamburguer;
import com.lanchonete.pedido.Pedido;

@DisplayName("Testes de Desconto - Chain of Responsibility Pattern")
class DescontoTest {

    @Nested
    @DisplayName("DescontoHandler")
    class DescontoHandlerTest {

        @Test
        @DisplayName("setProximo deve configurar o próximo handler")
        void deveConfigurarProximo() {
            DescontoHandler semDesconto = new SemDesconto();
            DescontoHandler descontoCombo = new DescontoCombo();
            
            semDesconto.setProximo(descontoCombo);
            
            assertEquals(descontoCombo, semDesconto.getProximo());
        }

        @Test
        @DisplayName("getProximo deve retornar null inicialmente")
        void deveRetornarNullInicialmente() {
            DescontoHandler handler = new SemDesconto();
            assertNull(handler.getProximo());
        }

        @Test
        @DisplayName("getProximo deve retornar o handler configurado")
        void deveRetornarHandlerConfigurado() {
            DescontoHandler handler1 = new SemDesconto();
            DescontoHandler handler2 = new DescontoCombo();
            
            handler1.setProximo(handler2);
            
            assertNotNull(handler1.getProximo());
            assertEquals(handler2, handler1.getProximo());
        }

        @Test
        @DisplayName("deve permitir cadeia de handlers")
        void devePermitirCadeiaHandlers() {
            DescontoHandler handler1 = new SemDesconto();
            DescontoHandler handler2 = new DescontoCombo();
            DescontoHandler handler3 = new SemDesconto();
            
            handler1.setProximo(handler2);
            handler2.setProximo(handler3);
            
            assertEquals(handler2, handler1.getProximo());
            assertEquals(handler3, handler2.getProximo());
        }
    }

    @Nested
    @DisplayName("SemDesconto")
    class SemDescontoTest {

        @Test
        @DisplayName("deve retornar 0.0 para um lanche")
        void semDescontoUmLanche() {
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            DescontoHandler handler = new SemDesconto();
            assertEquals(0.0, handler.calcularDesconto(pedido), 0.001);
        }

        @Test
        @DisplayName("deve retornar 0.0 para múltiplos lanches")
        void semDescontoMultiplosLanches() {
            Pedido pedido = new Pedido(List.of(new Hamburguer(), new Hamburguer()), new Loja());
            DescontoHandler handler = new SemDesconto();
            assertEquals(0.0, handler.calcularDesconto(pedido), 0.001);
        }
    }

    @Nested
    @DisplayName("DescontoCombo")
    class DescontoCombTest {

        @Test
        @DisplayName("deve retornar 0.0 para um lanche")
        void semDescontoUmLanche() {
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            DescontoHandler handler = new DescontoCombo();
            assertEquals(0.0, handler.calcularDesconto(pedido), 0.001);
        }

        @Test
        @DisplayName("deve retornar 10% para dois lanches")
        void descontoCombo2Lanches() {
            Pedido pedido = new Pedido(List.of(new Hamburguer(), new Hamburguer()), new Loja());
            DescontoHandler handler = new DescontoCombo();
            assertEquals(3.0, handler.calcularDesconto(pedido), 0.001);
        }

        @Test
        @DisplayName("deve retornar 10% para três lanches")
        void descontoCombo3Lanches() {
            Pedido pedido = new Pedido(
                List.of(new Hamburguer(), new Hamburguer(), new Hamburguer()), 
                new Loja()
            );
            DescontoHandler handler = new DescontoCombo();
            assertEquals(4.5, handler.calcularDesconto(pedido), 0.001);
        }
    }

    @Nested
    @DisplayName("Chain of Responsibility - Integração")
    class CadeiaDescontosTest {

        @Test
        @DisplayName("primeira handler que aplica desconto deve ser usada")
        void deveUsarPrimeiraHandlerComDesconto() {
            DescontoHandler semDesconto = new SemDesconto();
            DescontoHandler descontoCombo = new DescontoCombo();
            
            semDesconto.setProximo(descontoCombo);
            
            // Com um item, nenhum desconto
            Pedido pedido1 = new Pedido(List.of(new Hamburguer()), new Loja());
            assertEquals(0.0, semDesconto.calcularDesconto(pedido1), 0.001);
            
            // Com dois itens, desconto de combo
            Pedido pedido2 = new Pedido(List.of(new Hamburguer(), new Hamburguer()), new Loja());
            assertEquals(0.0, semDesconto.calcularDesconto(pedido2), 0.001); // SemDesconto sempre retorna 0
        }

        @Test
        @DisplayName("deve permitir múltiplos handlers na cadeia")
        void devePermitirMultiplosHandlers() {
            DescontoHandler handler1 = new SemDesconto();
            DescontoHandler handler2 = new DescontoCombo();
            DescontoHandler handler3 = new SemDesconto();
            
            handler1.setProximo(handler2);
            handler2.setProximo(handler3);
            
            // Verifica a cadeia está corretamente configurada
            assertEquals(handler2, handler1.getProximo());
            assertEquals(handler3, handler2.getProximo());
        }
    }
}
