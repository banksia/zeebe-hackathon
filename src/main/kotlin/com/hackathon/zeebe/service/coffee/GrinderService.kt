package com.hackathon.zeebe.service.coffee

import com.hackathon.zeebe.service.PaymentService
import io.zeebe.client.api.response.ActivatedJob
import io.zeebe.client.api.worker.JobClient
import io.zeebe.spring.client.annotation.ZeebeWorker
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class GrinderService {

    val logger: Logger = LoggerFactory.getLogger(GrinderService::class.java)

    @ZeebeWorker(type = "grinder-service")
    fun grindCoffee(client: JobClient, job: ActivatedJob) {
        val variables = job.variablesAsMap

        logger.info("Grinding coffee: ${variables["grindType"]}")

        client.newCompleteCommand(job.key)
                .send()
                .join()
    }

}