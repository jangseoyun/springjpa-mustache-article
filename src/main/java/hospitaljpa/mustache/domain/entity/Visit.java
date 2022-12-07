package hospitaljpa.mustache.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "t_visit")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @JsonIgnore
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users users;

    @Column(name = "disease")
    private String disease;

    @Column(name = "amount")
    private float amount;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    public Visit(Hospital hospital, Users users, String disease, float amount) {
        this.hospital = hospital;
        this.users = users;
        this.disease = disease;
        this.amount = amount;
    }
}
