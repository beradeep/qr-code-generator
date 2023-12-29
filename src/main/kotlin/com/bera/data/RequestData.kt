package com.bera.data

import org.bson.codecs.pojo.annotations.BsonId

data class RequestData(
    @BsonId
    val ip: String,
    val qrContents: List<String>
)
