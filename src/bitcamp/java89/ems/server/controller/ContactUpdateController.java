package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

@Component(value="contact/update") // ApplicationContext가 관리하는 대상 클래스임을 표시한다.
public class ContactUpdateController{
  // 의존 객체 DAO를 저장할 변수 선언
  ContactDao contactDao;

  // 의존 객체 주입할 때 호출할 셋터 추가.
  public void setContactDao(ContactDao contactDao) {
    this.contactDao = contactDao;
  }
  
  // contact/update?name=홍길동444up&position=과장&tel=222-2222&email=hong2@test.com
  @RequestMapping
  public void update(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    if (!contactDao.existEmail(paramMap.get("email"))) {
      out.println("이메일을 찾지 못했습니다.");
      return;
    }

    Contact contact = new Contact();
    contact.setEmail(paramMap.get("email"));
    contact.setName(paramMap.get("name"));
    contact.setPosition(paramMap.get("position"));
    contact.setTel(paramMap.get("tel"));

    contactDao.update(contact);
    out.println("변경 하였습니다.");
  }
}
