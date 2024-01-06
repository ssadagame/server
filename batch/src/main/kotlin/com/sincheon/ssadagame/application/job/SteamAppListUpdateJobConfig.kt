package com.sincheon.ssadagame.application.job

import com.sincheon.ssadagame.application.tasklet.SteamAppListTasklet
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SteamAppListUpdateJobConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    private val steamAppListTasklet: SteamAppListTasklet,
) {
    @Bean("steamAppListUpdateJob")
    fun steamAppListUpdateJob(
        steamAppListUpdateStep: Step,
    ) = jobBuilderFactory.get("steamAppListUpdateJob")
        .start(steamAppListUpdateStep)
        .build()

    @Bean("steamAppListUpdateStep")
    fun steamAppListUpdateStep() = stepBuilderFactory.get("steamAppListUpdateStep")
        .tasklet(steamAppListTasklet)
        .build()
}
