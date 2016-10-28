package bitcamp.java89.ems;

import java.util.Scanner;


public class EduApp {
  public static void main(String[] args) {
    System.out.println("비트캠프 관리시스템에 오신걸 환영합니다.");

    Scanner keyScan = new Scanner(System.in);

    Teacher[] teachers = new Teacher[100];
    int length = 0;

    while (length < teachers.length) {
      Teacher teacher = new Teacher();
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

      teachers[length++] = teacher;

      System.out.print("계속 입력하시겠습니까(y/n)? ");
      if (!keyScan.nextLine().equals("y"))
        break;
    }

    printTeacherList(teachers, length);

  }

  static void printTeacherList(Teacher[] teachers, int length) {
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
  }
}
