build.gradlebuild.gradle과 콘솔 출력 다루기

## 학습 목표

- 다양한 유형의 값을 콘솔로 출력할 수 있다.

## 실습 소스 및 결과

- src/main/java/com/kyh/market/App.java 변경
- src/main/java/com/kyh/market/App2.java 추가
- src/main/java/com/kyh/market/App3.java 추가
- src/test/java/com/kyh/market/AppTest.java 삭제

## 실습

### 작업1) 한 개의 물품 정보를 출력하라.

App.java 실행 결과

```
번호: 1
물품명: Mac Pro 2016
판매자 : kyh
분류 : labtop
설명: 버전 혹은 수명 등의 상태
가격 : 2,000,000
```

### 작업2) 한 명의 회원 정보를 출력하라.

App2.java 실행 결과

```
번호: 1
이름: kyh
이메일: kyh@gmail.com
주소 : 대한민국
암호: 1234
사진: hong.png
전화: 010-1111-1111
가입일: 2019-01-01
```

### 작업3) 한 개의 게시글을 출력하라.

App3.java 실행 결과

```
번호: 1
내용: 게시글입니다.
작성일: 2019-01-01
조회수: 0
```

### 작업4) AppTest 클래스를 삭제하라.

```
src/test/java/com/kyh/market/AppTest.java 삭제
```

## 추가사항
gradle에 eclipse 연동 이후 이클립스에서 작업할 때, 
코드는 기존의 src/ 가 아니라 package explorer 상단의 src/main/java 또는 src/test/java에서 확인