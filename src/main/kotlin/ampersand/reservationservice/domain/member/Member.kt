package ampersand.reservationservice.domain.member

import ampersand.reservationservice.global.internal.Authority

data class Member(
    val id: Long,
    val name: String,
    val email: String,
    val grade: Int?,
    val classNum: Int?,
    val number: Int?,
    val profileImage: String?,
    val authority: Authority?
)