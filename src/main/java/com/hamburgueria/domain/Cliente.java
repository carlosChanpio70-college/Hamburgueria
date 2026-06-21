package com.hamburgueria.domain;

import java.util.Objects;

public class Cliente {
    private Long id;
    private String nome;
    private String telefone;
    private String endereco;

    public Cliente(Long id, String nome, String telefone, String endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public void realizarPedido(Pedido pedido) {
        pedido.setCliente(this);
        pedido.setEndereco(this.endereco);
    }

    public Pedido consultarPedido(Pedido pedido) {
        return pedido;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
