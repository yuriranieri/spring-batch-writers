package com.springbatch.arquivodelimitadowriterjob.step;

import com.springbatch.arquivodelimitadowriterjob.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ArquivoDelimitadoStep {

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step escritaArquivoDelimitadoStep(ItemReader<Cliente> reader, ItemWriter<Cliente> writer) {
        return stepBuilderFactory
                .get("escritaArquivoDelimitadoStep")
                .<Cliente, Cliente>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

}
