package com.springbatch.demonstrativoorcamentariojob.reader;

import com.springbatch.demonstrativoorcamentariojob.dominio.GrupoLancamento;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoLancamentoReader implements ItemStreamReader<GrupoLancamento> {

    @Autowired
    private JdbcCursorItemReader<GrupoLancamento> delegate;
    private GrupoLancamento lancamentoAtual;

    @Override
    public GrupoLancamento read() throws Exception {
        if (lancamentoAtual == null)
            lancamentoAtual = delegate.read();

        GrupoLancamento grupoLancamento = lancamentoAtual;
        lancamentoAtual = null;

        if (grupoLancamento != null) {
            GrupoLancamento proxLancamento = peek();
            while (isLancamentoRelacionado(grupoLancamento, proxLancamento)) {
                grupoLancamento.getLancamentos().add(lancamentoAtual.getLancamentoTmp());
                proxLancamento = peek();
            }
            grupoLancamento.getLancamentos().add(grupoLancamento.getLancamentoTmp());
        }
        return grupoLancamento;
    }

    private boolean isLancamentoRelacionado(GrupoLancamento grupoLancamento, GrupoLancamento proxLancamento) {
        return proxLancamento != null && proxLancamento.getCodigoNaturezaDespesa().equals(grupoLancamento.getCodigoNaturezaDespesa());
    }

    public GrupoLancamento peek() throws Exception {
        lancamentoAtual = delegate.read();
        return lancamentoAtual;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }

}
