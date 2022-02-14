package com.springbatch.demonstrativoorcamentariojob.writer;

import com.springbatch.demonstrativoorcamentariojob.dominio.GrupoLancamento;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.List;

import static java.lang.String.format;

@Component
public class LancamentoDespesasFooter implements FlatFileFooterCallback {

    private Double total = 0.0;

    @Override
    public void writeFooter(Writer writer) throws IOException {
        writer.append("\n");
        writer.append(format("\t\t\t\t\t\t\t Total: %s%n", NumberFormat.getCurrencyInstance().format(total)));
        writer.append(format("\t\t\t\t\t\t\t Código de Autenticação: %s%n", "fkyew6868fewjfhjjewf"));
    }

    @BeforeWrite
    public void beforeWrite(List<GrupoLancamento> grupos) {
        grupos.forEach(grupo -> total += grupo.getTotal());
    }

}
