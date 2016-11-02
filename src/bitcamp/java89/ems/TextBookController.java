package bitcamp.java89.ems;
import java.util.Scanner;

public class TextBookController {
  // 아래 인스턴스 변수들은 외부에서 사용할 일이 없기 때문에
  // private으로 접근을 제한한다.
  private TextBook[] textBooks = new TextBook[100];
  private int length = 0;
  private Scanner keyScan;

//기본 생성자가 없다. 따라서 이 클래스를 사용하려면 반드시 Scanner를 줘야 한다.
// => 생성자에서 하는 일은 그 객체를 사용하기 전에 유효상태로 만드는 것이다.
  public TextBookController(Scanner keyScan) {
    this.keyScan = keyScan;
  }

  public void service() {
    loop:
    while(true) {
       System.out.print("교재관리> ");
      String command = keyScan.nextLine().toLowerCase();

      switch (command) {
        case "add": this.doAdd(); break;
        case "list": this.doList(); break;
        case "view": this.doView(); break;
        case "update": this.doUpdate(); break;
        case "delete": this.doDelete(); break;
        case "main": break loop;
        default :
          System.out.println("지원하지 않는 명령입니다.");
          break;
      }
    }
  }
  // 아래 doxx() 메서드들은 오직 service()에서만 호출하기 때문에
  // private으로 접근을 제한한다.
  private void doAdd() {
    while (length < this.textBooks.length) {
      TextBook textbook = new TextBook();

      System.out.print("책이름(ex: java's best practice)? ");
      textbook.title = this.keyScan.nextLine();

      System.out.print("저자(ex: mr.Nam)? ");
      textbook.author = this.keyScan.nextLine();

      System.out.print("출판사(ex: Dow)? ");
      textbook.press = this.keyScan.nextLine();

      System.out.print("출판년도(ex: 2016)? ");
      textbook.releaseDate = this.keyScan.nextLine();

      System.out.print("언어(ex: Korean)? ");
      textbook.language = this.keyScan.nextLine();

      System.out.print("설명(ex: Do you want to feel java from basic to OOP? ");
      textbook.description = this.keyScan.nextLine();

      System.out.print("쪽수(ex: 520)? ");
      textbook.page = Integer.parseInt(this.keyScan.nextLine());

      System.out.print("가격(ex: 30000)? ");
      textbook.price = Integer.parseInt(this.keyScan.nextLine());

      this.textBooks[length++] = textbook;

      System.out.print("계속 입력하시겠습니까(y/n)? ");
      if (!this.keyScan.nextLine().equals("y"))
        break;
      System.out.println();
    }
  }

  private void doList() {
    for (int i = 0; i < this.length; i++) {
      TextBook textbook = this.textBooks[i];
      System.out.printf("%s,%s,%s,%s,%s,%s,%d,%d\n",
        textbook.title,
        textbook.author,
        textbook.press,
        textbook.releaseDate,
        textbook.language,
        textbook.description,
        textbook.page,
        textbook.price);
      System.out.println();
    }
  }
  private void doView() {
    System.out.println("책이름을 입력하세요");
    String userTitle = this.keyScan.nextLine().toLowerCase();

    for(int i = 0; i <this.length;  i++) {

      if(this.textBooks[i].title.toLowerCase().equals(userTitle)) {
        System.out.println("-----------------------------------");
        System.out.printf("책이름: %s\n",this.textBooks[i].title);
        System.out.printf("저자: %s\n",this.textBooks[i].author);
        System.out.printf("출판사: %s\n",this.textBooks[i].press);
        System.out.printf("출판년도: %s\n",this.textBooks[i].releaseDate);
        System.out.printf("언어: %s\n",this.textBooks[i].language);
        System.out.printf("설명: %s\n",this.textBooks[i].description);
        System.out.printf("쪽수: %d\n",this.textBooks[i].page);
        System.out.printf("가격: %d\n",this.textBooks[i].price);
        System.out.println();
        break;
      }
    }
  }
  private void doDelete() {
    System.out.println("삭제할 리스트의 책이름은? ");
    String userTitle = this.keyScan.nextLine().toLowerCase();

    for(int i = 0; i < this.length;  i++) {
      if(this.textBooks[i].title.toLowerCase().equals(userTitle)) {
        System.out.print("진짜 삭제하시겠습니까?(y/n) ");
        if(!keyScan.nextLine().toLowerCase().equals("y")) {
          System.out.println("삭제를 취소하였습니다.");
          return;}
      //배열의 앞 항목의 값을 현재 항목으로 당겨온다.
        for(int x = i + 1; x < this.length; x++, i++) {
          this.textBooks[i] = this.textBooks[x];
        }
        this.textBooks[--length] = null;

        System.out.printf("%s 책 정보를 삭제하였습니다.\n", userTitle);
        return;  //함수 실행 종료
      }
    }
    System.out.printf("%s 책이 없습니다.\n", userTitle);
  }

  private void doUpdate() {
    System.out.print("변경할 책의 이름은? ");
    String userTitle = this.keyScan.nextLine().toLowerCase();
    TextBook textbook = new TextBook();

    for(int i = 0; i < this.length; i++) {

      if(this.textBooks[i].title.toLowerCase().equals(userTitle)) {
        textbook.title = textBooks[i].title;

        System.out.print("저자(ex: mr.Nam)? ");
        textbook.author = this.keyScan.nextLine();

        System.out.print("출판사(ex: Dow)? ");
        textbook.press = this.keyScan.nextLine();

        System.out.print("출판년도(ex: 2016)? ");
        textbook.releaseDate = this.keyScan.nextLine();

        System.out.print("언어(ex: Korean)? ");
        textbook.language = this.keyScan.nextLine();

        System.out.print("설명(ex: Do you want to feel java from basic to OOP? ");
        textbook.description = this.keyScan.nextLine();

        System.out.print("쪽수(ex: 520)? ");
        textbook.page = Integer.parseInt(this.keyScan.nextLine());

        System.out.print("가격(ex: 30000)? ");
        textbook.price = Integer.parseInt(this.keyScan.nextLine());

        System.out.print("저장하시겠습니까(y/n)?");
        if(keyScan.nextLine().toLowerCase().equals("y")) {
          textBooks[i] = textbook;
          System.out.println("저장하였습니다.");
        }else{
          System.out.println("변경을 취소하였습니다.");
        }
        return;
      } //if end
    }  //for end
    System.out.printf("%s 이라는 책이 없습니다.\n", userTitle);

  }






}
