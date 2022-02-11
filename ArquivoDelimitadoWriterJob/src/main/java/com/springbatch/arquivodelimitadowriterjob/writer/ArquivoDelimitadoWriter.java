package com.springbatch.arquivodelimitadowriterjob.writer;

import com.springbatch.arquivodelimitadowriterjob.dominio.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ArquivoDelimitadoWriter {

    @Bean
    public ItemWriter<Cliente> leituraArquivoDelimitadoWriter() {
        return items -> items.forEach(System.out::println);
    }

}
