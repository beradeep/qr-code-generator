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
    lateinit var db_url: String
    lateinit var db_name: String
    try {
        db_url = System.getenv("MONGO_URL")
        db_name = System.getenv("MONGO_DB_NAME")
    } catch (_: Exception) {
        properties.load(FileInputStream("local.properties"))
        db_url = properties.getProperty("MONGO_URL")
        db_name = properties.getProperty("MONGO_DB_NAME")
    }
    val dbClient = DbClient()
    val database = dbClient(db_url).getDatabase(db_name)
    configureSerialization()
    configureRouting(database)
}
