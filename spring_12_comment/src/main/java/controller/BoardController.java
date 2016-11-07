package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
		// mav.setViewName("boardView");
		mav.setViewName("boardView3");
		return mav;
	}// end boardViewPage()

	@RequestMapping("/replyInsertList.do")
	public @ResponseBody List<ReplyDTO> replyListPage(ReplyDTO rdto, HttpServletRequest request) {
		
		if (rdto.getFilename() != null) {
			MultipartFile file = rdto.getFilename();
			if (!file.isEmpty()) {
				String fileName = file.getOriginalFilename();

				// 중복파일명을 처리하기 위해 난수 발생
				UUID random = UUID.randomUUID();
				String root = request.getSession().getServletContext().getRealPath("/");
				// root+"temp/"
				String saveDirectory = root + "temp" + File.separator;
				File fe = new File(saveDirectory);
				if (!fe.exists())
					fe.mkdir();

				File ff = new File(saveDirectory, random + "_" + fileName);
				try {
					FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(ff));
				} catch (FileNotFoundException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
				rdto.setRupload(random + "_" + fileName);
				System.out.println(saveDirectory);
			}
		}

		return service.replyListProcess(rdto);
	}// end replyListPage()

	@RequestMapping("/replyDelete.do")
	public @ResponseBody List<ReplyDTO> replyDeleteListPage(ReplyDTO rdto, HttpServletRequest req) {
		return service.replyDeleteProcess(rdto, req);
	}// END replyDeleteListPage()

	@RequestMapping("/replyUpdate.do")
	public @ResponseBody List<ReplyDTO> replyUpdateListPage(ReplyDTO rdto, HttpServletRequest req) {
		return service.replyUpdateProcess(rdto, req);
	}// end replyUpdateListPage()

	@RequestMapping("/contentdownload.do")
	public ModelAndView downMethod(int rno) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("rno", rno);
		mav.setViewName("download");
		return mav;
	}

}
