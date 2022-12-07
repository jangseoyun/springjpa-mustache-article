package hospitaljpa.mustache.domain.factory;

import hospitaljpa.mustache.domain.dto.VisitCreateRequest;
import hospitaljpa.mustache.domain.dto.VisitCreateResponse;
import hospitaljpa.mustache.domain.entity.Hospital;
import hospitaljpa.mustache.domain.entity.Users;
import hospitaljpa.mustache.domain.entity.Visit;

public class VisitCreateFactory {

    public static Visit toVisitEntity(Hospital setHospital, Users setUser, VisitCreateRequest visitCreateRequest) {
        return new Visit(
                null
                , setHospital
                , setUser
                , visitCreateRequest.getDisease()
                , visitCreateRequest.getAmount()
                , null);
    }

    public static VisitCreateResponse toVisitResponse(Visit getVisit) {
        return new VisitCreateResponse(
                getVisit.getId()
                , getVisit.getHospital().getHospitalName()
                , getVisit.getUsers().getUsername()
                , getVisit.getDisease()
                , getVisit.getAmount());
    }

}
