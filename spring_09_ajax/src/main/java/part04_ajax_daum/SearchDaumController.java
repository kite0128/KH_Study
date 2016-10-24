package part04_ajax_daum;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//cd4811e3ec40daa4b37cdba5d8383e92 =>다음 API 키
@Controller
public class SearchDaumController {

	@RequestMapping("/daumForm.do")
	public String form(){
		
		return "part04_ajax_daum/daumForm";
		
	}
}
