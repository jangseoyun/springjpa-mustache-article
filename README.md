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
- API 문서 자동화: [Swagger Link](http://ec2-43-201-107-105.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/)<br>

🟧 FrontEnd: HTML / CSS / Mustache / bootstrap<br>
🟧 Server: AWS / EC2 / Docker<br>
🟧 CI/CD: github Action
- [AWS EC2 배포 Link](http://ec2-43-201-107-105.ap-northeast-2.compute.amazonaws.com:8080/hospital)
---

# 2. ERD
<img width="452" alt="image" src="https://user-images.githubusercontent.com/94329274/228175266-111fc1ad-5836-4523-aee6-18f762603745.png">

---

# 3. 추출 데이터 상세 내역
> [📝 구현 기능 코드 정리 Link](https://velog.io/@may_yun/SpringJPA-CRUD-API-%EB%A7%8C%EB%93%A4%EA%B8%B0)

|**데이터 항목** | **데이터 명** | **상세**                                          |
|:-----------------------:|:---------:|:------------------------------------------------|
|     open_service_name     |  개방서비스명   |                                                 |
| open_local_government_code | 개방자치단체코드  |                                                 |
|    management_number     |   관리번호    |                                                 |
|       license_date       |   인허가일자   |                                                 |
|     business_status      |   영업상태    | 1: 영업/정상 <br>2: 휴업<br> 3: 폐업<br> 4: 취소/말소영업상태구분 |
|   business_status_code    |  영업상태코드   | 2: 휴업<br> 3: 폐업<br> 13: 영업중                     |
|          phone          |  소재지 전화   |                                                 |
|       full_address       |  소재지 주소   |                                                 |
|     road_name_address     |소재지 도로명 주소 |                                                 |
|      hospital_name       |    병원명    |                                                 |
|    business_type_name     |   업태구분명   |                                                 |
| healthcare_provider_cnt |   의료인 수   |                                                 |
|    patient_room_cnt     |   입원실 수   |                                                 |
|    total_number_of_beds    |   병상 수    |                                                 |
|      total_area_size      |   총 면적    |                                                 |


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
페이징을 위해 Pageable 인터페이스를 사용하여 Slice, page 객체 중 어느 것으로 반환받을지 고민해 보았다.<br>

본 프로젝트는 12만 건의 데이터를 `한 테이블`의 데이터를 조회하는 것으로 join이 들어가는 쿼리보다 성능 최적화의 효과는 적겠지만<br>
사용하지 않는 `totalCount 쿼리가 실행되지 않도록 Slice를 사용`하여 성능 낭비를 줄였다.<br>

## 5-2) 정적 Factory 메서드를 사용하여 객체 생성 분리
역할에 따른 객체의 생성을 담당하는 팩토리 클래스를 분리하여 코드 가독성 상승과 객체를 매번 만들지 않도록 자원 낭비를 줄였다.<br>
- `메서드 오버로딩`으로 모든 객체 생성에 불필요한 클래스 생성을 줄이고, <br>
static 메서드를 통해 객체 생성 시 `매번 새로운 객체를 생성하지 않도록` 로직의 반복을 줄였다.<br>
- 네이밍에 따라 반환될 `객체의 역할을 예측`할 수 있기 때문에 코드 가독성을 높여줬다.<br>
- `객체 생성을 제한하기 위해서는` 역할을 작은 단위로 나누어 팩토리 클래스를 생성하고 생성자의 접근 제한자를 private로 설정하는 방법을 고려할 수 있을 것 같다.<br>

## 5-3) FetchType.LAZY를 통한 N+1 문제 해결
ManyToOne, OneToOne 관계는 `기본값`이 즉시 로딩으로 설정되어 있다.<br>
따라서 `지연로딩` 설정을 통해 불필요한 Join 쿼리 실행을 방지하고 `N + 1 문제`로 인한 성능 낭비 문제를 예방했다.<br>

## 5-4) BaseEntity를 통한 소스 코드 재사용
스프링부트 `Auditing` 기능을 통해 객체 생성과 수정일을 모든 엔티티에 주입하지 않고 BaseEntity를 `상속`받아 사용하도록 하여<br>
코드 중복을 줄이고 유지보수 관점에서 편리하여 시간 단축을 할 수 있도록 구현하였다.<br>

## 5-5) customException 예외처리
- `ExceptionHandler`를 통해 전역 예외처리 가능<br>
- RuntimeException 이 외 사용자 시나리오 중 발생할 수 있는 예외처리를 Enum 타입으로 지정하여
클라이언트에게 어떤 이유로 에러가 났는지 HttpStatus 코드와 메시지를 반환<br>
- 아이디 및 비밀번호 일치 여부는 `보안`을 위해 "id or pw" 메세지 반환

| Http Status Code | Error Message | Description |
|:----------------:|:-------------:|:-----------:|
|         409         |       DUPLICATED_USER_NAME        |      User name is duplicated       |
|          404           |       NOT_FOUND        |      not found id or pw       |
|          400           |       INVALID_PASSWORD        |      not found id or pw       |

## 5-6) springSecurity / JWT 적용
- springSecurity를 통해 특정 리소스(URL)에 권한을 가진 사용자만 접근이 가능하도록 권한을 설정<br>
- BCryptPasswordEncoder에서 제공하는 메서드를 통해 비밀번호 암호화를 통해 DB 데이터 보안 향상<br>

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

