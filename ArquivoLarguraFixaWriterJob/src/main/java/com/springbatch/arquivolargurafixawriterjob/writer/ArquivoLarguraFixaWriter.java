package com.springbatch.arquivolargurafixawriterjob.writer;

import com.springbatch.arquivolargurafixawriterjob.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ArquivoLarguraFixaWriter {

    @Bean
    @StepScope
    public FlatFileItemWriter<Cliente> escritaArquivoLarguraFixaWriter(
            @Value("#{jobParameters['arquivoSaida']}") Resource arquivoSaida) { // onde vamos escrever o arquivo
        return new FlatFileItemWriterBuilder<Cliente>()
                .name("escritaArquivoLarguraFixaWriter")
                .resource(arquivoSaida)
                .formatted()// informa que vamos usar 1 formato customizado q vai ser informado atraves de regex
                .format("%-9s %-9s %-2s %-19s")
                // formata com regex de acordo com o range, se a string recebida n tem o tamanho informado
                // completa com espacos em branco, o (-) antes informa para primeiro colocar a string e dps o tamanho
                .names("nome", "sobrenome", "idade", "email")
                .build();
    }

}
