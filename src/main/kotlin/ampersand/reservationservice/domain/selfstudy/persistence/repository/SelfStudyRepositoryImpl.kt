package ampersand.reservationservice.domain.selfstudy.persistence.repository

import ampersand.reservationservice.domain.selfstudy.persistence.SelfStudyEntity
import ampersand.reservationservice.domain.selfstudy.persistence.port.SelfStudyRepositoryPort
import com.linecorp.kotlinjdsl.ReactiveQueryFactory
import com.linecorp.kotlinjdsl.query.HibernateMutinyReactiveQueryFactory
import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.singleQueryOrNull
import org.springframework.stereotype.Repository

@Repository
class SelfStudyRepositoryImpl(
    private val reactiveQueryFactory: HibernateMutinyReactiveQueryFactory,
) : SelfStudyRepositoryPort {

    override suspend fun findById(id: Long): SelfStudyEntity? {
        val selfStudy = reactiveQueryFactory.withFactory { _, queryFactory ->
            queryFactory.findById(id)
        }

        return selfStudy
    }

    private suspend fun ReactiveQueryFactory.findById(id: Long): SelfStudyEntity? {
        return this.singleQueryOrNull<SelfStudyEntity> {
            select(entity(SelfStudyEntity::class))
            from(entity(SelfStudyEntity::class))
            where(
                col(SelfStudyEntity::id).equal(id)
            )
        }
    }

}