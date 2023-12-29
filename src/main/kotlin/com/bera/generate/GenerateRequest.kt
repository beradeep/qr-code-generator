package com.bera.generate

import kotlinx.serialization.Serializable

@Serializable
data class GenerateRequest(
    val content: String,
    val shape: String = "SQUARE",
    val color: Int = -16777216,
    val bgColor: Int = -1,
    val logo: ByteArray? = null,
    val logoSize: Int = 0,
    val size: Int = 12,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GenerateRequest

        if (content != other.content) return false
        if (shape != other.shape) return false
        if (color != other.color) return false
        if (bgColor != other.bgColor) return false
        if (logo != null) {
            if (other.logo == null) return false
            if (!logo.contentEquals(other.logo)) return false
        } else if (other.logo != null) return false
        if (logoSize != other.logoSize) return false
        if (size != other.size) return false

        return true
    }

    override fun hashCode(): Int {
        var result = content.hashCode()
        result = 31 * result + shape.hashCode()
        result = 31 * result + color
        result = 31 * result + bgColor
        result = 31 * result + (logo?.contentHashCode() ?: 0)
        result = 31 * result + logoSize
        result = 31 * result + size
        return result
    }
}