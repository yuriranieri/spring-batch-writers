package com.springbatch.demonstrativoorcamentariojob.writer;

import com.springbatch.demonstrativoorcamentariojob.dominio.Lancamento;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LancamentoDespesasWriter {

    @Bean
    public ItemWriter<Lancamento> writer() {
        return items -> items.forEach(System.out::println);
    }

}
