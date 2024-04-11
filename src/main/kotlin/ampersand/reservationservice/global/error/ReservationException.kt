package ampersand.reservationservice.global.error

import org.springframework.http.HttpStatus

data class ReservationException(val errorMessage: String, val status: HttpStatus): RuntimeException(errorMessage)
