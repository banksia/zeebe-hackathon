package com.hackathon.zeebe.service.coffee

import io.zeebe.client.api.response.ActivatedJob
import io.zeebe.client.api.worker.JobClient
import io.zeebe.spring.client.annotation.ZeebeWorker
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class BrewingService {

    val logger: Logger = LoggerFactory.getLogger(BrewingService::class.java)

    @ZeebeWorker(type = "brewing-service")
    fun brewCoffee(client: JobClient, job: ActivatedJob) {
        val variables = job.variablesAsMap

        logger.info("Brewing coffee: ${variables["brewType"]}")

        client.newCompleteCommand(job.key)
                .send()
                .join()
    }
}