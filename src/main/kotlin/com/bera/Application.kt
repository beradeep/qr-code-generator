package com.bera

import com.bera.EnvConstants.DB_NAME
import com.bera.EnvConstants.MONGO_DB_URI
import com.bera.data.DbClient
import com.bera.plugins.configureRouting
import com.bera.plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val dbClient = DbClient()
    val db = dbClient(
        environment.config.propertyOrNull("ktor.security.mongo_url")?.getString()
            ?: MONGO_DB_URI
    ).getDatabase(
        environment.config.propertyOrNull("ktor.security.mongo_db_name")?.getString()
            ?: DB_NAME
    )
    configureSerialization()
    configureRouting(db)
}
