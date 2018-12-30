package hello.configuration;

import hello.dto.Individu;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@EnableBatchProcessing
@Component
public class IndividusJob extends JobExecutionListenerSupport {
    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    Writer writer;

    @Bean(name = "accountJob")
    public Job accountKeeperJob() {

        Step step = stepBuilderFactory.get("step-1")
                .<Individu, Individu> chunk(1)
                .reader(new Reader())
                .writer(writer)
                .build();

        Job job = jobBuilderFactory.get("accounting-job")
                .incrementer(new RunIdIncrementer())
                .listener(this)
                .start(step)
                .build();

        return job;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
        }
    }
}
