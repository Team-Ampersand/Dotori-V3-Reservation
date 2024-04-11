package ampersand.reservationservice.global.error

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets

class GlobalErrorFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val result = runCatching {
            filterChain.doFilter(request, response)
        }

        result.onFailure { e ->
            when (e) {
                is ReservationException -> setErrorResponse(e.errorMessage, e.status , response)
                else -> {
                    if (e.cause is ReservationException) {
                        val exception = (e.cause as ReservationException)
                        setErrorResponse(exception.errorMessage, exception.status, response)
                    } else {
                        e.printStackTrace()
                        setErrorResponse("Internal Server Error - Reservation Service", HttpStatus.INTERNAL_SERVER_ERROR, response)
                    }
                }
            }
        }
    }

    private fun setErrorResponse(errorMessage: String, status: HttpStatus, response: HttpServletResponse) {
        response.status = status.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = StandardCharsets.UTF_8.name()
        response.writer.write(
            objectMapper.writeValueAsString(
                ErrorResponse(errorMessage, status.value())
            )
        )
    }
}