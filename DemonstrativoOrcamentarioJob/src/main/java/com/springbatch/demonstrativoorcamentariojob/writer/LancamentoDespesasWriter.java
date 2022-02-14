package com.springbatch.demonstrativoorcamentariojob.writer;

import com.springbatch.demonstrativoorcamentariojob.dominio.GrupoLancamento;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.file.ResourceSuffixCreator;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.format;

@Component
public class LancamentoDespesasWriter {

    @Bean
    @StepScope
    public MultiResourceItemWriter<GrupoLancamento> multiDemonstrativoOrcamentarioWriter(
            @Value("#{jobParameters['arquivosSaidas']}") Resource resource,
            FlatFileItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter) {
        return new MultiResourceItemWriterBuilder<GrupoLancamento>()
                .name("multiDemonstrativoOrcamentarioWriter")
                .resource(resource)
                .delegate(demonstrativoOrcamentarioWriter)
                .resourceSuffixCreator(suffixCreator()) // cria sufixos
                .itemCountLimitPerResource(1)
                // para cada grupo lancamento criar um item, só é checado dps que le td o chunk
                .build();
    }

    private ResourceSuffixCreator suffixCreator() {
        return index -> index + ".txt";
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter(
            @Value("#{jobParameters['arquivoSaida']}") Resource resource,
            LancamentoDespesasFooter footerCallback) {
        return new FlatFileItemWriterBuilder<GrupoLancamento>()
                .name("demonstrativoOrcamentarioWriter")
                .resource(resource)
                .lineAggregator(lineAggregator())
                // informa o formato, que fica customizado, lineAggregator define a logica de agregacao da linha
                .headerCallback(headerCallback()) // criar o header(cabecalho)
                .footerCallback(footerCallback) // cria o footer(rodape)
                .build();
    }

    private LineAggregator<GrupoLancamento> lineAggregator() {
        return grupoLancamento -> {
            var formatGrupo = format("[%d] %s - %s%n", grupoLancamento.getCodigoNaturezaDespesa(),
                    grupoLancamento.getDescricaoNaturezaDespesa(),
                    NumberFormat.getCurrencyInstance().format(grupoLancamento.getTotal()));

            var stringBuilder = new StringBuilder();
            grupoLancamento.getLancamentos().forEach(lancamento -> stringBuilder.append(
                    format("\t [%s] %s - %s%n",
                            new SimpleDateFormat("dd/MM/yyyy").format(lancamento.getData()),
                            lancamento.getDescricao(),
                            NumberFormat.getCurrencyInstance().format(lancamento.getValor()))));
            var formatLancamento = stringBuilder.toString();
            return formatGrupo + formatLancamento;
        };
    }

    private FlatFileHeaderCallback headerCallback() {
        return writer -> {
            writer.append(format("SISTEMA INTEGRADO: XPTO \t\t\t\t DATA: %s%n",
                    new SimpleDateFormat("dd//MM/yyyy").format(new Date())));
            writer.append(format("MÓDULO: ORÇAMENTO \t\t\t\t\t\t HORA: %s",
                    new SimpleDateFormat("HH:mm").format(new Date())));
            writer.append(format("MÓDULO: ORÇAMENTO \t\t\t\t\t HORA: %s",
                    new SimpleDateFormat("HH:mm").format(new Date())));
            writer.append("\t\t\tDEMONSTRATIVO ORCAMENTARIO\n");
            writer.append("----------------------------------------------------------------------------\n");
            writer.append("CODIGO NOME VALOR\n");
            writer.append("\t Data Descricao Valor\n");
            writer.append("----------------------------------------------------------------------------\n");

        };
    }

}
