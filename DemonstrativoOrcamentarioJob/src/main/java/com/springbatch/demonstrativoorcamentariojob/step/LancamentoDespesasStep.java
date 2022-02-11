package com.springbatch.demonstrativoorcamentariojob.step;

import com.springbatch.demonstrativoorcamentariojob.dominio.Lancamento;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LancamentoDespesasStep {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step escritaLancamentoDespesasStep(JdbcCursorItemReader<Lancamento> reader,
                                                 ItemWriter<Lancamento> writer) {
        return stepBuilderFactory.get("escritaLancamentoDespesasJobStep")
                .<Lancamento, Lancamento>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

}
