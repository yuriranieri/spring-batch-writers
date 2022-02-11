package com.springbatch.arquivodelimitadowriterjob.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ArquivoDelimitadoJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job escritaArquivoDelimitadoJob(Step step) {
        return jobBuilderFactory
                .get("escritaArquivoDelimitadoJob")
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
