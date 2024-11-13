# GDG INU 1기 - Spring 심화 스터디

## 팀원 👨‍👨‍👧‍👧👩‍👦‍👦

|Core|Member|Member|Member|Member|Member|Member|Member|
|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|
|<img src="https://avatars.githubusercontent.com/u/121238128?v=4" width=100px alt="나무"/>|<img src="https://avatars.githubusercontent.com/u/66998786?v=4" width=100px alt="수빈"/>|<img src="https://avatars.githubusercontent.com/u/75568516?v=4" width=100px alt="규리"/>|<img src="https://avatars.githubusercontent.com/u/170295944?v=4" width=100px alt="윤서"/>|<img src="https://avatars.githubusercontent.com/u/104718158?v=4" width=100px alt="제용"/>|<img src="https://avatars.githubusercontent.com/u/164310681?v=4" width=100px alt="채은"/>|<img src="https://avatars.githubusercontent.com/u/98392401?v=4" width=100px alt="광현"/>||
|[장철희](https://github.com/jang-namu)|[김수빈](https://github.com/subin3124)|[남규리](https://github.com/gyuri00)|[이윤서](https://github.com/yunseo0000)|[이제용](https://github.com/JayongLee)|[이채은](https://github.com/chaeeun1103)|[조광현](https://github.com/Kidprogramer1039)|천평근|

## 📝 규칙

1. 주차별 학습내용  
    ```
    N 주차 학습내용을 'concepts/week{N}/{영문이름}' 폴더에 작성합니다.  
    처음부터 마크다운으로 작성하셔도 좋고 노션으로 작성한 내용을 export해서 올리셔도 좋습니다.  
    - 노션 변환 시, 업로드 후 이미지 잘 보이는지 확인해주세요.
    ```

2. 실습
    ```
    스프링 프로젝트를 만들어 'practice/{영문이름}'에 작업 내용을 업로드합니다.
    README에 프로젝트에 대한 간략한 설명과 ERD를 업로드해주세요.
    기능 1개당 1커밋 규칙을 준수합니다.
    ```

- `커밋 컨벤션`
    - feat: 새로운 기능 추가
    - fix: 버그 수정
    - docs: 문서 수정
    - style: 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
    - refactor: 코드 리팩토링
    - test: 테스트 코드, 리팩토링 테스트 코드 추가
    - chore: 빌드 업무 수정, 패키지 매니저 수정
<br><br>

- `issue 규칙`
    - issue 제목
        - 예시: **도서 조회 기능 개발**
    - issue 템플릿
        ```markdown
        ## 어떤 기능인가요?

        > 추가하려는 기능에 대해 간결하게 설명해주세요

        ## 작업 상세 내용

        - [ ] TODO
        - [ ] TODO
        - [ ] TODO

        ## 참고할만한 자료(선택)
        ```
    <br><br>      

- `branch 규칙`
    - 각자의 영어 이름을 딴 branch 명을 생성하여 사용.
    - 예시: 
    ```
  git checkout -b <브랜치명>      
  git checkout -b chulhee
    ```

- `commit message 규칙`
    - {TYPE}: 커밋 메시지
    - 예시
        - docs: 서버 개념 정리
        - feat: 도서 관리 시스템 엔티티 구현
        - fix: 도서 조회 서비스 에러 수정
    <br><br>

- `PR 규칙`
    - PR 제목
        - [{N}주차] {주제} ~
        - 예시: [1주차] 서버개념 + 엔티티~레포지터리 작성
    - PR 템플릿

        ```markdown
        ## #️⃣연관된 이슈

        > ex) close #이슈번호

        ## 📝작업 내용

        > 이번 PR에서 작업한 내용을 간략히 설명해주세요(이미지 첨부 가능)

        ### 스크린샷 (선택)

        ## 💬리뷰 요구사항(선택)

        > 리뷰어가 특별히 봐주었으면 하는 부분이 있다면 작성해주세요
        >
        > ex) 메서드 XXX의 이름을 더 잘 짓고 싶은데 혹시 좋은 명칭이 있을까요?
        ```

- `merge 규칙`
    - rebase and merge
    ```
    # main 브랜치에서
    git fetch origin main
    git pull
    git checkout {내 브랜치}
    git rebase main
    # -> conflict 해결
    git push origin {내 브랜치}
    # -> PR 작성
    ```

    <br><br>
