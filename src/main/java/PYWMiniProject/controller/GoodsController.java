package PYWMiniProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import PYWMiniProject.command.GoodsCommand;
import PYWMiniProject.service.AutoNumService;
import PYWMiniProject.service.goods.GoodsDeleteService;
import PYWMiniProject.service.goods.GoodsDetailService;
import PYWMiniProject.service.goods.GoodsListService;
import PYWMiniProject.service.goods.GoodsUpdateService;
import PYWMiniProject.service.goods.GoodsWriteService;
import PYWMiniProject.service.goods.ProductsDeleteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("goods")
public class GoodsController {
	@Autowired
	GoodsListService goodsListService;
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	GoodsWriteService goodsWriteService;
	@Autowired
	ProductsDeleteService productsDeleteService;
	@Autowired
	GoodsDetailService goodsDetailService;
	@Autowired
	GoodsUpdateService goodsUpdateService;
	@Autowired
	GoodsDeleteService goodsDeleteService;

	@RequestMapping("goodsList")
	public String goodsList(
			@RequestParam(value="searchWord", required = false) String searchWord
			, @RequestParam(value="page", required = false, defaultValue = "1") int page
			, Model model) {
		goodsListService.execute(searchWord, page, model);
		return "thymeleaf/goods/goodsList";
	}
	@GetMapping("goodsWrite")
	public String goodsForm(Model model) {
		String autoNum = autoNumService.execute("goods_", "goods_num", 7, "goods");
		GoodsCommand  goodsCommand = new GoodsCommand();
		goodsCommand.setGoodsNum(autoNum);
		model.addAttribute("goodsCommand", goodsCommand);
		return "thymeleaf/goods/goodsForm";
	}
	
	@RequestMapping(value="goodsWrite" , method=RequestMethod.POST)
	public String goodsWrite(@Validated GoodsCommand goodsCommand,BindingResult result
			,HttpSession session) {
		if(result.hasErrors()) {
			return "thymeleaf/goods/goodsForm";
		}
		goodsWriteService.execute(goodsCommand, session);
		return "thymeleaf/goods/goodsRedirect";
		//return "redirect:goodsList";
	}

	@PostMapping("productsDelete")
	public String productsDelete(//체크박스에 의해 전달 된 값을 배열로 받습니다.
			@RequestParam(value = "nums") String memDels[]) {
		productsDeleteService.execute(memDels);
		return "redirect:goodsList";
	}
	
	@GetMapping("goodsDetail")
	public String goodsDetail(@RequestParam("goodsNum") String goodsNum
			,Model model,HttpSession session) {
		session.removeAttribute("fileList");
		goodsDetailService.execute(goodsNum, model);
		return "thymeleaf/goods/goodsInfo";
		
	}
	
	@GetMapping("goodsUpdate")
	public String goodsUpdate(@RequestParam("goodsNum") String goodsNum, Model model
			, HttpSession session) {
		// 삭제할 파일을 선택한 후 다시 수정페이지로 오면 삭제할 파일정보를 가진 session이 존재하여 오류의 소지가 있다.
		// 그래서 수정 페이지에 오면 삭제할 파일정보를 가진 session을 제거 하는 것이 좋다. 
		session.removeAttribute("fileList"); // 삭제할 파일 정보를 가지고 있는 session삭제
		goodsDetailService.execute(goodsNum, model);//수정을 하려면 기본 정보를 가져와야 하므로 goodsDetailService를 사용
		return "thymeleaf/goods/goodsModify";
	}
	
	
	@PostMapping("goodsUpdate")
	public String goodsUpdate(@Validated GoodsCommand goodsCommand,BindingResult result,
			HttpSession session, Model model) {
		goodsUpdateService.execute(goodsCommand, session, result, model);
		if(result.hasErrors()) {
			return "thymeleaf/goods/goodsModify";
		}
		return "redirect:goodsDetail?goodsNum="+goodsCommand.getGoodsNum();
	}
	
	
	@RequestMapping("goodsDel/{goodsNum}")
	public String goodsDel(@PathVariable("goodsNum") String goodsNum) {
		goodsDeleteService.execute(goodsNum);
		return "redirect:../goodsList"; //PathVariable인 경우에는 주소 앞에 .. 을 꼭해줘야 합니다.
	}
	
}