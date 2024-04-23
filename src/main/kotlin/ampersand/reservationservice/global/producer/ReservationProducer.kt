package ampersand.reservationservice.global.producer

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class ReservationProducer(
    private val kafkaTemplate: KafkaTemplate<String, Long>
) {

    fun applySelfStudy(memberId: Long) {
        kafkaTemplate.send("apply-selfstudy", memberId)
    }

    fun cancelSelfStudy(memberId: Long) {
        kafkaTemplate.send("cancel-selfstudy", memberId)
    }

    fun applyMassage(memberId: Long) {
        kafkaTemplate.send("apply-massage", memberId)
    }

    fun cancelMassage(memberId: Long) {
        kafkaTemplate.send("cancel-massage", memberId)
    }

}