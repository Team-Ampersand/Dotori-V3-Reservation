package ampersand.reservationservice.domain.selfstudy.util

import ampersand.reservationservice.domain.member.Member
import ampersand.reservationservice.global.error.ReservationException
import ampersand.reservationservice.global.internal.SelfStudyStatus
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class SelfStudyCheckUtil {
    fun isSelfStudyStatusCan(member: Member){
        if (member.selfStudyStatus != SelfStudyStatus.CAN)
            throw ReservationException("already apply self study", HttpStatus.CONFLICT)
    }

}