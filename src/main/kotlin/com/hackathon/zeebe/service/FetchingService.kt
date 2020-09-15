package com.hackathon.zeebe.service

import io.zeebe.client.api.response.ActivatedJob
import io.zeebe.client.api.worker.JobClient
import io.zeebe.spring.client.annotation.ZeebeWorker
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class FetchingService {
    val logger: Logger = LoggerFactory.getLogger(FetchingService::class.java)

    @ZeebeWorker(type = "fetcher-service")
    fun fetchItems(client: JobClient, job: ActivatedJob) {
        val variables = job.variablesAsMap

        logger.info("Processing order: ${variables["orderId"]}")
        logger.info("Fetch items: ${variables["orderItems"]}")

        client.newCompleteCommand(job.key)
                .send()
                .join()
    }
}