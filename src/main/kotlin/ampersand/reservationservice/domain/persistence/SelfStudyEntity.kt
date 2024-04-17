package ampersand.reservationservice.domain.persistence

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

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