# 도서 관리 프로그램 세부 내용 정리

## API 명세서
---
```markdown
|기능|HTTP Method|API path|
|------|---|---|
|회원 가입|POST|/user/signin|
|회원 탈퇴|DELETE|/user/signout|
|로그인|POST|/user/login|
|로그아웃|GET|/user/logout|
|책 조회|GET|/book/{bookid}|
|책 대여|PATCH|/book/{bookid}|
|책 반납|PATCH|/book/{bookid}|
```