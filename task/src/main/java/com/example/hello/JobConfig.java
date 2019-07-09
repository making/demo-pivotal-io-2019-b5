package com.example.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class JobConfig {

    private final Logger log = LoggerFactory.getLogger(JobConfig.class);

    private final StepBuilderFactory stepBuilderFactory;

    private final JobBuilderFactory jobBuilderFactory;

    public JobConfig(StepBuilderFactory stepBuilderFactory, JobBuilderFactory jobBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Bean
    public Step step1() {
        return this.stepBuilderFactory.get("step1") //
            .tasklet((stepContribution, chunkContext) -> {
                log.info("\n" +
        "██████╗ ██╗██╗   ██╗ ██████╗ ████████╗ █████╗ ██╗        ██╗ ██████╗     ██████╗  ██████╗  ██╗ █████╗ \n" +
        "██╔══██╗██║██║   ██║██╔═══██╗╚══██╔══╝██╔══██╗██║        ██║██╔═══██╗    ╚════██╗██╔═████╗███║██╔══██╗\n" +
        "██████╔╝██║██║   ██║██║   ██║   ██║   ███████║██║        ██║██║   ██║     █████╔╝██║██╔██║╚██║╚██████║\n" +
        "██╔═══╝ ██║╚██╗ ██╔╝██║   ██║   ██║   ██╔══██║██║        ██║██║   ██║    ██╔═══╝ ████╔╝██║ ██║ ╚═══██║\n" +
        "██║     ██║ ╚████╔╝ ╚██████╔╝   ██║   ██║  ██║███████╗██╗██║╚██████╔╝    ███████╗╚██████╔╝ ██║ █████╔╝\n" +
        "╚═╝     ╚═╝  ╚═══╝   ╚═════╝    ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝╚═╝ ╚═════╝     ╚══════╝ ╚═════╝  ╚═╝ ╚════╝ \n");
                return RepeatStatus.FINISHED;
            }) //
            .build();
    }

    @Bean
    public Job job1() {
        return this.jobBuilderFactory.get("job1") //
            .incrementer(new RunIdIncrementer()) //
            .start(step1()) //
            .build();
    }
}
