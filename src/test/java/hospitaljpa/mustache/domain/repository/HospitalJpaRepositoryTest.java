package hospitaljpa.mustache.domain.repository;

import hospitaljpa.mustache.domain.entity.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@DisplayName("HospitalJpaRepositoryTest")
class HospitalJpaRepositoryTest {

    @Autowired
    HospitalJpaRepository hospitalJpaRepository;

    @DisplayName("보건소 보건지소 데이터가 잘 나오는지 확인 ")
    @Test
    void findByBusinessNameIn() {
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건지소");
        inClues.add("보건진료소");
        List<Hospital> hospitals = hospitalJpaRepository.findByBusinessTypeNameIn(inClues);
        for (var hospital :
                hospitals) {
            System.out.println(hospital.getHospitalName());
        }

    }

}