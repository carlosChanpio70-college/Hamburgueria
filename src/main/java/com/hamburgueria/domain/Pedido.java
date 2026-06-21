package com.hamburgueria.domain;

import com.hamburgueria.observer.Observer;
import com.hamburgueria.state.Cancelado;
import com.hamburgueria.state.EmEntrega;
import com.hamburgueria.state.EstadoPedido;
import com.hamburgueria.state.Preparando;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    private Long id;
    private Date dataCriacao;
    private double valorTotal;
    private String endereco;
    private Cliente cliente;
    private EstadoPedido estado;
    private final List<Lanche> itens = new ArrayList<>();
    private final List<Observer> observers = new ArrayList<>();

    public Pedido() {
        this.dataCriacao = new Date();
        this.estado = new Preparando();
    }

    public void adicionarLanche(Lanche lanche) {
        if (lanche != null) {
            itens.add(lanche);
            calcularTotal();
        }
    }

    public void removerLanche(Lanche lanche) {
        itens.remove(lanche);
        calcularTotal();
    }

    public void calcularTotal() {
        this.valorTotal = itens.stream().mapToDouble(Lanche::getPreco).sum();
    }

    public void alterarEstado(EstadoPedido estado) {
        if (estado == null) {
            return;
        }
        this.estado = estado;
        notificarObservers();
    }

    public void registrarObserver(Observer observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    public void removerObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notificarObservers() {
        String mensagem = "Pedido " + (id != null ? id : "sem-id") + " mudou para " + estado.getClass().getSimpleName();
        for (Observer observer : observers) {
            observer.atualizar(mensagem);
        }
    }

    public void processarPedido() {
        if (!(estado instanceof Cancelado)) {
            estado.processarPedido(this);
        }
    }

    public void cancelarPedido() {
        if (!(estado instanceof Cancelado)) {
            estado.cancelarPedido(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public List<Lanche> getItens() {
        return new ArrayList<>(itens);
    }
}
