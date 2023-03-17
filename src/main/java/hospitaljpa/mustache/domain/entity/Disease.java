package hospitaljpa.mustache.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_disease")
public class Disease {
    @Id
    private String code;
    private String name;
}
