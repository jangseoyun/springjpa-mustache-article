select hospital_name
from nation_wide_hospitals hospital
where hospital.road_name_address like '경기도 수원시%'
and hospital.hospital_name like '%피부%';