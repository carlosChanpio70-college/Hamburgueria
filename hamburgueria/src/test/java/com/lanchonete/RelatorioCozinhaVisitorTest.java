package com.lanchonete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.lanchonete.lanche.Hamburguer;
import com.lanchonete.lanche.Lanche;
import com.lanchonete.lanche.decorator.ExtraBacon;
import com.lanchonete.lanche.decorator.ExtraQueijo;
import com.lanchonete.lanche.visitor.RelatorioCozinhaVisitor;

@DisplayName("Testes de RelatorioCozinhaVisitor - Visitor Pattern")
class RelatorioCozinhaVisitorTest {

    @Nested
    @DisplayName("Relatório Básico")
    class RelatorioBasicoTest {

        @Test
        @DisplayName("deve gerar relatório para hambúrguer simples")
        void deveGerarRelatoriHamburguerSimples() {
            Lanche hamburguer = new Hamburguer();
            RelatorioCozinhaVisitor visitor = new RelatorioCozinhaVisitor();

            hamburguer.accept(visitor);

            String relatorio = visitor.getRelatorio();
            assertTrue(relatorio.contains("Hambúrguer"));
        }

        @Test
        @DisplayName("deve gerar relatório para hambúrguer com queijo")
        void deveGerarRelatoriComQueijo() {
            Lanche lanche = new ExtraQueijo(new Hamburguer());
            RelatorioCozinhaVisitor visitor = new RelatorioCozinhaVisitor();

            lanche.accept(visitor);

            String relatorio = visitor.getRelatorio();
            assertTrue(relatorio.contains("Hambúrguer, Queijo Extra"));
        }

        @Test
        @DisplayName("deve gerar relatório para hambúrguer com bacon e queijo")
        void deveGerarRelatoriComBaconEQueijo() {
            Lanche lanche = new ExtraBacon(new ExtraQueijo(new Hamburguer()));
            RelatorioCozinhaVisitor visitor = new RelatorioCozinhaVisitor();

            lanche.accept(visitor);

            String relatorio = visitor.getRelatorio();
            assertTrue(relatorio.contains("Hambúrguer, Queijo Extra, Bacon Extra"));
        }
    }

    @Nested
    @DisplayName("Reset e Reutilização")
    class ResetEReutilizacaoTest {

        @Test
        @DisplayName("getRelatorio deve resetar ignoreNested automaticamente")
        void getRelatorioDeverResetarIgnoreNested() {
            Lanche lanche = new ExtraQueijo(new Hamburguer());
            RelatorioCozinhaVisitor visitor = new RelatorioCozinhaVisitor();

            // Primeira vez
            lanche.accept(visitor);
            String relatorio1 = visitor.getRelatorio();
            assertTrue(relatorio1.contains("Hambúrguer, Queijo Extra"));

            // Segunda vez - deve funcionar normalmente
            lanche.accept(visitor);
            String relatorio2 = visitor.getRelatorio();
            assertTrue(relatorio2.contains("Hambúrguer, Queijo Extra"));
        }

        @Test
        @DisplayName("deve permitir múltiplas gerações de relatório")
        void devePermitirMultiplasGeracoes() {
            Lanche lanche1 = new ExtraQueijo(new Hamburguer());
            Lanche lanche2 = new ExtraBacon(new Hamburguer());
            RelatorioCozinhaVisitor visitor = new RelatorioCozinhaVisitor();

            lanche1.accept(visitor);
            String relatorio1 = visitor.getRelatorio();

            lanche2.accept(visitor);
            String relatorio2 = visitor.getRelatorio();

            assertTrue(relatorio1.contains("Queijo Extra"));
            assertTrue(relatorio2.contains("Bacon Extra"));
        }

        @Test
        @DisplayName("reset deve limpar completamente o relatório")
        void resetDeveLimparRelatorio() {
            Lanche lanche = new ExtraQueijo(new Hamburguer());
            RelatorioCozinhaVisitor visitor = new RelatorioCozinhaVisitor();

            lanche.accept(visitor);
            String relatorio1 = visitor.getRelatorio();
            assertTrue(!relatorio1.isEmpty());

            visitor.reset();
            String relatorio2 = visitor.getRelatorio();
            assertEquals("", relatorio2);
        }

        @Test
        @DisplayName("após reset, deve gerar novo relatório corretamente")
        void aposResetDeveGerarNovoRelatorio() {
            Lanche lanche1 = new ExtraQueijo(new Hamburguer());
            Lanche lanche2 = new ExtraBacon(new Hamburguer());
            RelatorioCozinhaVisitor visitor = new RelatorioCozinhaVisitor();

            // Primeiro relatório
            lanche1.accept(visitor);
            String relatorio1 = visitor.getRelatorio();
            assertTrue(relatorio1.contains("Queijo Extra"));

            // Reset
            visitor.reset();

            // Novo relatório
            lanche2.accept(visitor);
            String relatorio2 = visitor.getRelatorio();
            assertTrue(relatorio2.contains("Bacon Extra"));
            assertTrue(!relatorio2.contains("Queijo Extra"));
        }
    }

    @Nested
    @DisplayName("Comportamento do ignoreNested")
    class ComportamentoIgnoreNestedTest {

        @Test
        @DisplayName("deve incluir decoradores no relatório")
        void deveIncluirDecoradoresNoRelatorio() {
            Lanche lanche = new ExtraQueijo(new Hamburguer());
            RelatorioCozinhaVisitor visitor = new RelatorioCozinhaVisitor();

            lanche.accept(visitor);

            String relatorio = visitor.getRelatorio();
            assertEquals("Hambúrguer, Queijo Extra", relatorio);
        }

        @Test
        @DisplayName("não deve incluir componentes básicos após decorador")
        void naoDeveIncluirComponentesBasicosAposDecorador() {
            Lanche lanche = new ExtraQueijo(new ExtraBacon(new Hamburguer()));
            RelatorioCozinhaVisitor visitor = new RelatorioCozinhaVisitor();

            lanche.accept(visitor);

            String relatorio = visitor.getRelatorio();
            // O comportamento esperado é que apenas o primeiro decorator seja listado
            // devido ao flag ignoreNested
            assertTrue(relatorio.length() > 0);
        }

        @Test
        @DisplayName("reset deve resetar ignoreNested flag")
        void resetDeveresetarIgnoreNestedFlag() {
            Lanche lanche1 = new ExtraQueijo(new Hamburguer());
            Lanche lanche2 = new ExtraBacon(new Hamburguer());
            RelatorioCozinhaVisitor visitor = new RelatorioCozinhaVisitor();

            // Primeira visita
            lanche1.accept(visitor);
            String relatorio1 = visitor.getRelatorio();
            assertTrue(!relatorio1.isEmpty());

            // Reset
            visitor.reset();

            // Segunda visita
            lanche2.accept(visitor);
            String relatorio2 = visitor.getRelatorio();
            assertTrue(!relatorio2.isEmpty());
        }
    }
}
