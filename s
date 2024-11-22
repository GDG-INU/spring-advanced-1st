[1mdiff --git a/.idea/.gitignore b/.idea/.gitignore[m
[1mnew file mode 100644[m
[1mindex 0000000..13566b8[m
[1m--- /dev/null[m
[1m+++ b/.idea/.gitignore[m
[36m@@ -0,0 +1,8 @@[m
[32m+[m[32m# Default ignored files[m
[32m+[m[32m/shelf/[m
[32m+[m[32m/workspace.xml[m
[32m+[m[32m# Editor-based HTTP Client requests[m
[32m+[m[32m/httpRequests/[m
[32m+[m[32m# Datasource local storage ignored files[m
[32m+[m[32m/dataSources/[m
[32m+[m[32m/dataSources.local.xml[m
[1mdiff --git a/.idea/compiler.xml b/.idea/compiler.xml[m
[1mdeleted file mode 100644[m
[1mindex 727f894..0000000[m
[1m--- a/.idea/compiler.xml[m
[1m+++ /dev/null[m
[36m@@ -1,18 +0,0 @@[m
[31m-<?xml version="1.0" encoding="UTF-8"?>[m
[31m-<project version="4">[m
[31m-  <component name="CompilerConfiguration">[m
[31m-    <annotationProcessing>[m
[31m-      <profile name="Gradle Imported" enabled="true">[m
[31m-        <outputRelativeToContentRoot value="true" />[m
[31m-        <processorPath useClasspath="false">[m
[31m-          <entry name="$PROJECT_DIR$/../.gradle/caches/modules-2/files-2.1/org.projectlombok/lombok/1.18.24/13a394eed5c4f9efb2a6d956e2086f1d81e857d9/lombok-1.18.24.jar" />[m
[31m-        </processorPath>[m
[31m-        <module name="demo.main" />[m
[31m-      </profile>[m
[31m-    </annotationProcessing>[m
[31m-    <bytecodeTargetLevel target="17" />[m
[31m-  </component>[m
[31m-  <component name="JavacSettings">[m
[31m-    <option name="ADDITIONAL_OPTIONS_STRING" value="-parameters" />[m
[31m-  </component>[m
[31m-</project>[m
\ No newline at end of file[m
[1mdiff --git a/.idea/gradle.xml b/.idea/gradle.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..158a4bb[m
[1m--- /dev/null[m
[1m+++ b/.idea/gradle.xml[m
[36m@@ -0,0 +1,16 @@[m
[32m+[m[32m<?xml version="1.0" encoding="UTF-8"?>[m
[32m+[m[32m<project version="4">[m
[32m+[m[32m  <component name="GradleMigrationSettings" migrationVersion="1" />[m
[32m+[m[32m  <component name="GradleSettings">[m
[32m+[m[32m    <option name="linkedExternalProjectsSettings">[m
[32m+[m[32m      <GradleProjectSettings>[m
[32m+[m[32m        <option name="externalProjectPath" value="$PROJECT_DIR$/practice/chaeeun/demo" />[m
[32m+[m[32m        <option name="modules">[m
[32m+[m[32m          <set>[m
[32m+[m[32m            <option value="$PROJECT_DIR$/practice/chaeeun/demo" />[m
[32m+[m[32m          </set>[m
[32m+[m[32m        </option>[m
[32m+[m[32m      </GradleProjectSettings>[m
[32m+[m[32m    </option>[m
[32m+[m[32m  </component>[m
[32m+[m[32m</project>[m
\ No newline at end of file[m
[1mdiff --git a/.idea/jarRepositories.xml b/.idea/jarRepositories.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..fdc392f[m
[1m--- /dev/null[m
[1m+++ b/.idea/jarRepositories.xml[m
[36m@@ -0,0 +1,20 @@[m
[32m+[m[32m<?xml version="1.0" encoding="UTF-8"?>[m
[32m+[m[32m<project version="4">[m
[32m+[m[32m  <component name="RemoteRepositoriesConfiguration">[m
[32m+[m[32m    <remote-repository>[m
[32m+[m[32m      <option name="id" value="central" />[m
[32m+[m[32m      <option name="name" value="Maven Central repository" />[m
[32m+[m[32m      <option name="url" value="https://repo1.maven.org/maven2" />[m
[32m+[m[32m    </remote-repository>[m
[32m+[m[32m    <remote-repository>[m
[32m+[m[32m      <option name="id" value="jboss.community" />[m
[32m+[m[32m      <option name="name" value="JBoss Community repository" />[m
[32m+[m[32m      <option name="url" value="https://repository.jboss.org/nexus/content/repositories/public/" />[m
[32m+[m[32m    </remote-repository>[m
[32m+[m[32m    <remote-repository>[m
[32m+[m[32m      <option name="id" value="MavenRepo" />[m
[32m+[m[32m      <option name="name" value="MavenRepo" />[m
[32m+[m[32m      <option name="url" value="https://repo.maven.apache.org/maven2/" />[m
[32m+[m[32m    </remote-repository>[m
[32m+[m[32m  </component>[m
[32m+[m[32m</project>[m
\ No newline at end of file[m
[1mdiff --git a/.idea/misc.xml b/.idea/misc.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..da6d6db[m
[1m--- /dev/null[m
[1m+++ b/.idea/misc.xml[m
[36m@@ -0,0 +1,10 @@[m
[32m+[m[32m<?xml version="1.0" encoding="UTF-8"?>[m
[32m+[m[32m<project version="4">[m
[32m+[m[32m  <component name="ExternalStorageConfigurationManager" enabled="true" />[m
[32m+[m[32m  <component name="FrameworkDetectionExcludesConfiguration">[m
[32m+[m[32m    <file type="web" url="file://$PROJECT_DIR$/practice/chaeeun/demo" />[m
[32m+[m[32m  </component>[m
[32m+[m[32m  <component name="ProjectRootManager" version="2" languageLevel="JDK_22" project-jdk-name="17" project-jdk-type="JavaSDK">[m
[32m+[m[32m    <output url="file://$PROJECT_DIR$/out" />[m
[32m+[m[32m  </component>[m
[32m+[m[32m</project>[m
\ No newline at end of file[m
[1mdiff --git a/.idea/modules.xml b/.idea/modules.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..28e32ec[m
[1m--- /dev/null[m
[1m+++ b/.idea/modules.xml[m
[36m@@ -0,0 +1,8 @@[m
[32m+[m[32m<?xml version="1.0" encoding="UTF-8"?>[m
[32m+[m[32m<project version="4">[m
[32m+[m[32m  <component name="ProjectModuleManager">[m
[32m+[m[32m    <modules>[m
[32m+[m[32m      <module fileurl="file://$PROJECT_DIR$/.idea/spring-advanced-1st.iml" filepath="$PROJECT_DIR$/.idea/spring-advanced-1st.iml" />[m
[32m+[m[32m    </modules>[m
[32m+[m[32m  </component>[m
[32m+[m[32m</project>[m
\ No newline at end of file[m
[1mdiff --git a/.idea/spring-advanced-1st.iml b/.idea/spring-advanced-1st.iml[m
[1mnew file mode 100644[m
[1mindex 0000000..d6ebd48[m
[1m--- /dev/null[m
[1m+++ b/.idea/spring-advanced-1st.iml[m
[36m@@ -0,0 +1,9 @@[m
[32m+[m[32m<?xml version="1.0" encoding="UTF-8"?>[m
[32m+[m[32m<module type="JAVA_MODULE" version="4">[m
[32m+[m[32m  <component name="NewModuleRootManager" inherit-compiler-output="true">[m
[32m+[m[32m    <exclude-output />[m
[32m+[m[32m    <content url="file://$MODULE_DIR$" />[m
[32m+[m[32m    <orderEntry type="inheritedJdk" />[m
[32m+[m[32m    <orderEntry type="sourceFolder" forTests="false" />[m
[32m+[m[32m  </component>[m
[32m+[m[32m</module>[m
\ No newline at end of file[m
[1mdiff --git a/.idea/vcs.xml b/.idea/vcs.xml[m
[1mnew file mode 100644[m
[1mindex 0000000..94a25f7[m
[1m--- /dev/null[m
[1m+++ b/.idea/vcs.xml[m
[36m@@ -0,0 +1,6 @@[m
[32m+[m[32m<?xml version="1.0" encoding="UTF-8"?>[m
[32m+[m[32m<project version="4">[m
[32m+[m[32m  <component name="VcsDirectoryMappings">[m
[32m+[m[32m    <mapping directory="$PROJECT_DIR$" vcs="Git" />[m
[32m+[m[32m  </component>[m
[32m+[m[32m</project>[m
\ No newline at end of file[m
[1mdiff --git a/practice/chaeeun/demo/.gitignore b/practice/chaeeun/demo/.gitignore[m
[1mindex c489d28..91de07c 100644[m
[1m--- a/practice/chaeeun/demo/.gitignore[m
[1m+++ b/practice/chaeeun/demo/.gitignore[m
[36m@@ -37,3 +37,4 @@[m [mout/[m
 .vscode/[m
 [m
 [m
[41m+[m
[1mdiff --git a/practice/chaeeun/demo/build.gradle b/practice/chaeeun/demo/build.gradle[m
[1mindex c3706c3..dc96847 100644[m
[1m--- a/practice/chaeeun/demo/build.gradle[m
[1m+++ b/practice/chaeeun/demo/build.gradle[m
[36m@@ -21,14 +21,7 @@[m [mdependencies {[m
 	implementation 'org.springframework.boot:spring-boot-starter'[m
 	testImplementation 'org.springframework.boot:spring-boot-starter-test'[m
 	implementation 'org.springframework.boot:spring-boot-starter-web'[m
[31m-	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'[m
 	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'[m
[31m-	// intellj랑 database 연결(intellj로 데이터베이스 보려면)[m
[31m-	runtimeOnly 'mysql:mysql-connector-java:8.0.33'[m
[31m-	// mysql 설정[m
[31m-	runtimeOnly 'com.mysql:mysql-connector-j'[m
[31m-	implementation 'org.projectlombok:lombok:1.18.24'[m
[31m-	annotationProcessor 'org.projectlombok:lombok:1.18.24'[m
 }[m
 [m
 tasks.named('test') {[m
[1mdiff --git a/practice/chaeeun/demo/src/main/java/com/example/demo/controller/AuthorController.java b/practice/chaeeun/demo/src/main/java/com/example/demo/controller/AuthorController.java[m
[1mdeleted file mode 100644[m
[1mindex 9288b5e..0000000[m
[1m--- a/practice/chaeeun/demo/src/main/java/com/example/demo/controller/AuthorController.java[m
[1m+++ /dev/null[m
[36m@@ -1,44 +0,0 @@[m
[31m-package com.example.demo.controller;[m
[31m-[m
[31m-import com.example.demo.dto.AuthorDTO;[m
[31m-import com.example.demo.service.AuthorService;[m
[31m-import org.springframework.beans.factory.annotation.Autowired;[m
[31m-import org.springframework.http.ResponseEntity;[m
[31m-import org.springframework.web.bind.annotation.*;[m
[31m-import java.util.List;[m
[31m-[m
[31m-[m
[31m-@RestController[m
[31m-@RequestMapping("/authors")[m
[31m-public class AuthorController {[m
[31m-[m
[31m-    private final AuthorService authorService;[m
[31m-[m
[31m-    @Autowired[m
[31m-    public AuthorController(AuthorService authorService) {[m
[31m-        this.authorService = authorService;[m
[31m-    }[m
[31m-[m
[31m-    @PostMapping[m
[31m-    public AuthorDTO createAuthor(@RequestBody AuthorDTO authorDTO) {[m
[31m-        return authorService.saveAuthor(authorDTO);[m
[31m-    }[m
[31m-[m
[31m-    @GetMapping("/{id}")[m
[31m-    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable("id") Long id) {[m
[31m-        return authorService.findById(id)[m
[31m-                .map(ResponseEntity::ok)[m
[31m-                .orElse(ResponseEntity.notFound().build());[m
[31m-    }[m
[31m-[m
[31m-    @GetMapping[m
[31m-    public List<AuthorDTO> getAllAuthors() {[m
[31m-        return authorService.findAll();[m
[31m-    }[m
[31m-[m
[31m-    @DeleteMapping("/{id}")[m
[31m-    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") Long id) {[m
[31m-        authorService.deleteById(id);[m
[31m-        return ResponseEntity.noContent().build(); // 204 no content[m
[31m-    }[m
[31m-}[m
\ No newline at end of file[m
[1mdiff --git a/practice/chaeeun/demo/src/main/java/com/example/demo/controller/BookController.java b/practice/chaeeun/demo/src/main/java/com/example/demo/controller/BookController.java[m
[1mindex 83b959d..f5125fe 100644[m
[1m--- a/practice/chaeeun/demo/src/main/java/com/example/demo/controller/BookController.java[m
[1m+++ b/practice/chaeeun/demo/src/main/java/com/example/demo/controller/BookController.java[m
[36m@@ -1,11 +1,9 @@[m
 package com.example.demo.controller;[m
 [m
[31m-import com.example.demo.dto.BookDTO;[m
[32m+[m[32mimport com.example.demo.domain.Book;[m
 import com.example.demo.service.BookService;[m
[31m-import org.springframework.http.ResponseEntity;[m
 import org.springframework.web.bind.annotation.*;[m
[31m-import java.util.List;[m
[31m-[m
[32m+[m[32mimport java.util.Optional;[m
 [m
 @RestController[m
 @RequestMapping("/books")[m
[36m@@ -18,26 +16,24 @@[m [mpublic class BookController {[m
     }[m
 [m
     @PostMapping[m
[31m-    public BookDTO createBook(@RequestBody BookDTO bookDTO){[m
[31m-        return bookService.saveBook(bookDTO);[m
[32m+[m[32m    public Book createBook(@RequestBody Book book){[m
[32m+[m[32m        return bookService.saveBook(book);[m
     }[m
 [m
     @GetMapping("/{id}")[m
[31m-    public ResponseEntity<BookDTO> getBookId(@PathVariable("id") Long id){[m
[31m-        return bookService.findById(id)[m
[31m-                .map(ResponseEntity::ok)[m
[31m-                .orElse(ResponseEntity.notFound().build());[m
[32m+[m[32m    public Optional<Book> getBookId(@PathVariable Long id){[m
[32m+[m[32m        return bookService.findById(id);[m
     }[m
 [m
[31m-    @GetMapping("/search")[m
[31m-    public ResponseEntity<List<BookDTO>> getBookTitle(@RequestParam String title){[m
[31m-        List<BookDTO> books = bookService.findByTitle(title);[m
[31m-        return ResponseEntity.ok(books);[m
[32m+[m[32m    @GetMapping("/title")[m
[32m+[m[32m    public Optional<Book> getBookTitle(@RequestParam String title){[m
[32m+[m[32m        return bookService.findByTitle(title);[m
     }[m
 [m
     @DeleteMapping("/{id}")[m
[31m-    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id){[m
[32m+[m[32m    public String deleteBook(@PathVariable Long id){[m
         bookService.deleteById(id);[m
[31m-        return ResponseEntity.noContent().build(); // 204 not content[m
[32m+[m[32m        return id + "번 도서가 삭제되었습니다.";[m
     }[m
[32m+[m
 }[m
\ No newline at end of file[m
[1mdiff --git a/practice/chaeeun/demo/src/main/java/com/example/demo/controller/MemberController.java b/practice/chaeeun/demo/src/main/java/com/example/demo/controller/MemberController.java[m
[1mdeleted file mode 100644[m
[1mindex 67a5a24..0000000[m
[1m--- a/practice/chaeeun/demo/src/main/java/com/example/demo/controller/MemberController.java[m
[1m+++ /dev/null[m
[36m@@ -1,41 +0,0 @@[m
[31m-package com.example.demo.controller;[m
[31m-[m
[31m-import com.example.demo.dto.AuthorDTO;[m
[31m-import com.example.demo.dto.MemberDTO;[m
[31m-import com.example.demo.service.MemberService;[m
[31m-import org.springframework.http.ResponseEntity;[m
[31m-import org.springframework.web.bind.annotation.*;[m
[31m-[m
[31m-@RestController[m
[31m-@RequestMapping("/members")[m
[31m-public class MemberController {[m
[31m-[m
[31m-    private final MemberService memberService;[m
[31m-[m
[31m-    public MemberController(MemberService memberService) {[m
[31m-        this.memberService = memberService;[m
[31m-    }[m
[31m-[m
[31m-    // 회원 생성[m
[31m-    @PostMapping[m
[31m-    public MemberDTO createMember(@RequestBody MemberDTO memberDTO) {[m
[31m-        return memberService.registerMember(memberDTO);[m
[31m-    }[m
[31m-[m
[31m-    // id로 회원 조회[m
[31m-    @GetMapping("/{id}")[m
[31m-    public ResponseEntity<MemberDTO> getMember(@PathVariable("id") Long id) {[m
[31m-        return memberService.findById(id)[m
[31m-                .map(ResponseEntity::ok)[m
[31m-                .orElse(ResponseEntity.notFound().build());[m
[31m-    }[m
[31m-[m
[31m-    // id로 회원 삭제[m
[31m-    @DeleteMapping("/{id}")[m
[31m-    public ResponseEntity<Void> deleteMember(@PathVariable("id") Long id) {[m
[31m-        memberService.deleteMember(id);[m
[31m-        return ResponseEntity.noContent().build(); // 204 no content[m
[31m-    }[m
[31m-[m
[31m-    // 회원이 대여 중인 책 목록 조회[m
[31m-}[m
[1mdiff --git a/practice/chaeeun/demo/src/main/java/com/example/demo/controller/PublisherController.java b/practice/chaeeun/demo/src/main/java/com/example/demo/controller/PublisherController.java[m
[1mdeleted file mode 100644[m
[1mindex e2566fa..0000000[m
[1m--- a/practice/chaeeun/demo/src/main/java/com/example/demo/controller/PublisherController.java[m
[1m+++ /dev/null[m
[36m@@ -1,48 +0,0 @@[m
[31m-package com.example.demo.controller;[m
[31m-[m
[31m-import com.example.demo.domain.Publisher;[m
[31m-import com.example.demo.dto.PublisherDTO;[m
[31m-import com.example.demo.service.PublisherService;[m
[31m-import org.springframework.beans.factory.annotation.Autowired;[m
[31m-import org.springframework.http.ResponseEntity;[m
[31m-import org.springframework.web.bind.annotation.*;[m
[31m-[m
[31m-import java.util.List;[m
[31m-import java.util.Optional;[m
[31m-[m
[31m-@RestController[m
[31m-@RequestMapping("/publishers")[m
[31m-public class PublisherController {[m
[31m-[m
[31m-    private final PublisherService publisherService;[m
[31m-[m
[31m-    @Autowired[m
[31m-    public PublisherController(PublisherService publisherService) {[m
[31m-        this.publisherService = publisherService;[m
[31m-    }[m
[31m-[m
[31m-   @PostMapping[m
[31m-    public PublisherDTO createAuthor(@RequestBody PublisherDTO publisherDTO) {[m
[31m-       return publisherService.savePublisher(publisherDTO);[m
[31m-   }[m
[31m-[m
[31m-  