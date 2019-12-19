# 02 - `이클립스 IDE`로 프로젝트 다루기

## 학습 목표

- `그레이들`을 이용하여 `이클립스 IDE`용 프로젝트로 전환할 수 있다.
- `이클립스` 워크스페이스로 프로젝트를 가져올 수 있다.
- `이클립스`에서 빌드하고 실행할 수 있다.

## 실습 소스 및 결과

- ~/git/SimpleWebProject/MyProject/build.gradle (변경)

이클립스 IDE로 import 한 후 애플리케이션을 실행한다.

## 실습

### 작업1) 그레이들의 빌드 스크립트 파일에 이클립스 플러그인을 추가하라.

build.gradle 파일에 'eclipse' 플러그인을 추가한다.

```
plugins {
    id 'java'
    id 'application'
    id 'eclipse'     -- 이클립스 플러그인,이클립스 IDE 관련 작업을 수행할 수 있는 명령들로 구성
}
```

### 작업2) 이클립스 IDE에서 사용할 프로젝트 설정 파일을 생성하라.

```
// 이클립스에서 사용할 프로젝트 설정파일 생성(.class, .project, settings)
// 위의 id 'eclipse' 주석처리하면 사용하지 못할뿐더러 git tasks --all에서 사라짐
// gradle eclipse 명령 수행하여 이클립스 관련 파일 생성 안하면 이클립스 - import - exsisting 에서 프로젝트 추가 불가능
// 주의) 특정 개발 툴에 종속되는 프로젝트를 만들면 안된다.(ex 이클립스, 인텔리제이에서 프로젝트 생성 x)
[~/git/SimpleWebProject/MyProject]$ gradle eclipse
```

### 작업3) 이클립스 IDE의 워크스페이스로 프로젝트를 가져와라.

이클립스 워크스페이스로 가져온다.

### 작업4) 이클립스 IDE에서 프로젝트를 실행하라.

이클립스 메뉴를 통해 App 클래스를 실행한다.


## 추가사항, 이클립스 설정
window - preferences에서 설정
General - Appearance - Theme - Dark
General - Editors - Text Editors에서 tab width 2, show print margin column 100, show line numbers, insert spaces for tabs, show whitespace characters 설정
General - Workspace - Test file encoding - UTF-8
General - Workspace - New text file line delimiter - 필요에 따라 설정
Java - Code style - Formatter - Google 스타일.xml로 변경
Java - Installed JREs - graalvm 확인, 이후 필요하면 그냥 jdk 설정하는법 찾아볼 것
