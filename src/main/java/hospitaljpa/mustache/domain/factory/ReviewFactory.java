package hospitaljpa.mustache.domain.factory;

import hospitaljpa.mustache.domain.dto.ReviewDto;
import hospitaljpa.mustache.domain.entity.Hospital;
import hospitaljpa.mustache.domain.entity.HospitalReview;

public class ReviewFactory {
    public static HospitalReview toReviewEntity(ReviewDto reviewDto, Hospital hospital) {
        return new HospitalReview(
                  hospital
                , reviewDto.getTitle()
                , reviewDto.getComment()
                , reviewDto.getUserName());
    }

    public static ReviewDto toReviewDto(HospitalReview hospitalReview) {
        return new ReviewDto(
                  hospitalReview.getId()
                , hospitalReview.getHospital().getId()
                , hospitalReview.getTitle()
                , hospitalReview.getReview()
                , hospitalReview.getUsersName());
    }

}
