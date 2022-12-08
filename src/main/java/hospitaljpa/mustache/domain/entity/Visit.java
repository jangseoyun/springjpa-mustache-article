package hospitaljpa.mustache.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_visit")
public class Visit extends BaseEntity{

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

    public Visit(Hospital hospital, Users users, String disease, float amount) {
        this.hospital = hospital;
        this.users = users;
        this.disease = disease;
        this.amount = amount;
    }

}
