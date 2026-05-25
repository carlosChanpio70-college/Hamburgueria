package com.lanchonete.mediator;

import com.lanchonete.entrega.Entrega;
import com.lanchonete.observer.Observer;
import com.lanchonete.observer.PedidoSubject;
import com.lanchonete.pedido.Pedido;

/**
 * Mediador que centraliza operações entre pedidos, observadores e entregas.
 */
public class CentralOperacoesMediator implements RestauranteMediator {

    /**
     * Registra um observador para um subject.
     * @param subject o subject para registrar o observador
     * @param observador o observador a ser registrado
     */
    public void registrarObservador(PedidoSubject subject, Observer observador) {
        if (subject == null || observador == null) {
            return;
        }
        subject.attach(observador);
    }

    /**
     * Processa a entrega de um pedido.
     * @param pedido o pedido a ser entregue
     * @param entrega a estratégia de entrega
     */
    public void processarEntrega(Pedido pedido, Entrega entrega) {
        if (pedido == null || entrega == null) {
            return;
        }
        entrega.processar(pedido);
    }

    /**
     * Notifica sobre uma mensagem de operação.
     * @param origem a origem da notificação
     * @param mensagem a mensagem a ser notificada
     */
    @Override
    public void notificar(Object origem, String mensagem) {
        if (origem == null || mensagem == null) {
            return;
        }
        System.out.printf("[MEDIADOR] %s%n", mensagem);
    }
}
