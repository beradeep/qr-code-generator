package com.bera.routes

import com.bera.data.RequestData
import com.bera.generate.GenerateRequest
import com.bera.generate.GenerateService
import com.mongodb.client.model.Filters
import com.mongodb.client.model.FindOneAndUpdateOptions
import com.mongodb.client.model.ReturnDocument
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

fun Route.generateRoute(db: MongoDatabase) {
    get("/") {
        val generateRequest = call.receive<GenerateRequest>()
        val upsertDbJob = launch {
            val requestCollection = db.getCollection<RequestData>("request")
            val ip = call.request.origin.remoteAddress
            val content = generateRequest.content
            val filter = Filters.eq("_id", ip)
            val update = Updates.push(RequestData::qrContents.name, content)
            val options = FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER)
            try {
                requestCollection.findOneAndUpdate(filter, update, options)
                    ?: requestCollection.insertOne(RequestData(ip, listOf(content)))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val generateService = GenerateService()
        val file = async(Dispatchers.Default) { generateService(generateRequest) }
        val respondJob = launch {
            call.respondFile(file.await())
        }
        upsertDbJob.join()
        respondJob.join()
    }
}