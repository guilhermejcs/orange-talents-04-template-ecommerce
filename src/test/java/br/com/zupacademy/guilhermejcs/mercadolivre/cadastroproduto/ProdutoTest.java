package br.com.zupacademy.guilhermejcs.mercadolivre.cadastroproduto;

import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.SenhaLimpa;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.guilhermejcs.mercadolivre.cadastrocategorias.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class ProdutoTest {
    @DisplayName("um produto precisa de no mínimo 3 características")
    @ParameterizedTest
    @MethodSource("geradorTeste1")
    void teste1(Collection<NovaCaracteristicaRequest> caracteristicas) throws Exception {
        Categoria categoria = new Categoria("categoria");
        Usuario dono = new Usuario("email@email.com.br", new SenhaLimpa("senhaa"));

        new Produto("nome", 10, "descricao", BigDecimal.TEN,
                categoria, dono, caracteristicas);

    }

    static Stream<Arguments> geradorTeste1() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new NovaCaracteristicaRequest("key", "value"),
                                new NovaCaracteristicaRequest("key2", "value2"),
                                new NovaCaracteristicaRequest("key3", "value3"))),
                Arguments.of(
                        List.of(
                                new NovaCaracteristicaRequest("key", "value"),
                                new NovaCaracteristicaRequest("key2", "value2"),
                                new NovaCaracteristicaRequest("key3", "value3"))));

    }

    @DisplayName("um produto não pode ser criado com menos de 3 características")
    @ParameterizedTest
    @MethodSource("geradorTeste2")
    void teste2(Collection<NovaCaracteristicaRequest> caracteristicas)
            throws Exception {

        Categoria categoria = new Categoria("categoria");
        Usuario dono = new Usuario("email@email.com.br",
                new SenhaLimpa("senhaa"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Produto("nome", 10, "descricao", BigDecimal.TEN, categoria,
                    dono, caracteristicas);
        });
    }

    static Stream<Arguments> geradorTeste2() {
        return Stream.of(
                Arguments.of(
                        List.of(new NovaCaracteristicaRequest("key", "descricao"),
                                new NovaCaracteristicaRequest("key2", "value2"))),
                Arguments.of(
                        List.of(new NovaCaracteristicaRequest("key", "value"))));
    }

    @DisplayName("verifica estoque do preoduto")
    @ParameterizedTest
    @CsvSource({"1,1,true","1,2,false","4,2,true","1,5,false"})
    void teste3(int estoque, int quantidadePedida, boolean resultadoEsperado) throws Exception {
        List<NovaCaracteristicaRequest> caracteristicas = List.of(
                new NovaCaracteristicaRequest("key", "value"),
                new NovaCaracteristicaRequest("key2", "value2"),
                new NovaCaracteristicaRequest("key3", "value3"));
        Categoria categoria = new Categoria("categoria");
        Usuario dono = new Usuario("email@email.com.br",
                new SenhaLimpa("senhaa"));
        Produto produto = new Produto("nome", estoque, "descricao",BigDecimal.TEN,
                categoria, dono, caracteristicas);
        boolean resultado = produto.abateEstoque(quantidadePedida);

        Assertions.assertEquals(resultadoEsperado, resultado);
    }

    @DisplayName("nao aceita abater estoque <= zero")
    @ParameterizedTest
    @CsvSource({"0","-1","-100"})
    void teste4(int estoque) throws Exception {
        List<NovaCaracteristicaRequest> caracteristicas = List.of(
                new NovaCaracteristicaRequest("key", "value"),
                new NovaCaracteristicaRequest("key2", "value2"),
                new NovaCaracteristicaRequest("key3", "value3"));
        Categoria categoria = new Categoria("categoria");
        Usuario dono = new Usuario("email@email.com.br",
                new SenhaLimpa("senhaa"));
        Produto produto = new Produto("nome", estoque, "descricao",BigDecimal.TEN,
                categoria, dono, caracteristicas);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                produto.abateEstoque(estoque));
    }

}

