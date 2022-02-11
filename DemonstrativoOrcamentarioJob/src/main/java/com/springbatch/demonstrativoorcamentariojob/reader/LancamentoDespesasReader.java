package com.springbatch.demonstrativoorcamentariojob.reader;

import com.springbatch.demonstrativoorcamentariojob.dominio.GrupoLancamento;
import com.springbatch.demonstrativoorcamentariojob.dominio.Lancamento;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class LancamentoDespesasReader {

    @Bean
    public JdbcCursorItemReader<GrupoLancamento> bancoLancamentoReader(
            @Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<GrupoLancamento>()
                .name("bancoLancamentoReader")
                .dataSource(dataSource)
                .sql("select * from lancamento")
                .rowMapper(rowMapper())
                .build();
    }

    private RowMapper<GrupoLancamento> rowMapper() {
        return (rs, rowNum) -> {
            Lancamento lancamento = new Lancamento();
            lancamento.setData(rs.getDate("data"));
            lancamento.setDescricao(rs.getString("descricaoLancamento"));
            lancamento.setValor(rs.getDouble("valor"));

            GrupoLancamento grupo = new GrupoLancamento();
            grupo.setCodigoNaturezaDespesa(rs.getInt("codigoNaturezaDespesa"));
            grupo.setDescricaoNaturezaDespesa(rs.getString("descricaoNaturezaDespesa"));
            grupo.setLancamentoTmp(lancamento);

            return grupo;
        };
    }

}
