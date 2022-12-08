package hospitaljpa.mustache.service;

import hospitaljpa.mustache.domain.dto.*;
import hospitaljpa.mustache.domain.entity.Hospital;
import hospitaljpa.mustache.domain.entity.Users;
import hospitaljpa.mustache.domain.entity.Visit;
import hospitaljpa.mustache.domain.factory.VisitCreateFactory;
import hospitaljpa.mustache.domain.repository.HospitalJpaRepository;
import hospitaljpa.mustache.domain.repository.UserJpaRepository;
import hospitaljpa.mustache.domain.repository.VisitJpaRepository;
import hospitaljpa.mustache.exception.ErrorCode;
import hospitaljpa.mustache.exception.HospitalReviewException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitJpaRepository visitJpaRepository;
    private final HospitalJpaRepository hospitalJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public VisitCreateResponse create(VisitCreateRequest visitCreateRequest, String username) {
        Hospital getHospital = hospitalJpaRepository
                .findById(visitCreateRequest.getHospitalId())
                .orElseThrow(() -> new HospitalReviewException(ErrorCode.NOT_FOUND, "해당 병원이 없습니다"));

        Users getUser = userJpaRepository
                .findByUsername(username)
                .orElseThrow(() -> new HospitalReviewException(ErrorCode.NOT_FOUND, "해당 유저가 존재하지 않습니다"));

        Visit visit = VisitCreateFactory.toVisitEntity(getHospital, getUser, visitCreateRequest);
        Visit saveVisitBook = visitJpaRepository.save(visit);
        return VisitCreateFactory.toVisitResponse(saveVisitBook);
    }

    public VisitFindAllResponse findAll() {
        List<Visit> visitList = visitJpaRepository.findAll();
        int count = visitList.size();
        return new VisitFindAllResponse(visitList, count);
    }

    public FindByUserVisitResponse findByVisitsUser(Long userId) {
        List<Visit> findByVisitsUser = visitJpaRepository.findByUsersId(userId);
        return new FindByUserVisitResponse(findByVisitsUser, findByVisitsUser.size());
    }

    public FindByHospitalVisitResponse findBuHospitalVisits(Long hospitalId) {
        List<Visit> findByVisitsHospital = visitJpaRepository.findByHospitalId(hospitalId);
        return new FindByHospitalVisitResponse(findByVisitsHospital, findByVisitsHospital.size());
    }

}
