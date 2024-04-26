package ampersand.reservationservice.domain.selfstudy.persistence.port

import ampersand.reservationservice.domain.selfstudy.persistence.SelfStudyEntity

interface SelfStudyRepositoryPort {
    suspend fun findById(id: Long): SelfStudyEntity?
}