package hospitaljpa.mustache.domain.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class ReviewResponse {
    List<ReviewDto> reviewList;
    Long reviewTotalCnt;

    public ReviewResponse(List<ReviewDto> reviewList, Long reviewTotalCnt) {
        this.reviewList = reviewList;
        this.reviewTotalCnt = reviewTotalCnt;
    }

}
