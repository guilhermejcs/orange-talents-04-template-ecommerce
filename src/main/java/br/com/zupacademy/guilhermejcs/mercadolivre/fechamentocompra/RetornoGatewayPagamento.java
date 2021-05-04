package br.com.zupacademy.guilhermejcs.mercadolivre.fechamentocompra;

public interface RetornoGatewayPagamento {
    /**
     *
     * @param compra
     * @return uma nova transação em função do gateway específico
     */
    Transacao toTransacao(Compra compra);
}
