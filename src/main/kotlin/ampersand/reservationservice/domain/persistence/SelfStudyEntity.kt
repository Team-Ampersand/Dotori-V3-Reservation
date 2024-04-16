package ampersand.reservationservice.domain.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "self_study")
class SelfStudyEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "self_study_id")
    val id: Long = 0,

    @Column(name = "self_study_limit")
    val limit: Int,

    @Column(name = "self_study_count")
    val count: Int

)