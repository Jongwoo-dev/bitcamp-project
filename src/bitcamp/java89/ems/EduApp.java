package bitcamp.java89.ems;

import java.util.Scanner;


public class EduApp {
  public static void main(String[] args) {
    System.out.println("비트캠프 관리시스템에 오신걸 환영합니다.");

    Scanner keyScan = new Scanner(System.in);

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


    System.out.printf("아이디: %s\n", teacher.userId);
    System.out.printf("암호: %s\n", teacher.password);
    System.out.printf("이름: %s\n", teacher.name);
    System.out.printf("이메일: %s\n", teacher.email);
    System.out.printf("전화: %s\n", teacher.tel);
    System.out.printf("나이: %d\n", teacher.age);
    System.out.printf("담당과목: %s\n", teacher.subject);
    System.out.printf("경력: %d\n", teacher.carrer);
    System.out.printf("연봉: %d\n", teacher.salary);
    System.out.printf("주소: %s\n", teacher.address);

  }
}
