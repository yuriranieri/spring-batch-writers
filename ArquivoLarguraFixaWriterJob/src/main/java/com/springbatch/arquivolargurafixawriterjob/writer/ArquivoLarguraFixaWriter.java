package com.springbatch.arquivolargurafixawriterjob.writer;

import com.springbatch.arquivolargurafixawriterjob.dominio.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ArquivoLarguraFixaWriter {

    @Bean
    public ItemWriter<Cliente> leituraArquivoLarguraFixaWriter() {
        return clientes -> clientes.forEach(System.out::println);
    }

}
