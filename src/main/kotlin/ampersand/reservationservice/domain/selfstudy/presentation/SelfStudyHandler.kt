package ampersand.reservationservice.domain.selfstudy.presentation

import ampersand.reservationservice.domain.selfstudy.application.SelfStudyService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait
import java.net.URI

@Component
class SelfStudyHandler(
    private val selfStudyService: SelfStudyService
) {

    suspend fun applySelfStudy(serverRequest: ServerRequest): ServerResponse {
        selfStudyService.applySelfStudy()

        return ServerResponse.created(URI("/reservation/self-study")).buildAndAwait()
    }

}