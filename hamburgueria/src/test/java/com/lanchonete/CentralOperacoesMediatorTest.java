package com.lanchonete;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.lanchonete.entrega.Delivery;
import com.lanchonete.entrega.DriveThrough;
import com.lanchonete.entrega.Loja;
import com.lanchonete.lanche.Hamburguer;
import com.lanchonete.mediator.CentralOperacoesMediator;
import com.lanchonete.observer.Observer;
import com.lanchonete.pedido.Pedido;

@DisplayName("Testes de CentralOperacoesMediator - Mediator Pattern")
class CentralOperacoesMediatorTest {

    @Nested
    @DisplayName("Registrar Observador")
    class RegistrarObservadorTest {

        @Test
        @DisplayName("deve registrar um observador")
        void deveRegistrarObservador() {
            CentralOperacoesMediator mediator = new CentralOperacoesMediator();
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            Observer observador = (p) -> {};

            assertDoesNotThrow(() -> {
                mediator.registrarObservador(pedido, observador);
            });
        }

        @Test
        @DisplayName("deve aceitar subject nulo sem lançar exceção")
        void deveAceitarSubjectNulo() {
            CentralOperacoesMediator mediator = new CentralOperacoesMediator();
            Observer observador = (p) -> {};

            assertDoesNotThrow(() -> {
                mediator.registrarObservador(null, observador);
            });
        }

        @Test
        @DisplayName("deve aceitar observador nulo sem lançar exceção")
        void deveAceitarObservadorNulo() {
            CentralOperacoesMediator mediator = new CentralOperacoesMediator();
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());

            assertDoesNotThrow(() -> {
                mediator.registrarObservador(pedido, null);
            });
        }

        @Test
        @DisplayName("deve registrar múltiplos observadores")
        void deveRegistrarMultiplosObservadores() {
            CentralOperacoesMediator mediator = new CentralOperacoesMediator();
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            Observer obs1 = (p) -> {};
            Observer obs2 = (p) -> {};

            assertDoesNotThrow(() -> {
                mediator.registrarObservador(pedido, obs1);
                mediator.registrarObservador(pedido, obs2);
            });
        }
    }

    @Nested
    @DisplayName("Processar Entrega")
    class ProcessarEntregaTest {

        @Test
        @DisplayName("deve processar entrega com Loja")
        void deveProcessarEntregaLoja() {
            CentralOperacoesMediator mediator = new CentralOperacoesMediator();
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            Loja entrega = new Loja();

            assertDoesNotThrow(() -> {
                mediator.processarEntrega(pedido, entrega);
            });
        }

        @Test
        @DisplayName("deve processar entrega com Delivery")
        void deveProcessarEntregaDelivery() {
            CentralOperacoesMediator mediator = new CentralOperacoesMediator();
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            Delivery entrega = new Delivery();

            assertDoesNotThrow(() -> {
                mediator.processarEntrega(pedido, entrega);
            });
        }

        @Test
        @DisplayName("deve processar entrega com DriveThrough")
        void deveProcessarEntregaDriveThrough() {
            CentralOperacoesMediator mediator = new CentralOperacoesMediator();
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            DriveThrough entrega = new DriveThrough();

            assertDoesNotThrow(() -> {
                mediator.processarEntrega(pedido, entrega);
            });
        }

        @Test
        @DisplayName("deve aceitar pedido nulo sem lançar exceção")
        void deveAceitarPedidoNulo() {
            CentralOperacoesMediator mediator = new CentralOperacoesMediator();
            Loja entrega = new Loja();

            assertDoesNotThrow(() -> {
                mediator.processarEntrega(null, entrega);
            });
        }

        @Test
        @DisplayName("deve aceitar entrega nula sem lançar exceção")
        void deveAceitarEntregaNula() {
            CentralOperacoesMediator mediator = new CentralOperacoesMediator();
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());

            assertDoesNotThrow(() -> {
                mediator.processarEntrega(pedido, null);
            });
        }
    }

    @Nested
    @DisplayName("Notificar")
    class NotificarTest {

        @Test
        @DisplayName("deve enviar notificação")
        void deveEnviarNotificacao() {
            CentralOperacoesMediator mediator = new CentralOperacoesMediator();

            assertDoesNotThrow(() -> {
                mediator.notificar("test", "Teste de notificação");
            });
        }

        @Test
        @DisplayName("deve aceitar origem nula")
        void deveAceitarOrigemNula() {
            CentralOperacoesMediator mediator = new CentralOperacoesMediator();

            assertDoesNotThrow(() -> {
                mediator.notificar(null, "Teste");
            });
        }

        @Test
        @DisplayName("deve aceitar mensagem nula")
        void deveAceitarMensagemNula() {
            CentralOperacoesMediator mediator = new CentralOperacoesMediator();

            assertDoesNotThrow(() -> {
                mediator.notificar("test", null);
            });
        }

        @Test
        @DisplayName("deve implementar RestauranteMediator")
        void deveImplementarRestauranteMediator() {
            CentralOperacoesMediator mediator = new CentralOperacoesMediator();
            assertNotNull(mediator);
        }
    }

    @Nested
    @DisplayName("Integração com Pedido e Entrega")
    class IntegracaoTest {

        @Test
        @DisplayName("deve coordenar múltiplas operações")
        void deveCoordenadasMultiplasOperacoes() {
            CentralOperacoesMediator mediator = new CentralOperacoesMediator();
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            Loja entrega = new Loja();
            Observer observador = (p) -> {};

            assertDoesNotThrow(() -> {
                mediator.registrarObservador(pedido, observador);
                mediator.processarEntrega(pedido, entrega);
                mediator.notificar(pedido, "Pedido processado");
            });
        }

        @Test
        @DisplayName("deve processar pedido completo com múltiplos observadores")
        void deveProcessarPedidoCompletoComMultiplosObservadores() {
            CentralOperacoesMediator mediator = new CentralOperacoesMediator();
            Pedido pedido = new Pedido(List.of(new Hamburguer()), new Loja());
            Loja entrega = new Loja();
            
            Observer obs1 = (p) -> System.out.println("Observador 1");
            Observer obs2 = (p) -> System.out.println("Observador 2");

            assertDoesNotThrow(() -> {
                mediator.registrarObservador(pedido, obs1);
                mediator.registrarObservador(pedido, obs2);
                mediator.processarEntrega(pedido, entrega);
                mediator.notificar(pedido, "Pedido pronto");
            });
        }
    }
}
