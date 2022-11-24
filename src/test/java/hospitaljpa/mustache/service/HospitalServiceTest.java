package hospitaljpa.mustache.service;

import hospitaljpa.mustache.domain.dto.HospitalResponse;
import hospitaljpa.mustache.domain.entity.Hospital;
import hospitaljpa.mustache.domain.repository.HospitalJpaRepository;
import hospitaljpa.mustache.domain.repository.ReviewJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@DisplayName("HospitalServiceTest")
class HospitalServiceTest {

    private HospitalJpaRepository hospitalJpaRepository = Mockito.mock(HospitalJpaRepository.class);
    private ReviewJpaRepository reviewJpaRepository = Mockito.mock(ReviewJpaRepository.class);
    private HospitalService hospitalService;

    @BeforeEach
    void setUp() {
        hospitalService = new HospitalService(hospitalJpaRepository, reviewJpaRepository);
    }

    @DisplayName("3일때 폐업, 13일때 영업중")
    @Test
    void businessStatusName3() {
        Hospital hospital3 = Hospital.builder()
                .id(718457L)
                .businessStatusCode(3)
                .build();

        Mockito.when(hospitalJpaRepository.findById(any()))
                .thenReturn(Optional.of(hospital3));

        HospitalResponse hospital = hospitalService.getHospital(71857L);
        Assertions.assertEquals("폐업",hospital.getBusinessStatus());
    }
}