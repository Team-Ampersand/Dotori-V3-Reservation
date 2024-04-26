package ampersand.reservationservice.global.producer

import kotlinx.coroutines.future.await
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class ReservationProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    suspend fun applySelfStudy(memberId: String) {
        kafkaTemplate.send("apply-selfstudy", memberId).await()
    }

    suspend fun cancelSelfStudy(memberId: String) {
        kafkaTemplate.send("cancel-selfstudy", memberId).await()
    }

    suspend fun applyMassage(memberId: String) {
        kafkaTemplate.send("apply-massage", memberId).await()
    }

    suspend fun cancelMassage(memberId: String) {
        kafkaTemplate.send("cancel-massage", memberId).await()
    }
}