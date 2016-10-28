package bitcamp.java89.ems;

import java.util.Scanner;

public class TeacherController {
  // 아래 인스턴스 변수들은 외부에서 사용할 일이 없기 때문에
  // private으로 접근을 제한한다.
  private Teacher[] teachers = new Teacher[100];
  private int length = 0;
  private Scanner keyScan;

  public TeacherController(Scanner keyScan) {
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
    System.out.print("변경할 강사의 아이디는? ");
    String userId = this.keyScan.nextLine().toLowerCase();

    for (int i = 0; i < this.length; i++) {
      if (this.teachers[i].userId.toLowerCase().equals(userId)) {
        Teacher teacher = new Teacher();

        teacher.userId = this.teachers[i].userId;

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

        System.out.print("저장하시겠습니까(y/n)? ");
        if (this.keyScan.nextLine().toLowerCase().equals("y")) {
          this.teachers[i] = teacher;
          System.out.println("저장하였습니다.");
        } else {
          System.out.println("변경을 취소하였습니다.");
        }
        return;
      }
    }
    System.out.printf("%s 강사가 없습니다.\n", userId);
  }

  private void doDelete() {
    System.out.print("삭제할 강사의 아이디는? ");
    String userId = this.keyScan.nextLine().toLowerCase();

    for (int i = 0; i < this.length; i++) {
      if (this.teachers[i].userId.toLowerCase().equals(userId)) {
        //배열의 앞 항목의 값을 현재 항목으로 당겨온다.
        for (int x = i + 1; x < this.length; x++, i++) {
          this.teachers[i] = this.teachers[x];
        }
        this.teachers[--length] = null;
        System.out.printf("%s 강사 정보를 삭제하였습니다.\n", userId);
        return;
      }
    }
    System.out.printf("%s 강사가 없습니다.\n", userId);
  }

  private void doView() {
    //int target = 0;
    //Scanner keyScan = new Scanner(System.in);
    System.out.print("강사 아이디? ");
    String viewId = this.keyScan.nextLine().toLowerCase();

    for (int i = 0; i < this.length; i++) {
      if (this.teachers[i].userId.toLowerCase().equals(viewId)) {
        System.out.printf("아이디: %s\n", this.teachers[i].userId);
        System.out.printf("암호: (***)\n");
        System.out.printf("이름: %s\n", this.teachers[i].name);
        System.out.printf("이메일: %s\n", this.teachers[i].email);
        System.out.printf("전화: %s\n", this.teachers[i].tel);
        System.out.printf("나이: %d\n", this.teachers[i].age);
        System.out.printf("담당과목: %s\n", this.teachers[i].subject);
        System.out.printf("경력: %d\n", this.teachers[i].carrer);
        System.out.printf("연봉: %d\n", this.teachers[i].salary);
        System.out.printf("주소: %s\n", this.teachers[i].address);
        break;
      }
    }
  }

  private void doAdd() {

    //Scanner keyScan = new Scanner(System.in);
    while (length < this.teachers.length) {
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

      this.teachers[length++] = teacher;
      System.out.print("계속 입력하시겠습니까(y/n)? ");
      if (!this.keyScan.nextLine().equals("y"))
        break;
    }
  }

  private void doList() {
    if (length == 0) {
      System.out.println("리스트가 존재하지 않습니다.");
    } else {
      Teacher teacher;
      for (int i = 0; i < this.length; i++) {
        teacher = this.teachers[i];
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
