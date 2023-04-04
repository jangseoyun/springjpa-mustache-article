# 전국 병원 위치 데이터 파싱
> [공공데이터](https://www.data.go.kr/data/15045024/fileData.do) 의료기관 데이터를 파싱하고 해당 데이터를 사용하여 전국 병원 위치 및 방문자 기능 구현
>
<img width="793" alt="image" src="https://user-images.githubusercontent.com/94329274/229743241-e0391e35-3069-4f06-834d-4781085a0f2a.png">

---

# 1. 개발 환경
🟧 BackEnd (Mac)
- Language/Skills: JAVA 11<br>
- Framework: Springboot 2.7.5, Junit5<br>
- Build: Gradle 7.5.1<br>
- ORM: Spring Data JPA <br>
- DB: Mysql<br>
- Library: Lombok, SpringSecurity, JWT<br>
- API 문서 자동화: [Swagger](http://ec2-43-201-107-105.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/)<br>

🟧 FrontEnd: HTML / CSS / Mustache / bootstrap<br>
🟧 Server: AWS / EC2<br>
- [AWS EC2 배포](http://ec2-43-201-107-105.ap-northeast-2.compute.amazonaws.com:8080/hospital)
---

# 2. ERD
<img width="452" alt="image" src="https://user-images.githubusercontent.com/94329274/228175266-111fc1ad-5836-4523-aee6-18f762603745.png">

---

# 3. 추출 데이터 상세 내역
> [📝 구현 기능 코드 정리](https://velog.io/@may_yun/SpringJPA-CRUD-API-%EB%A7%8C%EB%93%A4%EA%B8%B0)

- license_date: 인허가일자<br>
- business_status: 영업상태(1: 영업/정상 2: 휴업 3: 폐업 4: 취소/말소영업상태구분)<br>
- business_status_code: 영업상태코드 (2: 휴업 3: 폐업 13: 영업중)<br>
- business_type_name: 업태구분명<br>
- full_address: 소재지 전체 주소<br>
- healthcare_provider_cnt: 의료인 수<br>
- hospital_name: 병원명<br>
- management_number: 관리번호<br>
- open_local_government_code: 개방자치단체코드<br>
- open_service_name: 개방서비스명<br>
- patient_room_cnt: 입원실 수 <br>
- phone: 소재지 전화<br>
- road_name_address: 도로명 주소<br>
- total_area_size: 총 면적<br>
- total_number_of_beds: 병상 수<br>

---

# 4. 구현 기능

## 4-1) 약 12 만건의 데이터 파싱
### [🔗Data Parser Project Link](https://github.com/jangseoyun/data-parser-tdd) 
- `TSV 파일`을 [Tab]으로 분리하여 필요한 데이터만 파싱.
  <br>( CSV 파일의 경우 ','로 데이터를 분리하기 때문에 데이터 중간에 ','가 존재하는 경우 원하는 데이터 단위로 분리하기 어렵기 때문에 CSV 파일을 TSV 파일로 변환.)

## 4-2) 구현 기능 
- MVC 패턴 / Rest API 구현 
- SpringSecurity 회원가입 구현
- JWT 로그인 구현
- 병원 전체 리스트 (Pageable 페이징)
- 병원명/주소 키워드 검색
- 유저 리뷰 작성 기능
- 작성된 리뷰 총 갯수 카운트 기능
- 행정자치코드(시군구) 카테고리 구분 기능
- 특정 유저 / 병원 방문 기록 등록, 조회

---

# 5. 코드 설명

## 5-1) Slice를 통한 paging 성능 개선

## 5-2) Factory 패턴을 통한 객체 생성 분리

## 5-3) FetchType.LAZY를 통한 N+1 문제 해결

## 5-4) BaseEntity를 통한 소스 코드 재사용

## 5-5) ExceptionHandler 공통 에러처리

## 5-6) springSecurity 적용 

---

# 6. UI
- Main
<img width="1692" alt="image" src="https://user-images.githubusercontent.com/94329274/228158794-27d0c55d-70d4-48c6-bfd7-a922b9de409c.png">

- 사용자 review
<img width="1727" alt="image" src="https://user-images.githubusercontent.com/94329274/228159587-6dd76ef9-6c43-4955-8877-4496ec878d8a.png">

- review count
  <img width="1726" alt="image" src="https://user-images.githubusercontent.com/94329274/228161002-986ef7ed-313a-4015-95aa-fe61f02c5219.png">

- Swagger (API 문서화)
<img width="1728" alt="image" src="https://user-images.githubusercontent.com/94329274/228159320-3554b918-d90b-4f4a-b689-5be521842b67.png">

