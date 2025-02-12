package com.sc.config;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.ResourcelessJobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.boot.autoconfigure.batch.JobLauncherApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.sc.batch.CustomProcessor;
import com.sc.batch.CustomReader;
import com.sc.batch.CustomWriter;

import java.time.LocalDateTime;
import java.util.UUID;

@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfiguration {
	@Bean
	public JobParameters jobParameters() {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("prodDt", LocalDateTime.now().toString())
				.addString("uuid", UUID.randomUUID().toString()) // resourcelsssJobRepository 사용할거면 해당 파라미터는 불필요
				.toJobParameters();

		return jobParameters;
	}

	@Bean
	public JobRepository jobRepository() {
		return new ResourcelessJobRepository();
	}
	@Bean
	Job createJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new JobBuilder("job", jobRepository)
				.flow(createStep(jobRepository, transactionManager)).end().build();
	}
	
	@Bean
	Step createStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("step", jobRepository)
				.<String, String> chunk(3, transactionManager)
				.allowStartIfComplete(true)
				.reader(new CustomReader())
				.processor(new CustomProcessor())
				.writer(new CustomWriter())
				.build();
	}	
}