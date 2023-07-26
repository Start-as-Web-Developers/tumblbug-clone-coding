# Tumblbug clone coding   

<br>

## 프로젝트 설명
크라우드 펀딩 사이트인 [텀블벅](https://tumblbug.com/discover)을 클론코딩하는 프로젝트입니다.<br>
회원 가입, 프로젝트 게시 및 공유, 결제 시스템 구현 등을 목표로 하고 있습니다.

<br>

## 계기
대규모 DB를 다루면서, 외부 API와의 연동을 해볼 수 있는 클론코딩 프로젝트로 대상을 물색하여, 많은 수의 회원과 프로젝트를 관리하고 결제 API를 갖춘 텀블벅을 선정했습니다.<br>
특히 텀블벅을 TypeScript와 React를 사용하여 제작해보면서 컴포넌트의 기능 및 구성에 대해서도 탐구하는 것을 목표로 진행했습니다.

<br>

## 화면 구성 및 기능 소개

- 프로젝트 확인 가능한 메인 화면, 필터 적용하여 프로젝트 분류 기능 <br>

<img src="https://user-images.githubusercontent.com/74173976/234723559-8e917e4e-3c66-4fed-9fee-eecccd6e513e.gif" width="100%">

- 회원가입 및 로그인 기능 <br>

<img src="https://github-production-user-asset-6210df.s3.amazonaws.com/45064913/239012541-5d18665d-1f60-4c31-a74c-1a0b3ba7ea90.gif" width="100%">

- 프로젝트 업로드, 프리뷰 <br>

<img src="https://github.com/Start-as-Web-Developers/tumblbug-clone-coding/assets/45064913/e16618b3-8999-4714-b1eb-91b65ee7c0c2" width="100%">


<br>

<!-- 기술 스택 및 정보는 https://simpleicons.org/ 찾으세요! -->
## 기술 스택
### FE

<!-- 
:-- 테이블 좌측 정렬
:-: 테이블 중앙 정렬
--: 테이블 우측 정렬
-->
|Types|Techs|
|:-|:-|
|RunTime|<img src="https://img.shields.io/badge/Node.js-339933?style=flat&logo=Node.js&logoColor=white"/>|
|Framework|<img src="https://img.shields.io/badge/React-61DAFB?style=flat&logo=React&logoColor=white"/>|
|Language|<img src="https://img.shields.io/badge/TypeScript-3178C6?style=flat&logo=TypeScript&logoColor=white"/> <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=JavaScript&logoColor=white"/> <img src="https://img.shields.io/badge/Sass-CC6699?style=flat&logo=Sass&logoColor=white"/>|
|Formatter & Linter|<img src="https://img.shields.io/badge/ESLint-4B32C3?style=flat&logo=ESLint&logoColor=white"/> <img src="https://img.shields.io/badge/Prettier-F7B93E?style=flat&logo=Prettier&logoColor=white"/>|
|Bundler|<img src="https://img.shields.io/badge/Webpack-8DD6F9?style=flat&logo=Webpack&logoColor=white"/>|
|Testing|<img src="https://img.shields.io/badge/Jest-C21325?style=flat&logo=Jest&logoColor=white"/>|

### BE
|Types|Techs|
|:-|:-|
|Language|<img src="https://img.shields.io/badge/java-007396?style=flat&logo=java&logoColor=white">|
|Framework|<img src="https://img.shields.io/badge/spring-6DB33F?style=flat&logo=Spring&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=flat&logo=SpringBoot&logoColor=white">|
|Database| <img src="https://img.shields.io/badge/mariaDB-003545?style=flat&logo=mariaDB&logoColor=white">|
|Infra|<img src="https://img.shields.io/badge/amazonaws-232F3E?style=flat&logo=amazonaws&logoColor=white">|

## 아키텍쳐
- Directory
```
📂 frontend > src
├─ 📂 Login  ▶️ 로그인, 회원가입 컴포넌트
├─ 📂 Main  ▶️ 메인 화면 컴포넌트
├─ 📂 MyDropdown  ▶️ 프로필 드롭다운 메뉴 컴포넌트
├─ 📂 Navbar  ▶️ 내비게이션 바 컴포넌트
├─ 📂 Profile  ▶️ 프로필 페이지 관련 컴포넌트
├─ 📂 ProjectDetail ▶️ 프로젝트 상세 페이지 컴포넌트
├─ 📂 ProjectUpload  ▶️ 프로젝트 게시 페이지 컴포넌트
├─ 📂 TumblbugLogo  ▶️ 로고 컴포넌트
└─ 📂 utils ▶️ 유틸 함수, 기능 모음
```
```
📂 
📦backend > tumblbug-clone > src > main > java > com > example > tumblbugclone
├─ 📂controller 
├─ 📂dto 
├─ 📂Exception ▶️ 커스텀 `Exception`
├─ 📂filter ▶️ CORS 해결을 위한 필터
├─ 📂managedconst ▶️ 상수 관리 클래스
├─ 📂model 
├─ 📂repository
├─ 📂service  
├─ 📜AppConfig.java
├─ 📜ServletInitializer.java
└─ 📜TumblbugCloneApplication.java
```
- ERD
![image](https://github.com/Start-as-Web-Developers/tumblbug-clone-coding/assets/45064913/82cf8c2d-4b64-4c70-bef9-239481f39fb8)
<br>

## API 명세서
[API 명세서 Notion](https://buttoned-grip-573.notion.site/API-215bf85d262747099df0a19a1a908676)
<br>

## 프로젝트 관리
### Jira
### Github
- [Wiki](https://github.com/Start-as-Web-Developers/tumblbug-clone-coding/wiki)
- [Issues](https://github.com/Start-as-Web-Developers/tumblbug-clone-coding/issues)
- [Pull Requests](https://github.com/Start-as-Web-Developers/tumblbug-clone-coding/pulls)

<br>

## 팀 소개 및 역할
|```FE``` 문경덕|```BE``` 신지송|```BE``` 장혁수|```FE``` 정태훈|
|:-:|:-:|:-:|:-:|
|<img src="https://user-images.githubusercontent.com/74173976/216749372-fe3715b9-9249-4e89-b43b-3bf8198c9b0b.png" width=130>|<img src="https://user-images.githubusercontent.com/74173976/231698050-4e9259b4-0b61-47c6-8c2c-a92689f90d3a.png"/>|<img src="https://user-images.githubusercontent.com/76612738/227775954-b5469ce3-c92d-4b12-a186-5a20547dabbe.png"/>|<img src="https://user-images.githubusercontent.com/74173976/231698801-0398e279-9aac-4d43-9204-615efe9d80bf.png" width=130>|
|[@Moon-GD](https://github.com/Moon-GD)|[@gmuz1c](https://github.com/shin-jisong)|[@zangsu](https://github.com/zangsu)|[@hoonystory98](https://github.com/hoonystory98)|
