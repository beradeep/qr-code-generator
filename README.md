# QR Code Generator API
_A REST api QR Code Generator app._

## `GET:` Generate QR Request
https://qr-code-generator-production-a2a6.up.railway.app/

Send a `get` request to the above url with a raw (json) body a follows.

### Request Body 
> [!NOTE]
> The request body must contain a single JSON object with the following keys:

| Key: type | Value |
| --- | --- |
| content: String | Text to be encoded in the QR |
| shape: String | "SQUARE" / "CIRCLE" / "ROUNDED_SQUARE" |
| color: Int | RGBA int of QR code color |
| bgColor: Int | RGBA int of bg color of QR |
| logo: ByteArray? | ByteArray of logo image in the center; `null` if none |
| logoSize: Int | Size of logo |
| size: Int | Size of QR code |

> [!NOTE]
> Default values of each key:

| Key | Default Value |
| --- | --- |
| content | No default |
| shape | `"SQUARE"` |
| color | `-16777216` |
| bgColor | `-1` |
| logo | `null` |
| logoSize | `0` |
| size | `12` |

### Example
#### Request
`get`  https://qr-code-generator-production-a2a6.up.railway.app/ 
```json
{
    "content": "hey, how are you",
    "shape": "SQUARE",
    "color": -11193345,
    "bgColor": -1,
    "logo": null,
    "logoSize": 2,
    "size": 8
}
```
#### Response
![download (6)](https://github.com/beradeep/qr-code-generator/assets/124783808/cf9bfda6-a94b-44f9-aeda-1547cf15f184)

## Acknowledgements
> g0dkar/qrcode-kotlin - https://github.com/g0dkar/qrcode-kotlin
