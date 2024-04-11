package ampersand.reservationservice.domain.member

interface MemberApi {
    suspend fun getMemberById(id: Long): Member
}