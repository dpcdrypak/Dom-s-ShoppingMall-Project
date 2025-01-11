package PYWMiniProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import PYWMiniProject.command.LoginCommand;
import PYWMiniProject.service.goods.MainGoodsListService;



@SpringBootApplication
@Controller
public class PywMiniProjectApplication {
	@Autowired
	MainGoodsListService mainGoodsListService;

	public static void main(String[] args) {
		SpringApplication.run(PywMiniProjectApplication.class, args);
	}

	/*
	 * @RequestMapping("/") public String index(LoginCommand loginCommand, Model
	 * model) { return "thymeleaf/initial"; }
	 */
	/*
	 * @GetMapping("/") public String index(LoginCommand loginCommand,
	 * 
	 * @RequestParam(value = "page", required = false, defaultValue = "1") Integer
	 * page, Model model) { mainGoodsListService.execute(page, model); return
	 * "thymeleaf/initial"; }
	 */
	
	@GetMapping("/")
    public String index(LoginCommand loginCommand, Model model) {
        return "thymeleaf/initial";
    }

	// GET: 초기 렌더링
	@GetMapping("/shopGoods")
	public String shopGoods(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page, 
	                        Model model) {
	    mainGoodsListService.execute(page, model); // 초기 상품 목록
	    return "thymeleaf/shopGoods"; // goodsList.html로 이동
	}

	// POST: AJAX를 통한 데이터 로드
	@PostMapping("/shopGoods")
	public String loadMoreGoods(@RequestParam("page") Integer page, Model model) {
	    mainGoodsListService.execute(page, model);
	    return "jsonView"; // JSON 데이터를 반환
	}



   

	

}
