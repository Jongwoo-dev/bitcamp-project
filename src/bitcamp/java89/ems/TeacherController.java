package bitcamp.java89.ems;

import java.util.Scanner;

public class TeacherController {
  // 아래 인스턴스 변수들은 외부에서 사용할 일이 없기 때문에
  // private으로 접근을 제한한다.
  private Scanner keyScan;
  private LinkedList list;

  public TeacherController(Scanner keyScan) {
    list = new LinkedList();
    this.keyScan = keyScan;
  }

  public void service() {
    loop:
    while (true) {
      System.out.print("강사관리> ");
      String command = keyScan.nextLine().toLowerCase();

      switch (command) {
      case "add": this.doAdd(); break;
      case "list": this.doList(); break;
      case "view": this.doView(); break;
      case "delete": this.doDelete(); break;
      case "update": this.doUpdate(); break;
      case "main": break loop;
      default:
        System.out.println("지원하지 않는 명령어입니다.");
      }
    }
  }

  // 아래 doXxx() 메서드들은 오직 service()에서만 호출하기 때문에
  // private으로 접근을 제한한다.
  private void doUpdate() {
    System.out.print("변경할 강사의 인덱스? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    if (index < 0 || index >= list.size()) {
      System.out.println("인덱스가 유효하지 않습니다.");
      return;
    }

    Teacher oldTeacher = (Teacher)list.get(index);

    //새 강사 정보를 입력받는다.
    Teacher teacher = new Teacher();
    System.out.print("암호(예:1234)? ");
    teacher.password = this.keyScan.nextLine();

    System.out.printf("이름(%s)? ", oldTeacher.name);
    teacher.name = this.keyScan.nextLine();

    System.out.printf("이메일(%s)? ", oldTeacher.email);
    teacher.email = this.keyScan.nextLine();

    System.out.printf("전화(%s)? ", oldTeacher.tel);
    teacher.tel = this.keyScan.nextLine();

    System.out.printf("나이(%d)? ", oldTeacher.age);
    teacher.age = Integer.parseInt(this.keyScan.nextLine());

    System.out.printf("담당과목(%s)? ", oldTeacher.subject);
    teacher.subject = this.keyScan.nextLine();

    System.out.printf("경력(%d)? ", oldTeacher.carrer);
    teacher.carrer = Integer.parseInt(this.keyScan.nextLine());

    System.out.printf("연봉(%d)? ", oldTeacher.salary);
    teacher.salary = Integer.parseInt(this.keyScan.nextLine());

    System.out.printf("주소(%s)? ", oldTeacher.address);
    teacher.address = this.keyScan.nextLine();

    System.out.printf("저장하시겠습니까(y/n)? ");
    if (this.keyScan.nextLine().toLowerCase().equals("y")) {
      teacher.userId = oldTeacher.userId;
      list.set(index, teacher);
      System.out.println("저장하였습니다.");
    } else {
      System.out.println("변경을 취소하였습니다.");
    }
    return;
  }

  private void doDelete() {
    System.out.print("삭제할 강사의 인덱스? ");
    int index = Integer.parseInt(keyScan.nextLine());

    if (index < 0 || index >= list.size()) {
      System.out.println("인덱스가 유효하지 않습니다.");
      return;
    }

    Teacher deletedTeacher = (Teacher)list.remove(index);

    System.out.printf("%s 강사 정보를 삭제하였습니다.\n", deletedTeacher.userId);
  }

  private void doView() {
    System.out.print("강사의 인덱스? ");
    int index = Integer.parseInt(this.keyScan.nextLine());

    if (index < 0 || index >= list.size()) {
      System.out.println("인덱스가 유효하지 않습니다.");
      return;
    }

    Teacher teacher = (Teacher)list.get(index);

    System.out.printf("아이디: %s\n", teacher.userId);
    System.out.printf("암호: (***)\n");
    System.out.printf("이름: %s\n", teacher.name);
    System.out.printf("이메일: %s\n", teacher.email);
    System.out.printf("전화: %s\n", teacher.tel);
    System.out.printf("나이: %d\n", teacher.age);
    System.out.printf("담당과목: %s\n", teacher.subject);
    System.out.printf("경력: %d\n", teacher.carrer);
    System.out.printf("연봉: %d\n", teacher.salary);
    System.out.printf("주소: %s\n", teacher.address);
  }

  private void doAdd() {

    while (true) {
      Teacher teacher = new Teacher();
      System.out.print("아이디(예:hong)? ");
      teacher.userId = this.keyScan.nextLine();

      System.out.print("암호(예:1234)? ");
      teacher.password = this.keyScan.nextLine();

      System.out.print("이름(예:홍길동)? ");
      teacher.name = this.keyScan.nextLine();

      System.out.print("이메일(예:hong@test.com)? ");
      teacher.email = this.keyScan.nextLine();

      System.out.print("전화(예:010-1111-2222)? ");
      teacher.tel = this.keyScan.nextLine();

      System.out.print("나이(예:39)? ");
      teacher.age = Integer.parseInt(this.keyScan.nextLine());

      System.out.print("담당과목(예:자바)? ");
      teacher.subject = this.keyScan.nextLine();

      System.out.print("경력(예:10)? ");
      teacher.carrer = Integer.parseInt(this.keyScan.nextLine());

      System.out.print("연봉(예:8500)? ");
      teacher.salary = Integer.parseInt(this.keyScan.nextLine());

      System.out.print("주소(예:서울시 서초구 서초동)? ");
      teacher.address = this.keyScan.nextLine();

      list.add(teacher);

      System.out.print("계속 입력하시겠습니까(y/n)? ");
      if (!this.keyScan.nextLine().equals("y"))
        break;
    }
  }

  private void doList() {
    if (list.size() == 0) {
      System.out.println("리스트가 존재하지 않습니다.");
    } else {
      for (int i = 0; i < list.size(); i++) {
        Teacher teacher = (Teacher)list.get(i);
        System.out.printf("%s, %s, %s, %s, %s, %d, %s, %d, %d, %s\n",
          teacher.userId,
          teacher.password,
          teacher.name,
          teacher.email,
          teacher.tel,
          teacher.age,
          teacher.subject,
          teacher.carrer,
          teacher.salary,
          teacher.address);
      }
    }
  }
}
