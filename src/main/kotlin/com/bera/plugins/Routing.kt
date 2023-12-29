package com.bera.plugins

import com.bera.routes.generateRoute
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(db: MongoDatabase) {
    routing {
        generateRoute(db)
    }
}
