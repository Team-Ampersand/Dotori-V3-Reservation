package ampersand.reservationservice.global.util

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Component

@Component
class MemberUtil {

    suspend fun getCurrentMemberId(): Long =
        ReactiveSecurityContextHolder.getContext().awaitSingle().authentication.name.toLong()

//    suspend fun getUserAuthority(): List<String> {
//        val authorities = ReactiveSecurityContextHolder.getContext().awaitSingle().authentication.authorities
//
//        return authorities.stream()
//            .map(GrantedAuthority::authority)
//            .toList()
//    }

}
