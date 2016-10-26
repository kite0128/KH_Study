package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dto.ReplyDTO;
import service.BoardService;

@Controller
public class BoardController {
	private BoardService service;

	public BoardController() {

	}

	public void setService(BoardService service) {
		this.service = service;
	}

	@RequestMapping("/boardList.do")
	public ModelAndView boardListPage() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", service.boardListProcess());
		mav.setViewName("boardList");
		return mav;
	}// end boardListPage()

	@RequestMapping("/boardView.do")
	public ModelAndView boardViewPage(int bno) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardDTO", service.boardViewProcess(bno));
		mav.setViewName("boardView");
		return mav;
	}// end boardViewPage()

	@RequestMapping("/replyInsertList.do")
	public @ResponseBody List<ReplyDTO> replyListPage(ReplyDTO rdto) {
		return service.replyListProcess(rdto);
	}//end replyListPage()

}
