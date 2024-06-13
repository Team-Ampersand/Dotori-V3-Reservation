package ampersand.reservationservice.domain.selfstudy.presentation

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class SelfStudyRouter {

    @Bean
    fun selfStudyBaseRouter(selfStudyHandler: SelfStudyHandler) = coRouter {
        "/self-study".nest {
            contentType(MediaType.APPLICATION_JSON)
            POST("", selfStudyHandler::applySelfStudy)
        }
    }
}