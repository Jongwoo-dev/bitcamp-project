package bitcamp.java89.ems.server;

import java.util.Scanner;

import bitcamp.java89.ems.server.controller.ClassroomController;
import bitcamp.java89.ems.server.controller.ContactController;
import bitcamp.java89.ems.server.controller.StudentController;
import bitcamp.java89.ems.server.controller.TeacherController;
import bitcamp.java89.ems.server.controller.TextBookController;

public class EduAppServer {
  static Scanner keyScan = new Scanner(System.in);
  static TeacherController teacherController;
  static ClassroomController classroomController;
  static TextBookController textBookController;
  static StudentController studentController;
  static ContactController contactController;

  public static void main(String[] args) throws Exception {
    //EduApp에서 사용하는 keyScan을 StudentController와 공유한다.
    teacherController = new TeacherController(keyScan);
    classroomController = new ClassroomController(keyScan);
    textBookController = new TextBookController(keyScan);
    studentController = new StudentController(keyScan);
    contactController = new ContactController(keyScan);

    System.out.println("비트캠프 관리시스템에 오신걸 환영합니다.");

    loop:
    while (true) {
      System.out.print("명령> ");
      String command = keyScan.nextLine().toLowerCase();

      switch (command) {
      case "menu": doMenu(); break;
      case "go 1": studentController.service(); break;
      case "go 2": teacherController.service(); break;
      case "go 3": classroomController.service(); break;
      case "go 4": textBookController.service(); break;
      case "go 5": contactController.service(); break;
      case "save": doSave(); break;
      case "quit": 
        if (doQuit())
          break loop;
        break;
      default:
         System.out.println("지원하지 않는 명령어입니다.");
      }
    }
  }

  static void doMenu() {
    System.out.println("[메뉴]");
    System.out.println("1. 학생관리");
    System.out.println("2. 강사관리");
    System.out.println("3. 강의실관리");
    System.out.println("4. 교재관리");
    System.out.println("5. 연락처관리");
    System.out.println("메뉴 이동은 'go 메뉴번호'를 입력하세요.");
    System.out.println("[명령]");
    System.out.println("save   데이터 저장");
    System.out.println("quit   프로그램 종료");
  }

  static boolean doQuit() {
    boolean changed = teacherController.isChanged();
    if (changed) {
      System.out.print("강사 정보가 변경 되었습니다. 그래도 종료하시겠습니까?(y/n) ");
      if (!keyScan.nextLine().toLowerCase().equals("y"))
        return false;
      System.out.println("강사 정보가 변경된 것을 취소하고 종료합니다.");
    }
    System.out.println("Good bye!");
    return true;
  } 

  static void doSave() {
    try {
      teacherController.save();
      System.out.println("저장하였습니다.");
    } catch (Exception e) {
      System.out.println("파일 저장 중에 오류가 발생했습니다.");
    }
  }
}
