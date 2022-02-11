package com.springbatch.demonstrativoorcamentariojob.writer;

import com.springbatch.demonstrativoorcamentariojob.dominio.GrupoLancamento;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

@Component
public class LancamentoDespesasWriter {

    @Bean
    public ItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter() {
        return itens -> {
            System.out.println("---- Demonstrativo orçamentário ----");
            itens.forEach(grupo -> {
                System.out.printf("[%d] %s - %s%n", grupo.getCodigoNaturezaDespesa(),
                        grupo.getDescricaoNaturezaDespesa(),
                        NumberFormat.getCurrencyInstance().format(grupo.getTotal()));
                grupo.getLancamentos().forEach(lancamento ->
                        System.out.printf("\t [%s] %s - %s%n",
                                new SimpleDateFormat("dd/MM/yyyy").format(lancamento.getData()),
                                lancamento.getDescricao(),
                                NumberFormat.getCurrencyInstance().format(lancamento.getValor())));
            });
        };
    }

}
