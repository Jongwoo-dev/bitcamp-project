[v2.0]
구현할 기능 및 산출물:
=> 상속의 일반화(Generalization) 기법 적용
   - AbstractDao.java (추가)
   - ContactDao와 TeacherDao의 공통 멤버를 추출하여 상위 클래스 정의 
=> ContactXxxController와 StudentXxxController 변경
   - ContactDao와 TeacherDao 변경 사항에 맞춰 호출 코드 변경


학습목표:
=> 상속의 generalization을 이해하고 적용할 수 있다.
=> 추상 클래스를 적용할 수 있다. 
=> try...catch...의 동작원리를 이해한다.
=> 예외를 처리하거나 상위 호출자에게 예외를 던질 수 있다.





[v1.8]
구현할 기능 및 산출물:
=> command 패턴 적용
=> Command.java 인터페이스 정의
   - 클라이언트 요청이 들어왔을 때 EduAppServer가 호출하는 메서드 규칙
   - 클라이언트 요청을 처리하는 클래스는 반드시 이 규칙을 따라야 한다.
=> ContactController.java에 command 패턴 적용
   - ContactListController.java : doList()를 객체화
   - ContactViewController.java : doView()를 객체화.
   - ContactAddController.java : doAdd()를 객체화.
   - ContactUpdateController.java : doUpdate()를 객체화
   - ContactDeleteController.java : doDelete()를 객체화.
=> TeacherController.java에 command 패턴 적용
   - TeacherListController.java : doList()를 객체화
   - TeacherViewController.java : doView()를 객체화.
   - TeacherAddController.java : doAdd()를 객체화.
   - TeacherUpdateController.java : doUpdate()를 객체화
   - TeacherDeleteController.java : doDelete()를 객체화. 

학습목표:
=> 커맨드 패턴 이해하며 또한 적용할 수 있다. 
=> 인터페이스의 용도를 이해한다.




[v1.7]
구현할 기능 및 산출물:
=> step1
   - EduAppServer.java.01
     - static 멤버 대신 instantce 멤버로 전환
=> step2
   - EduAppServer.java.02
     - 클라이언트의 요청을 처리하는 것을 스레드로 분리
   - RequestThread.java (생성)
=> step3
   - StudentDao.java 생성
     - StudentController.java 에서 데이터 처리 부분을 분리
     - Singleton 패턴 적용
     - insert(), update(), delete() 메서드에 동기화 적용
   - ContactDao.java 생성
     - ContactController.java 에서 데이터 처리 부분을 분리
     - Singleton 패턴 적용
     - insert(), update(), delete() 메서드에 동기화 적용
   - Controller들이 DAO를 공유하도록 변경

학습목표:
=> 자바의 다양한 문법을 활용하여 유지보수가 좋은 구조로 코드를 변경할 수 있다.
=> DAO(Data Access Object) 객체의 역할을 이해한다.
=> Singleton 패턴을 이해하고 사용할 수 있다. 




[v1.6]
구현할 기능:
=> 클라이언트/서버 구조로 아키텍처를 변경

산출물:
=> bitcamp/java89/ems/server 패키지 생성
   - EduApp.java를 이 패키지로 옮긴다.
   bitcamp/java89/ems/server/vo 패키지 생성
   - Contact.java, Classroom.java, Student.java, TextBook.java,
     Teacher.java를 이 패키지로 옮긴다
   bitcamp/java89/ems/server/controller 패키지 생성
   - ContactController.java, ClassroomController.java, StudentController.java,
     TextBookController.java, TeacherController를 이 패키지로 옮긴다
=> bitcamp/java89/ems/client 패키지 생성
   - EduAppClient.java (생성)

학습목표:
=> 소캣 프로그래밍 연습
=> 역할 별로 클래스를 나누어 패키지로 관리하는 방법 훈련





[v1.5]
구현할 기능:
=> 연락처 관리 기능

산출물:
=> Teacher.java
=> EduApp.java (변경)
=> TeacherController.java
=> Contact.java (추가)
=> ContactController.java (추가)

학습목표:
=> 직렬화 프로그래밍 및 유지보수 연습





[v1.4]
구현할 기능:
=> 직렬화 방식을 사용하여 데이터를 저장하고 읽는다.

산출물:
=> Teacher.java (변경)
=> EduApp.java
=> xxxController.java (변경)

학습목표:
=> 직렬화방식을 사용하여 인스턴스의 값을 저장하고 읽을 수 있다. 




[v1.3]
구현할 기능:
=> 파일 저장 기능 추가
=> 실행 예: (go 1 하기 이전)
명령> quit
학생 정보가 변경 되었습니다. 그래도 종료하시겠습니까?(y/n) y
학생 정보가 변경된 것을 취소하고 종료합니다.
Good bye!
명령> quit
학생 정보가 변경 되었습니다. 그래도 종료하시겠습니까?(y/n) n
명령> save
저장하였습니다.
명령> quit
Good bye!

=> 프로그램이 실행할때 저장한 파일 자동으로 불러옴
=> 저장파일 이름은 자유

산출물:
=> Teacher.java
=> EduApp.java (변경)
=> xxxController.java (변경)

학습목표:
=> File I/O 클래스를 사용하여 데이터 저장과 로딩을 할 수있다.




[v1.3]
구현할 기능:
=> 파일 저장 기능 추가
=> 실행 예: (go 1 하기 이전)
명령> quit
학생 정보가 변경 되었습니다. 그래도 종료하시겠습니까?(y/n) y
학생 정보가 변경된 것을 취소하고 종료합니다.
Good bye!
명령> quit
학생 정보가 변경 되었습니다. 그래도 종료하시겠습니까?(y/n) n
명령> save
저장하였습니다.
명령> quit
Good bye!

=> 프로그램이 실행할때 저장한 파일 자동으로 불러옴
=> 저장파일 이름은 자유

산출물:
=> Teacher.java
=> EduApp.java (변경)
=> xxxController.java (변경)

학습목표:
=> File I/O 클래스를 사용하여 데이터 저장과 로딩을 할 수있다.





[v1.2]
구현할 기능:
=> 우리가 만든 LinkedList 대신 자바에서 제공하는 ArrayList 사용하여 데이터 목록을 다룬다.

산출물:
=> Teacher.java
=> EduApp.java
=> xxxController.java (변경)
=> LinkedList.java (제거)

학습목표:
=> 자바에서 제공하는 ArrayList를 사용할 수 있다.




[v1.1]
구현할 기능:
=> Linkedlist에 예외처리 코드 추가
=> xxxController에서 예외 다루기

산출물:
=> Teacher.java
=> EduApp.java
=> xxxController.java (변경)
=> LinkedList.java (변경)

학습목표:
=> 메서드에 예외 던지는 기능을 추가하고,
   예외를 던지는 메서드를 호출하는 방법을 안다.
=> 오류가 발생하더라도 시스템을 멈추지 않고 실행하는 방법을 이해안다.




[v1.0]
구현할 기능:
=> LinkedList에 제네릭을 적용한다.ㄱ
=> LinkedList를 사용하는 모든 코드에서 타입 캐스팅을 제거한다.

산출물:
=> Teacher.java
=> EduApp.java
=> xxxController.java (변경)
=> LinkedList.java (변경)

학습목표:
=> 제네릭을 클래스에 적용하고 제네릭이 적용된 클래스를 사용할 수 있다.




[v0.9]
구현할 기능:
=> LinkedList 클래스를 만든다
=> LinkedList를 StudentController에 적용한다.


산출물:
=> Teacher.java
=> EduApp.java
=> xxxController.java (변경)
=> Box.java (삭제)
=> LinkedList.java (추가)

학습목표:
=> LinkedList를 만들 수 있다.
=> LinkedList를 사용하여 데이터를 저장, 조회, 변경, 삭제할 수 있다.




[v0.8]
구현할 기능:
=> Linked List 기법을 이용하여 학생, 강사 등의 데이터를 관리한다.


산출물:
=> Teacher.java
=> EduApp.java
=> xxxController.java (변경)
=> Box.java (추가)

학습목표:
=> Linked List의 사용법을 익힌다.
=> Linked List를 구현하고 프로젝트에 적용할 수 있다.




[v0.7]
구현할 기능:
=> EduApp에 있던 학생관리 명령어 처리를
   학생관리 전문가인 StudentController로 이전한다.
=> EduApp은 대신 메뉴 선택 기능을 추가한다.

산출물:
=> Teacher.java
=> EduApp.java (변경)
=> TeacherController.java (변경)

학습목표:
=> 리팩토링(refactoring)을 통해 명령어 코드를 관련된 클래스에 분류시키는 것.
=> 객체지향 설계 방법론 중에서 "응집력 높히기(high cohesion)"을 이해한다.
   High Cohesion? 한 클래스는 한 가지 역할에 집중해야 한다.
   => 한 클래스가 여러 가지 일을 하면 클래스 코드가 커지고, 유지보수에 안좋다.
=> 메서드나 변수에 대해 접근을 제어하는 방법과 이유를 이해한다.
   => StudentController의 doXxx() 메서드들을 private 으로 접근을 제한한다.
   => 이 클래스의 내부 변수들도 private으로 접근을 제한한다.




[v0.6]
구현할 기능:
=> 삭제 기능 추가

   명령> delete
   삭제할 학생의 아이디는? hong
   hong 학생 정보를 삭제하였습니다.
   명령>

   명령> delete
   삭제할 학생의 아이디는? hong2
   hong2 이라는 학생이 없습니다.
   명령>

=> 변경 기능 추가
   명령> update
   변경할 학생의 아이디는? hong
   암호? 1111
   이름? 임꺽정
   전화? 111-1212
   이메일? lemm@test.com
   재직중(y/n)? y
   태어난해? 1980
   최종학교? 1
   저장하시겠습니까(y/n)? y
   저장하였습니다.
   명령>

   ...
   저장하시겠습니까(y/n)? b
   변경을 취소하였습니다.
   명령>

   ...
   명령> update
   변경할 학생의 아이디는? hong
   hong 이라는 학생이 없습니다.

산출물:
=> Teacher.java
=> EduApp.java (변경)
=> TeacherController.java (변경)

학습목표:
=> 삭제와 변경을 추가하여 CRUD(Create/Read/Update/Delete) 기본 기능을 완성한다.
=> 기존의 코드에 새 기능을 추가하는 경험을 해본다.
   이것이 유지보수 개발업무이다.




[v0.5]
구현할 기능:
=> StudentController 클래스를 확장해서 사용할 수 있도록 ,
   학생 목록을 스태틱 변수로 저장하지 말고, 인스턴스 변수로 저장한다.
=> 그에 따라 명령어를 처리하는 메서드도 인스턴스 메서드로 전환한다.


산출물:
=> Teacher.java
=> EduApp.java (변경)
=> TeacherController.java (변경)

학습목표:
=> 인스턴스 변수와 인스턴스 메서드의 사용법을 익힌다.
=> 생성자의 용도를 다시 한 번 확인한다.



[v0.4]
구현할 기능:
=> 소스코드 리팩토링(refactoring)
  => 유지보수하기 좋게 소스 코드를 좀 더 객체지향적으로 정리한다.
=> EduApp에 있는 Student 데이터의 입출력을 다루는데 관련된 메서드를
   따로 다른 클레스로 분류한다.

산출물:
=> Teacher.java
=> EduApp.java
=> TeacherController.java (추가)

학습목표:
=> 클래스 문법의 용도를 다시 한 번 익힌다.
  => 클래스는 특정 작업과 관련된 메서드를 관리하기 좋게 분류할 때 사용한다.



[v0.3]
구현할 기능:
=> 사용자에게 명령어를 입력 받는 프롬프트 기능을 추가한다.
예) 프로그램을 시작시키면 다음과 같이 동작한다.
명령> add
아이디(예:hong)? 5
암호(예:1234)? 5
이름(예:홍길동)? 5
이메일(예:hong@test.com)? 5
전화(예:010-1111-2222)? 5
나이(예:39)? 5
담당과목(예:자바)? 5
경력(예:10)? 5
연봉(예:8500)? 5
주소(예:서울시 서초구 서초동)? 5
계속 입력하시겠습니까(y/n)? n
명령> list
1, 1, 1, 1, 1, 1, 1, 1, 1, 1
명령> view
학생(나는 강사?) 아이디? hong
아이디: 5
암호: 5
이름: 5
이메일: 5
전화: 5
나이: 5
담당과목: 5
경력: 5
연봉: 5
주소: 5


산출물:
학습목표:
=> 반복문 중첩해서 사용하는 방법을 익힌다.
=> 입력 코드를 별도의 메서드로 분리하여 유지보수 하기 좋은 코드를 만드는 것을 익힌다.
