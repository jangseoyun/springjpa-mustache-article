# 🏥 전국 병원 위치 데이터 🏥
[구현 기능 정보](https://velog.io/@may_yun/SpringJPA-CRUD-API-%EB%A7%8C%EB%93%A4%EA%B8%B0)

---

# 개발 환경
🟧 BackEnd (Mac)
- Language/Skills: JAVA 11, Swagger<br>
- Framework: Springboot 2.7.5, Junit5<br>
- Build tool: Gradle<br>
- ORM: JPA <br>
- IDE: IntelliJ<br>
- DB: Mysql<br>
- Library: Lombok<br>

🟧 FrontEnd: HTML / CSS / Mustache / bootstrap<br>
🟧 Server: AWS / EC2<br>
- [배포](http://ec2-3-35-226-64.ap-northeast-2.compute.amazonaws.com:8080/hospital)

---

# 구현 기능
- 데이터 파싱
  - Data Parser : TSV 파일을 [Tab]으로 분리하여 필요한 데이터만 파싱
    - 추출 데이터 상세<br>
개방서비스명, 개방자치단체코드, 관리번호, 인허가일자 <br>
영업상태코드 (2: 휴업 3: 폐업 13: 영업중)<br>
영업상태(1: 영업/정상 2: 휴업 3: 폐업 4: 취소/말소영업상태구분)<br>
소재지 전화, 소재지 전체 주소, 도로명 주소, 병원명<br>
업태구분명, 의료인 수, 입원실 수, 병상 수, 총 면적
- 병원 전체 리스트 (페이징)
- 병원명 검색
- 행정자치코드(시군구) 카테고리 구분 기능

---

# UI

![병원정보 리스트](img.png)

![img_1.png](img_1.png)
