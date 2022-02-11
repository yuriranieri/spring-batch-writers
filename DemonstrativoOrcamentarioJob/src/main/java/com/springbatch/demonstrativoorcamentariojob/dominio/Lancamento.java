package com.springbatch.demonstrativoorcamentariojob.dominio;

public class Lancamento {

    private int codigoNaturezaDespesa;
    private String descricaoNaturezaDespesa;
    private String descricaoLancamento;
    private String data;
    private Double valor;

    public int getCodigoNaturezaDespesa() {
        return codigoNaturezaDespesa;
    }

    public void setCodigoNaturezaDespesa(int codigoNaturezaDespesa) {
        this.codigoNaturezaDespesa = codigoNaturezaDespesa;
    }

    public String getDescricaoNaturezaDespesa() {
        return descricaoNaturezaDespesa;
    }

    public void setDescricaoNaturezaDespesa(String descricaoNaturezaDespesa) {
        this.descricaoNaturezaDespesa = descricaoNaturezaDespesa;
    }

    public String getDescricaoLancamento() {
        return descricaoLancamento;
    }

    public void setDescricaoLancamento(String descricaoLancamento) {
        this.descricaoLancamento = descricaoLancamento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Lancamento{" +
                "codigoNaturezaDespesa=" + codigoNaturezaDespesa +
                ", descricaoNaturezaDespesa='" + descricaoNaturezaDespesa + '\'' +
                ", descricaoLancamento='" + descricaoLancamento + '\'' +
                ", data=" + data +
                ", valor=" + valor +
                '}';
    }
}
