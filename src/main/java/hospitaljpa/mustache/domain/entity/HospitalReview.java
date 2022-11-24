package hospitaljpa.mustache.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "t_hospital_review")
public class HospitalReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String review;

    @Column(name = "user_name", nullable = false)
    private String usersName;
    // user를 따로 주지않음 가지고올 user 정보가 많지 않다
    // username 유니크로 사용자 고유 아이디로 대체 사용 가능


    public HospitalReview(Hospital hospital, String title, String review, String usersName) {
        this.hospital = hospital;
        this.title = title;
        this.review = review;
        this.usersName = usersName;
    }
}
