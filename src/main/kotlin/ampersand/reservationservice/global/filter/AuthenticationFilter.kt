package ampersand.reservationservice.global.filter

import ampersand.reservationservice.global.internal.Authority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class AuthenticationWebFilter : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val request = exchange.request

        val memberId = request.headers.getFirst("Request-Member-Id") ?: return chain.filter(exchange)
        val authority = request.headers.getFirst("Request-Member-Authority")?.let { Authority.valueOf(it) }

        if (authority == null) {
            return chain.filter(exchange)
        }

        val authorities = mutableListOf(SimpleGrantedAuthority("ROLE_${authority.name}"))

        val userDetails: UserDetails = User(memberId, "", authorities)
        val authentication: Authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)

        return chain.filter(exchange)
            .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication))
    }
}
