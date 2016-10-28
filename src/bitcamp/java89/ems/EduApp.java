package bitcamp.java89.ems;

import java.util.Scanner;

public class EduApp {
  static Scanner keyScan = new Scanner(System.in);
  static Teacher[] teachers = new Teacher[100];
  static int length = 0;

  public static void main(String[] args) {
    System.out.println();
    System.out.println("비트캠프 관리시스템에 오신걸 환영합니다.");
    System.out.println("명령어 목록 : [ add ] = 항목 추가, [ list ] = 항목 리스트, " +
      "[ view ] = 항목 상세보기, [ quit ] = 관리 시스템 종료");
    System.out.println();

    //boolean run = true;

    loop:
    while (true) {
      System.out.print("명령> ");
      String cmd = keyScan.nextLine().toLowerCase();

      switch (cmd) {
      case "add": doAdd(); break;
      case "list": doList(); break;
      case "view": doView(); break;
      case "quit": System.out.println("Good bye!"); break loop;
      default:
        System.out.println("지원하지 않는 명령어입니다.");
      }
    }
  }

  static void doView() {
    int target = 0;
    //Scanner keyScan = new Scanner(System.in);
    System.out.print("강사 아이디? ");
    String viewId = keyScan.nextLine().toLowerCase();

    for (int i = 0; i < length; i++) {
      if (teachers[i].userId.toLowerCase().equals(viewId)) {
        System.out.printf("아이디: %s\n", teachers[i].userId);
        System.out.printf("암호: (***)\n");
        System.out.printf("이름: %s\n", teachers[i].name);
        System.out.printf("이메일: %s\n", teachers[i].email);
        System.out.printf("전화: %s\n", teachers[i].tel);
        System.out.printf("나이: %d\n", teachers[i].age);
        System.out.printf("담당과목: %s\n", teachers[i].subject);
        System.out.printf("경력: %d\n", teachers[i].carrer);
        System.out.printf("연봉: %d\n", teachers[i].salary);
        System.out.printf("주소: %s\n", teachers[i].address);
        break;
      }
    }
    System.out.println();
  }

  static void doAdd() {
    Teacher teacher = new Teacher();
    //Scanner keyScan = new Scanner(System.in);

    System.out.print("아이디(예:hong)? ");
    teacher.userId = keyScan.nextLine();

    System.out.print("암호(예:1234)? ");
    teacher.password = keyScan.nextLine();

    System.out.print("이름(예:홍길동)? ");
    teacher.name = keyScan.nextLine();

    System.out.print("이메일(예:hong@test.com)? ");
    teacher.email = keyScan.nextLine();

    System.out.print("전화(예:010-1111-2222)? ");
    teacher.tel = keyScan.nextLine();

    System.out.print("나이(예:39)? ");
    teacher.age = Integer.parseInt(keyScan.nextLine());

    System.out.print("담당과목(예:자바)? ");
    teacher.subject = keyScan.nextLine();

    System.out.print("경력(예:10)? ");
    teacher.carrer = Integer.parseInt(keyScan.nextLine());

    System.out.print("연봉(예:8500)? ");
    teacher.salary = Integer.parseInt(keyScan.nextLine());

    System.out.print("주소(예:서울시 서초구 서초동)? ");
    teacher.address = keyScan.nextLine();

    System.out.println();

    teachers[length++] = teacher;
  }

  static void doList() {
    if (length == 0) {
      System.out.println("리스트가 존재하지 않습니다.");
      System.out.println();
    } else {
      Teacher teacher;
      for (int i = 0; i < length; i++) {
        teacher = teachers[i];
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
      System.out.println();
    }
  }
}
