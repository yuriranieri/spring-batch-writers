package com.springbatch.demonstrativoorcamentariojob.writer;

import com.springbatch.demonstrativoorcamentariojob.dominio.GrupoLancamento;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

@Component
public class LancamentoDespesasWriter {

    @Bean
    @StepScope
    public FlatFileItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter(
            @Value("#{jobParameters['arquivoSaida']}") Resource resource) {
        return new FlatFileItemWriterBuilder<GrupoLancamento>()
                .name("demonstrativoOrcamentarioWriter")
                .resource(resource)
                .lineAggregator(lineAggregator())
                // informa o formato, que fica customizado, lineAggregator define a logica de agregacao da linha
                .build();
    }

    private LineAggregator<GrupoLancamento> lineAggregator() {
        return grupoLancamento -> {
            var formatGrupo = String.format("[%d] %s - %s%n", grupoLancamento.getCodigoNaturezaDespesa(),
                    grupoLancamento.getDescricaoNaturezaDespesa(),
                    NumberFormat.getCurrencyInstance().format(grupoLancamento.getTotal()));

            var stringBuilder = new StringBuilder();
            grupoLancamento.getLancamentos().forEach(lancamento -> stringBuilder.append(
                    String.format("\t [%s] %s - %s%n",
                            new SimpleDateFormat("dd/MM/yyyy").format(lancamento.getData()),
                            lancamento.getDescricao(),
                            NumberFormat.getCurrencyInstance().format(lancamento.getValor()))));
            var formatLancamento = stringBuilder.toString();
            return formatGrupo + formatLancamento;
        };
    }

}
