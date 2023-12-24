package com.sincheon.ssadagame.interfaces

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class Scheduler(
    private val jobLauncher: JobLauncher,
    @Qualifier("topGamePriceUpdateJob")
    private val topGamePriceUpdateJob: Job,
) {
    @Scheduled(fixedDelay = 3600000, initialDelay = 1000)
    fun topGamePriceUpdateJobExecute() {
        val jobParameters = JobParametersBuilder()
            .addString("uuid", UUID.randomUUID().toString(), true)
            .toJobParameters()

        println("job start")
        jobLauncher.run(topGamePriceUpdateJob, jobParameters)
        println("job end")
    }
}
