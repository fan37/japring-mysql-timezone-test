# Environment setting

'''docker run -e MYSQL_ROOT_PASSWORD=1234 -d -p 3306:3306 mysql'''

##### Run mysql with docker
```
docker run -e MYSQL_ROOT_PASSWORD=1234 -d -p 3306:3306 mysql
```

##### Create database
```
docker exec -it [container] bash

mysql -u root -p

create database mydb1
```



# Test result

#### OffsetDateTime 테스트
- serverTimezone 적용 안해도 잘 들어감. (어플리케이션에서 OffsetDateTime KST로 생성된 값이 DB에 UTC로 저장되었다가 UTC로 조회됨.)
- serverTimezone에 UTC, Asia/Seoul 적용해도 잘 들어감.(useLegacyDatetimeCode이 true/false 상관 없었음)

#### LocalDateTime 테스트 
- serverTimezone에 UTC를 적용해줘야만 제대로 변환되어 들어감.
- serverTimezone을 지정하지 않거나, Asia/Seoul로 하면 -9를 안하고 그대로 저장해서 결과적으로 9시간 빨라진 시간으로 변경이 되어 버림. 
