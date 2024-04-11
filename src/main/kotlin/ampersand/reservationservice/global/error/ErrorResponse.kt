package ampersand.reservationservice.global.error

data class ErrorResponse(
    val errorMessage: String,
    val status: Int
)