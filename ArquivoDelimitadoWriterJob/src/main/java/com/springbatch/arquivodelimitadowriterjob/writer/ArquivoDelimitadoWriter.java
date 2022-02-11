package com.springbatch.arquivodelimitadowriterjob.writer;

import com.springbatch.arquivodelimitadowriterjob.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ArquivoDelimitadoWriter {

    @Bean
    @StepScope
    public FlatFileItemWriter<Cliente> escritaArquivoDelimitadoWriter(
            @Value("#{jobParameters['arquivoSaida']}") Resource arquivoSaida) {
        return new FlatFileItemWriterBuilder<Cliente>()
                .name("escritaArquivoDelimitadoWriter")
                .resource(arquivoSaida)
                .delimited() // tipo arquivo
                .delimiter("||") // informa qual vai ser o delmitador (, ; \\ // || ...)
                .names("nome", "sobrenome", "idade", "email")
                .build();
    }

}
