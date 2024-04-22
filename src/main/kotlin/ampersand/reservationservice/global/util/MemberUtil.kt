package ampersand.reservationservice.global.util

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class MemberUtil {

    fun getCurrentMemberId(): Long =
        SecurityContextHolder.getContext().authentication.name.toLong()

    fun getUserAuthority(): List<String> {
        val authorities = SecurityContextHolder.getContext().authentication.authorities

        return authorities.stream()
            .map(GrantedAuthority::authority)
            .toList()
    }

}
