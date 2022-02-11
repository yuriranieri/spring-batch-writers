package com.springbatch.demonstrativoorcamentariojob.reader;

import com.springbatch.demonstrativoorcamentariojob.dominio.Lancamento;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class LancamentoDespesasReader {

    @Bean
    public JdbcCursorItemReader<Lancamento> jdbcCursorReader(@Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Lancamento>()
                .name("jdbcCursorReader")
                .dataSource(dataSource)
                .sql("select * from lancamento")
                .rowMapper(new BeanPropertyRowMapper<>(Lancamento.class))
                .build();
    }

}
