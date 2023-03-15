package net.leanix.vsm.githubbroker.connector.adapter.feign.data

import java.util.UUID

data class LanguageRequest(
    val id: String,
    val runId: UUID,
    val source: String,
    val name: String,
    val organizationName: String?
)
