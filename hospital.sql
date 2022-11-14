select hospital_name
from nation_wide_hospitals hospital
where hospital.road_name_address like '경기도 수원시%'
and hospital.hospital_name like '%피부%';

SELECT business_type_name, hospital_name, road_name_address
FROM `likelion-db`.nation_wide_hospitals
where business_type_name in ('보건소', '보건지소')