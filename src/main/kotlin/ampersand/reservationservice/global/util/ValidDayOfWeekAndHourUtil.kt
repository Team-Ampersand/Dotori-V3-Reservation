package ampersand.reservationservice.global.util

import ampersand.reservationservice.global.error.ReservationException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.time.DayOfWeek
import java.time.LocalDateTime

@Component
class ValidDayOfWeekAndHourUtil(
    private val currentTime: LocalDateTime? = null
) {
    fun validateApply() {
        val currentTime = currentTime ?: LocalDateTime.now()
        val dayOfWeek = currentTime.dayOfWeek
        val hour = currentTime.hour

        if (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)
            throw ReservationException("not self study apply day", HttpStatus.CONFLICT)

        if (hour != 20)
            throw ReservationException("not self study apply hour", HttpStatus.CONFLICT)
    }

}