package ampersand.reservationservice.domain.selfstudy.application

import ampersand.reservationservice.domain.member.MemberApi
import ampersand.reservationservice.domain.selfstudy.persistence.port.SelfStudyRepositoryPort
import ampersand.reservationservice.domain.selfstudy.util.SelfStudyCheckUtil
import ampersand.reservationservice.global.error.ReservationException
import ampersand.reservationservice.global.producer.ReservationProducer
import ampersand.reservationservice.global.util.MemberUtil
import ampersand.reservationservice.global.util.ValidDayOfWeekAndHourUtil
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class SelfStudyServiceImpl(
    private val memberApi: MemberApi,
    private val memberUtil: MemberUtil,
    private val selfStudyRepositoryPort: SelfStudyRepositoryPort,
    private val reservationProducer: ReservationProducer,
    private val validDayOfWeekAndHourUtil: ValidDayOfWeekAndHourUtil,
    private val selfStudyCheckUtil: SelfStudyCheckUtil
) : SelfStudyService {
    override suspend fun applySelfStudy() {
        validDayOfWeekAndHourUtil.validateApply()

        val memberId = memberUtil.getCurrentMemberId()
        val member = memberApi.getMemberById(memberId)

        val selfStudy = selfStudyRepositoryPort.findById(1L)!!

        if (selfStudy.count >= selfStudy.limit) {
            ReservationException("self study count over", HttpStatus.CONFLICT)
        }

        selfStudyCheckUtil.isSelfStudyStatusCan(member)
        selfStudy.addCount()
        reservationProducer.applySelfStudy(memberId.toString())
    }
}