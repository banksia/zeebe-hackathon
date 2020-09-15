package com.hackathon.zeebe.service.coffee

import io.zeebe.client.api.response.ActivatedJob
import io.zeebe.client.api.worker.JobClient
import io.zeebe.spring.client.annotation.ZeebeWorker
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class FlavourService {

    val logger: Logger = LoggerFactory.getLogger(FlavourService::class.java)

    @ZeebeWorker(type = "flavour-service")
    fun flavourCoffee(client: JobClient, job: ActivatedJob) {
        val variables = job.variablesAsMap

        logger.info("Adding flavour: ${variables["flavourType"]}")

        client.newCompleteCommand(job.key)
                .send()
                .join()
    }
}