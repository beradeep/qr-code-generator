package com.bera.generate

import kotlinx.coroutines.coroutineScope
import qrcode.QRCode
import java.io.File

class GenerateService {
    operator fun invoke(generateRequest: GenerateRequest): File {
        val pngBytes = QRCode
            .run {
                when(generateRequest.shape) {
                    Shape.CIRCLE.toString() -> ofCircles()
                    Shape.SQUARE.toString() -> ofSquares()
                    Shape.ROUNDED_SQUARE.toString() -> ofRoundedSquares()
                    else -> { ofSquares() }
                }
            }
            .apply {
                withSize(generateRequest.size)
                withColor(generateRequest.color)
                withBackgroundColor(generateRequest.bgColor)
                withLogo(generateRequest.logo, generateRequest.logoSize, generateRequest.logoSize)
            }
            .build(generateRequest.content)
            .renderToBytes("PNG")
        val file = File("qr-code.png")
        file.writeBytes(pngBytes)
        return file
    }
}
