package ampersand.reservationservice.global.filter

import ampersand.reservationservice.global.internal.Authority
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.filter.OncePerRequestFilter

class AuthenticationFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val memberId: String? = request.getHeader("Request-Member-Id")
        val authority: Authority? = request.getHeader("Request-Member-Authority")?.run { Authority.valueOf(this) }
        val memberAuthorities: List<String>? = request.getHeader("Request-Member-Authorities")?.run { listOf(this) }

        if (memberId == null || authority == null || memberAuthorities == null) {
            filterChain.doFilter(request, response)
            return
        }

        val authorities: MutableCollection<SimpleGrantedAuthority> = ArrayList()
        for (memberAuthority in memberAuthorities) {
            authorities.add(SimpleGrantedAuthority(memberAuthority))
        }

        authorities.add(SimpleGrantedAuthority("ROLE_${authority.name}"))
        val userDetails: UserDetails = User(memberId, "", authorities)
        val authentication: Authentication =
            UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
        SecurityContextHolder.getContext().authentication = authentication

        filterChain.doFilter(request, response)
    }
}