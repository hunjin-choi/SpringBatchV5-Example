package com.sc.config;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.BatchConfigurationException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.ResourcelessJobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

@Configuration
@EnableBatchProcessing
public class CustomResourcelessBatchConfig extends DefaultBatchConfiguration {
    @Override
    public JobRepository jobRepository() throws BatchConfigurationException {
        return new ResourcelessJobRepository();
    }

    @Override
    public JobExplorer jobExplorer() throws BatchConfigurationException {
        return new JobExplorer() {
            @Override
            public JobInstance getLastJobInstance(String jobName) {
                return JobExplorer.super.getLastJobInstance(jobName);
            }

            @Override
            public JobInstance getJobInstance(String jobName, JobParameters jobParameters) {
                return JobExplorer.super.getJobInstance(jobName, jobParameters);
            }

            @Override
            public JobExecution getLastJobExecution(JobInstance jobInstance) {
                return JobExplorer.super.getLastJobExecution(jobInstance);
            }

            @Override
            public List<JobInstance> getJobInstances(String jobName, int start, int count) {
                return null;
            }

            @Override
            public JobExecution getJobExecution(Long executionId) {
                return null;
            }

            @Override
            public StepExecution getStepExecution(Long jobExecutionId, Long stepExecutionId) {
                return null;
            }

            @Override
            public JobInstance getJobInstance(Long instanceId) {
                return null;
            }

            @Override
            public List<JobExecution> getJobExecutions(JobInstance jobInstance) {
                return null;
            }

            @Override
            public Set<JobExecution> findRunningJobExecutions(String jobName) {
                return null;
            }

            @Override
            public List<String> getJobNames() {
                return null;
            }

            @Override
            public List<JobInstance> findJobInstancesByJobName(String jobName, int start, int count) {
                return null;
            }

            @Override
            public long getJobInstanceCount(String jobName) throws NoSuchJobException {
                return 0;
            }
        };
    }


}
