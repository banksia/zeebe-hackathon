package com.hackathon.zeebe

import io.zeebe.spring.client.EnableZeebeClient
import io.zeebe.spring.client.annotation.ZeebeDeployment
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
@EnableZeebeClient // connect to Zeebe broker
@ZeebeDeployment(classPathResources = ["order-process.bpmn"]) // deploy the model
class Application

fun main(args: Array<String>) {
	SpringApplication.run(Application::class.java, *args)
}