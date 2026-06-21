package com.hamburgueria.observer;

public class CozinhaObserver implements Observer {
    @Override
    public void atualizar(String mensagem) {
        System.out.println("Cozinha recebeu atualização: " + mensagem);
    }
}
