package com.hamburgueria.observer;

public class ClienteObserver implements Observer {
    @Override
    public void atualizar(String mensagem) {
        System.out.println("Cliente recebeu atualização: " + mensagem);
    }
}
