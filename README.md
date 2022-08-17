# 🎖 Groupin
> 취미 모임 생성 웹 사이트, Groupin

*in 2022 Summer, SOLUX*

## Team 송편🍡
<table>
  <tr>
    <td rowspan="2" align="center">
      Frontend
    </td>
    <td> <a href="https://github.com/Jiyoongrace"> 배지윤 </a> </td>
  </tr>
  <tr>
    <td> <a href="https://github.com/seoyeonsong"> 서연송 </a> </td>
  </tr>
  <tr>
    <td rowspan="2">Backend</td>
    <td> <a href="https://github.com/JeongIn37"> 윤정인 </a> </td>
  </tr>
  <tr>
    <td> <a href="https://github.com/lucy1287"> 이은재 </a> </td>
  </tr>
</table>

<br/>

## 🛠 Tech Stack - Backend
- JAVA 11, Spring Boot 2.6.9 (Build-Tool: Maven)
- Spring Data JPA, Spring Security
- Deploy: Heroku, ClearDB(MySQL)
- CI/CD: Github Actions

<br/>

## 📄 API Documentation
[API Documentation - Notion](https://jonnie37.notion.site/API-7df2549689984788b4ab3292b84525fc)

<br/>

## 🖇 ERD
[Web - lucidchart login required](https://lucid.app/lucidchart/5e6200f4-0887-45a2-a9c7-869e7da87dcc/edit?viewport_loc=80%2C-523%2C1698%2C1903%2C0_0&invitationId=inv_406b038c-5d00-4a4e-848f-dccce514e4dc#)
<img alt="Groupin' ER diagram" src="https://user-images.githubusercontent.com/54874529/185191940-5eb9faa5-5b3b-40c5-bbc6-fc448235ce93.png">

<br/>

## ⚙️ Project Architecture
<img alt="Groupin_Project_Architecture_dark" src="https://user-images.githubusercontent.com/54874529/185190335-0bcab811-98a4-40f3-b390-958ec77a6ed7.png">

<br/>

## 🗂 Project Structure
```
├── GroupinApplication.java
|
├── User
├── board
├── group
├── mypage
├── review
|
├── handler
├── config
└── util
```

<details>
<summary> Details </summary>
<div markdown="1">
  ```
  ├── GroupinApplication.java
  ├── User
  │   ├── controller
  │   │   └── AuthController.java
  │   ├── dto
  │   │   └── SignupDto.java
  │   ├── entity
  │   │   └── User.java
  │   ├── repository
  │   │   └── UserRepository.java
  │   └── service
  │       └── AuthService.java
  ├── board
  │   ├── controller
  │   │   ├── BoardController.java
  │   │   └── CommentController.java
  │   ├── domain
  │   │   ├── Board.java
  │   │   └── Comment.java
  │   ├── dto
  │   │   ├── BoardWriteDto.java
  │   │   └── CommentDto.java
  │   ├── repository
  │   │   ├── BoardRepository.java
  │   │   └── CommentRepository.java
  │   └── service
  │       ├── BoardService.java
  │       └── CommentService.java
  ├── config
  │   ├── SecurityConfig.java
  │   ├── WebMvcConfig.java
  │   └── auth
  │       ├── PrincipalDetails.java
  │       └── PrincipalDetailsService.java
  ├── group
  │   ├── controller
  │   │   ├── GroupController.java
  │   │   └── GroupProposalController.java
  │   ├── domain
  │   │   ├── GroupProposal.java
  │   │   ├── GroupRelation.java
  │   │   └── Groupin.java
  │   ├── dto
  │   │   ├── GroupDto.java
  │   │   ├── GroupProposalDto.java
  │   │   └── GroupRelationDto.java
  │   ├── repository
  │   │   ├── GroupProposalRepository.java
  │   │   ├── GroupRelationRepository.java
  │   │   └── GroupRepository.java
  │   └── service
  │       ├── GroupProposalService.java
  │       └── GroupService.java
  ├── handler
  │   ├── ControllerExceptionHandler.java
  │   ├── GlobalExceptionHandler.java
  │   └── ex
  │       ├── CustomException.java
  │       ├── CustomValidationException.java
  │       ├── ErrorCode.java
  │       └── ErrorResponse.java
  ├── mypage
  │   ├── controller
  │   │   └── MyPageController.java
  │   └── service
  │       └── MyPageService.java
  ├── review
  │   ├── controller
  │   │   ├── RCommentController.java
  │   │   └── ReviewController.java
  │   ├── domain
  │   │   ├── RComment.java
  │   │   └── Review.java
  │   ├── dto
  │   │   ├── RCommentDto.java
  │   │   └── ReviewWriteDto.java
  │   ├── repository
  │   │   ├── RCommentRepository.java
  │   │   └── ReviewRepository.java
  │   └── service
  │       ├── RCommentService.java
  │       └── ReviewService.java
  └── util
      ├── CMRespDto.java
      └── Script.java
  ```


</div>
</details>
