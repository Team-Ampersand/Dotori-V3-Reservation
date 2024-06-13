package ampersand.reservationservice.domain.selfstudy.persistence

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tbl_self_study")
class SelfStudyEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "self_study_id")
    val id: Long = 0,

    @Column(name = "self_study_limit")
    var limit: Int,

    @Column(name = "self_study_count")
    var count: Int

) {

    fun addCount() {
        this.count++
    }

}