package bitcamp.java89.ems;

import java.util.Scanner;

public class EduApp {
  static Scanner keyScan = new Scanner(System.in);

  public static void main(String[] args) {
    //EduApp에서 사용하는 keyScan을 StudentController와 공유한다.
    TeacherController.keyScan = keyScan;

    System.out.println("비트캠프 관리시스템에 오신걸 환영합니다.");

    loop:
    while (true) {
      System.out.print("명령> ");
      String cmd = keyScan.nextLine().toLowerCase();

      switch (cmd) {
      case "add": TeacherController.doAdd(); break;
      case "list": TeacherController.doList(); break;
      case "view": TeacherController.doView(); break;
      case "quit": System.out.println("Good bye!"); break loop;
      default:
        System.out.println("지원하지 않는 명령어입니다.");
      }
    }
  }
}
