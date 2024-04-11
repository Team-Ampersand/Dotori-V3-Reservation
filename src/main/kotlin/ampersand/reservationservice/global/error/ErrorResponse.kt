package ampersand.reservationservice.global.error

data class ErrorResponse(
    val errorMessage: String
) {
    companion object {
        fun of(cause: Throwable): ErrorResponse =
            ErrorResponse(cause.message!!)
    }
}
