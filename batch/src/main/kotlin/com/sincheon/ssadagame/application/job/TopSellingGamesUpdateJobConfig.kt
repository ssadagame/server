package com.sincheon.ssadagame.application.job

import com.sincheon.ssadagame.application.tasklet.TopSellingGamesUpdateTasklet
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TopSellingGamesUpdateJobConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    private val tasklet: TopSellingGamesUpdateTasklet,
) {
    @Bean("topSellingGamesUpdateJob")
    fun topSellingGamesUpdateJob(
        topSellingGamesUpdateStep: Step,
    ) = jobBuilderFactory.get("topSellingGamesUpdateJob")
        .incrementer(RunIdIncrementer())
        .start(topSellingGamesUpdateStep)
        .build()

    @Bean("topSellingGamesUpdateStep")
    fun topSellingGamesUpdateStep() = stepBuilderFactory.get("topSellingGamesUpdateStep")
        .tasklet(tasklet)
        .build()
}
