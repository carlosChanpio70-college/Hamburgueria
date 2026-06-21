package com.hamburgueria.decorator;

import com.hamburgueria.domain.Hamburguer;
import com.hamburgueria.domain.Lanche;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LancheDecoratorTest {

    @Test
    void deveCalcularPrecoComAdicionais() {
        Lanche comAdicionais = criarHamburguerComAdicionais();
        assertEquals(23.75, comAdicionais.getPreco(), 0.001);
    }

    @Test
    void deveIncluirExtraQueijoNaDescricao() {
        Lanche comAdicionais = criarHamburguerComAdicionais();
        assertTrue(comAdicionais.getDescricao().contains("Extra Queijo"));
    }

    @Test
    void deveIncluirExtraBaconNaDescricao() {
        Lanche comAdicionais = criarHamburguerComAdicionais();
        assertTrue(comAdicionais.getDescricao().contains("Extra Bacon"));
    }

    @Test
    void deveIncluirExtraMolhoNaDescricao() {
        Lanche comAdicionais = criarHamburguerComAdicionais();
        assertTrue(comAdicionais.getDescricao().contains("Extra Molho"));
    }

    private Lanche criarHamburguerComAdicionais() {
        Lanche hamburguer = new Hamburguer("X-Bacon", 16.0);
        return new ExtraMolho(new ExtraBacon(new ExtraQueijo(hamburguer)));
    }
}
