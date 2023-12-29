package com.bera.data

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.kotlin.client.coroutine.MongoClient

class DbClient {
    operator fun invoke(uri: String): MongoClient {
        val serverApi = ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build()
        val settings = MongoClientSettings.builder()
            .applyConnectionString(ConnectionString(uri))
            .serverApi(serverApi)
            .build()
        return MongoClient.create(settings)
    }
}