package com.hackathon.zeebe.service

import io.zeebe.client.api.response.ActivatedJob
import io.zeebe.client.api.worker.JobClient
import io.zeebe.spring.client.annotation.ZeebeWorker
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.HashMap

@Service
class PaymentService {
    val logger: Logger = LoggerFactory.getLogger(PaymentService::class.java)

    @ZeebeWorker(type = "payment-service")
    fun collectMoney(client: JobClient, job: ActivatedJob) {
        val variables = job.variablesAsMap
        val price = 46.50

        logger.info("Processing order: ${variables["orderId"]}")
        logger.info("Collect money: $price")

        val result: MutableMap<String, Any> = HashMap()
        result["totalPrice"] = price

        client.newCompleteCommand(job.key)
                .variables(result)
                .send()
                .join()
    }
}