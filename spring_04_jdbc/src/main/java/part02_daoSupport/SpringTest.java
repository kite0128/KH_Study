package part02_daoSupport;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("part02_daoSupport/jdbc.xml");

		MemDao dao = (MemDao) context.getBean("dao");
		// dao.insertMethod(new MemDTO("박나리", 40, "서울"));
		//dao.updateMethod(new MemDTO(6, "김나리"));
		//dao.deleteMethod(6);
		
		/*List<MemDTO> list = dao.list();
		for (MemDTO dto : list) {
			System.out.printf("%d %s %d %s\n", dto.getNum(), dto.getName(), dto.getAge(), dto.getLoc());
		}*/
		 
		/*MemDTO dto = dao.one(5);
		System.out.printf("%d %s %d %s\n", dto.getNum(), dto.getName(), dto.getAge(), dto.getLoc());*/
		System.out.println("count : " + dao.countMethod());

	}

}
