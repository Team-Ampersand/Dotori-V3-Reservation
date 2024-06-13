package ampersand.reservationservice.domain.member

import ampersand.reservationservice.global.error.ReservationException
import ampersand.reservationservice.global.internal.Authority
import ampersand.reservationservice.global.internal.MassageStatus
import ampersand.reservationservice.global.internal.SelfStudyStatus
import org.springframework.http.HttpStatus

data class Member(
    val id: Long,
    val name: String,
    val email: String,
    val grade: Int?,
    val classNum: Int?,
    val number: Int?,
    val profileImage: String?,
    val authority: Authority?,
    var selfStudyStatus: SelfStudyStatus,
    var massageStatus: MassageStatus

) {

    fun isSelfStudyStatusCan(member: Member){
        if (member.selfStudyStatus != SelfStudyStatus.CAN)
            throw ReservationException("already apply self study", HttpStatus.CONFLICT)
    }

}