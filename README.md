# MTE(MoveToEC2)

# 실행 환경
- java version jdk17 이상

# 빌드
```
C:\> mvn clean package
```

# 실행
```
C:\> java -DpropertyPath=c:\path\to\the\config\config.properties -jar mte-1.0.0.SNAPSHOT.jar
```
# config.properties
```
# database
jdbc.mysql.t.port=3306
jdbc.mysql.t.url={DB URL}
jdbc.mysql.t.username={DB NAME}
jdbc.mysql.t.password={DB PASSWORD}
```