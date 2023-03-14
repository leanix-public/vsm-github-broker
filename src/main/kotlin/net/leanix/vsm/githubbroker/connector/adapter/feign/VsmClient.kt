package net.leanix.vsm.githubbroker.connector.adapter.feign

import net.leanix.vsm.githubbroker.connector.adapter.feign.data.LanguageRequest
import net.leanix.vsm.githubbroker.connector.adapter.feign.data.ServiceRequest
import net.leanix.vsm.githubbroker.connector.adapter.feign.data.TopicRequest
import net.leanix.vsm.githubbroker.shared.auth.adapter.feign.config.MtmFeignClientConfiguration
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(
    name = "servicesClient",
    url = "\${leanix.vsm.event-broker.base-url}",
    configuration = [MtmFeignClientConfiguration::class]
)
interface VsmClient {

    @PostMapping("/services")
    fun saveService(serviceRequest: ServiceRequest)

    @PostMapping("/languages")
    fun saveLanguage(languageRequest: LanguageRequest)

    @PostMapping("/topics")
    fun saveTopic(topicRequest: TopicRequest)
}