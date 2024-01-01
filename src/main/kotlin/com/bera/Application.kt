package com.bera

import com.bera.data.DbClient
import com.bera.plugins.configureRouting
import com.bera.plugins.configureSerialization
import io.ktor.server.application.*
import java.io.FileInputStream
import java.util.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val properties = Properties()
    properties.load(FileInputStream("/local.properties"))
    val db_url = properties.getProperty("MONGO_URL") ?: System.getenv("MONGO_URL")
    val db_name = properties.getProperty("MONGO_DB_NAME") ?: System.getenv("MONGO_DB_NAME")
    val dbClient = DbClient()
    val database = dbClient(db_url).getDatabase(db_name)
    configureSerialization()
    configureRouting(database)
}
