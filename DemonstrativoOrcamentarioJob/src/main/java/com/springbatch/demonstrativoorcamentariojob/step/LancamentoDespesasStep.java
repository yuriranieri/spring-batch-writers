package com.springbatch.demonstrativoorcamentariojob.step;

import com.springbatch.demonstrativoorcamentariojob.dominio.GrupoLancamento;
import com.springbatch.demonstrativoorcamentariojob.reader.GrupoLancamentoReader;
import com.springbatch.demonstrativoorcamentariojob.writer.LancamentoDespesasFooter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LancamentoDespesasStep {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step escritaLancamentoDespesasStep(GrupoLancamentoReader reader,
                                              MultiResourceItemWriter<GrupoLancamento> writer,
                                              LancamentoDespesasFooter footerCallback) {
        return stepBuilderFactory.get("escritaLancamentoDespesasJobStep")
                .<GrupoLancamento, GrupoLancamento>chunk(1)
                .reader(reader)
                .writer(writer)
                .listener(footerCallback)
                .build();
    }

}
