package com.hackathon.zeebe.service.order

import io.zeebe.client.api.response.ActivatedJob
import io.zeebe.client.api.worker.JobClient
import io.zeebe.spring.client.annotation.ZeebeWorker
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ShippingService {
    val logger: Logger = LoggerFactory.getLogger(ShippingService::class.java)

    @ZeebeWorker(type = "shipping-service")
    fun shipItems(client: JobClient, job: ActivatedJob) {
        val variables = job.variablesAsMap

        logger.info("Processing order: ${variables["orderId"]}")
        logger.info("Ship items: : ${variables["orderItems"]}")

        client.newCompleteCommand(job.key)
                .send()
                .join()
    }
}