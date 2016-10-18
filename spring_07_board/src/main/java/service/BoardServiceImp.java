package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.BoardDAO;
import dto.BoardDTO;
import dto.PageDTO;

public class BoardServiceImp implements BoardService {
	private BoardDAO dao;

	public BoardServiceImp() {

	}

	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}

	@Override
	public int countProcess() {

		return dao.count();
	}

	@Override
	public List<BoardDTO> listProcess(PageDTO pv) {

		return dao.list(pv);
	}

	@Override
	public void insertProcess(BoardDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public BoardDTO contentProcess(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reStepProcess(BoardDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public BoardDTO updateSelectProcess(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProcess(BoardDTO dto, HttpServletRequest request) {
		// TODO Auto-generated method stub

	}

	@Override
	public String deleteProcess(int num, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
