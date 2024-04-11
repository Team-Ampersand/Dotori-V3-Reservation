package ampersand.reservationservice.global.webclient.member

import ampersand.reservationservice.domain.member.Member
import ampersand.reservationservice.domain.member.MemberApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.util.UriComponentsBuilder

@Component
class MemberApiAdapter(
    private val webClient: WebClient,
    @Value("\${service.member.host}")
    private val memberHost: String,
    @Value("\${service.scheme}")
    private val scheme: String,
) : MemberApi {

    override suspend fun getMemberById(id: Long): Member {
        val uri = UriComponentsBuilder.fromPath("/member/id/{memberId}")
            .scheme(scheme)
            .host(memberHost)
            .buildAndExpand(id)
            .toUri()

        return webClient.get()
            .uri(uri)
            .retrieve()
            .onStatus({ it.is4xxClientError || it.is5xxServerError }, { throw RuntimeException() })
            .awaitBody()
    }

}