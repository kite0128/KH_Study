package part02;

public class ServiceImp implements Service {

	private MemDaoImp dao;

	public ServiceImp() {

	}

	public void setDao(MemDaoImp dao) {
		this.dao = dao;
	}

	@Override
	public void insertProcess() {

		dao.insertMethod(new MemDTO(52, "용팔이", 50, "경기"));
		dao.insertMethod(new MemDTO(53, "우대위", 20, "대전"));

	}// end insertProcess()

}// end class
