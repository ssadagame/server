package com.sincheon.ssadagame.application.job

import com.sincheon.ssadagame.application.tasklet.TopSellingGamesUpdateTasklet
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TopGamePriceUpdateJobConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    private val tasklet: TopSellingGamesUpdateTasklet,
) {

    @Bean
    fun topGamePriceUpdateJob(
        step: Step,
    ) = jobBuilderFactory.get("topGamePriceUpdateJob")
        .incrementer(RunIdIncrementer())
        .start(step)
        .build()

    @Bean
    fun step() = stepBuilderFactory.get("step1")
        .tasklet(tasklet)
        .build()
}
