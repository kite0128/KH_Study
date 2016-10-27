package part03;

import org.springframework.transaction.annotation.Transactional;

public class ServiceImp implements Service {

	private MemDaoImp dao;

	public ServiceImp() {

	}

	public void setDao(MemDaoImp dao) {
		this.dao = dao;
	}

	@Transactional(rollbackFor = java.lang.Exception.class)
	@Override
	public void insertProcess() {

		dao.insertMethod(new MemDTO(60, "용팔이", 50, "경기"));
		dao.insertMethod(new MemDTO(61, "우대위", 20, "대전"));

	}// end insertProcess()

	@Transactional(rollbackFor = java.lang.Exception.class)
	@Override
	public void testProcess() throws Exception {
		// 트랜잭션이 설정된 메소드에서는 try~catch을 설정하면 안되고 메소드를 호출하는 곳에서 try~catch를 한다

		dao.insertMethod(new MemDTO(64, "박효신", 50, "경기"));
		dao.insertMethod(new MemDTO(64, "김범수", 20, "대전"));

	}

}// end class
