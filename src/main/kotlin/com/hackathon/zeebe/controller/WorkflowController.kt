package com.hackathon.zeebe.controller

import com.hackathon.zeebe.Application
import io.zeebe.client.api.response.WorkflowInstanceEvent
import io.zeebe.spring.client.ZeebeClientLifecycle
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.HashMap


@RestController
class WorkflowController {
    val logger: Logger = LoggerFactory.getLogger(Application::class.java)

    @Autowired
    private val client: ZeebeClientLifecycle? = null

    @GetMapping("/start")
    fun workflow() {
        // Create workflow instance with fake data
        val data: MutableMap<String, Any> = HashMap()
        data["orderId"] = 31243
        data["orderItems"] = listOf(435, 182, 376)

        val event: WorkflowInstanceEvent = client!!
                .newCreateInstanceCommand()
                .bpmnProcessId("order-process")
                .latestVersion()
                .variables(data)
                .send()
                .join()

        logger.info("started instance for workflowKey='{}', bpmnProcessId='{}', version='{}' with workflowInstanceKey='{}'",
                event.workflowKey, event.bpmnProcessId, event.version, event.workflowInstanceKey)
    }
}