package com.springbatch.arquivolargurafixawriterjob.step;

import com.springbatch.arquivolargurafixawriterjob.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ArquivoLarguraFixaStep {

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step escritaArquivoLarguraFixaStep(ItemReader<Cliente> reader, ItemWriter<Cliente> writer) {
        return stepBuilderFactory
                .get("escritaArquivoLarguraFixaStep")
                .<Cliente, Cliente>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

}
