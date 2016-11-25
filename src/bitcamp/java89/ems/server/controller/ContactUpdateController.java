package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;

public class ContactUpdateController extends AbstractCommand {

  // contact/update?name=홍길동444up&position=과장&tel=222-2222&email=hong2@test.com
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out) throws Exception {
    ContactDao contactDao = ContactDao.getInstance();
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
