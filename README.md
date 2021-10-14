# 특정 고객 거래내역 조회 서비스 개발
> 데이터를 이용해 상황에 맞는 거래내역, 고객, 지점정보 조회


## 개발 프레임워크

OS X & 리눅스:

```sh
npm install my-crazy-module --save
```

윈도우:

```sh
edit autoexec.bat
```

## 문제 해결 방법

모든 개발 의존성 설치 방법과 자동 테스트 슈트 실행 방법을 운영체제 별로 작성합니다.

```sh
make install
npm test
```

## 빌드 및 실행 방법
* gradle
```sh
  1. /project 경로 이동
  2. ./gradlew.bat build [Window] / ./gradlew build [macOS]
  3. cd build
  4. cd libs -> sample-0.0.1-SNAPSHOT.jar 확인
  5. java -jar sample-0.0.1-SNAPSHOT.jar
```

