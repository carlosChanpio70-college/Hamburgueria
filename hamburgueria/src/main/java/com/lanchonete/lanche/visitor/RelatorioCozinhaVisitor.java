package com.lanchonete.lanche.visitor;

import com.lanchonete.lanche.Combo;
import com.lanchonete.lanche.Hamburguer;
import com.lanchonete.lanche.decorator.ExtraBacon;
import com.lanchonete.lanche.decorator.ExtraQueijo;

public class RelatorioCozinhaVisitor implements LancheVisitor {

    private final StringBuilder relatorio = new StringBuilder();
    private boolean ignoreNested = false;

    @Override
    public void visitHamburguer(Hamburguer hamburguer) {
        if (!ignoreNested) {
            relatorio.append("Hambúrguer");
            relatorio.append(System.lineSeparator());
        }
    }

    @Override
    public void visitCombo(Combo combo) {
        if (!ignoreNested) {
            relatorio.append("Combo: ").append(combo.descricao());
            relatorio.append(System.lineSeparator());
        }
    }

    @Override
    public void visitExtraQueijo(ExtraQueijo extraQueijo) {
        if (!ignoreNested) {
            relatorio.append(extraQueijo.descricao());
            relatorio.append(System.lineSeparator());
            ignoreNested = true;
        }
    }

    @Override
    public void visitExtraBacon(ExtraBacon extraBacon) {
        if (!ignoreNested) {
            relatorio.append(extraBacon.descricao());
            relatorio.append(System.lineSeparator());
            ignoreNested = true;
        }
    }

    public String getRelatorio() {
        String resultado = relatorio.toString().trim();
        ignoreNested = false; // Reset para permitir reutilização
        return resultado;
    }

    public void reset() {
        relatorio.setLength(0);
        ignoreNested = false;
    }
}
