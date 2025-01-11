package PYWMiniProject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import PYWMiniProject.command.CartCommand;
import PYWMiniProject.service.item.CartInsertService;
import PYWMiniProject.service.item.CartQtyDownService;
import PYWMiniProject.service.item.GoodsCartDelsService;
import PYWMiniProject.service.item.GoodsDelsService;
import PYWMiniProject.service.item.GoodsWishService;
import jakarta.servlet.http.HttpSession;

@RestController  /// Rest API
@RequestMapping("item")
public class ItemRestController {
	@Autowired
	GoodsWishService goodsWishService;
	@Autowired
	CartInsertService cartInsertService;
	@Autowired
	CartQtyDownService cartQtyDownService;
	@Autowired
	GoodsCartDelsService goodsCartDelsService;
	@Autowired
	GoodsDelsService goodsDelsService;
	
	@RequestMapping("wishItem")
	public void wishAdd(@RequestBody Map<String, Object> map,HttpSession session) {
		goodsWishService.execute(map.get("goodsNum").toString(), session);
	}
	@RequestMapping("cartAdd")
	public String cartAdd(@RequestBody CartCommand cartCommand
			, HttpSession session) {
		System.out.println(cartCommand.getGoodsNum());
		return cartInsertService.execute(cartCommand, session);
	}
	@GetMapping("cartQtyDown")
	public void cartQtyDown(String goodsNum, HttpSession session )  {
		cartQtyDownService.execute(goodsNum, session);
	}
	@PostMapping("cartDels")
	public String cartDels(@RequestBody String goodsNums[],  HttpSession session ) {
		return goodsCartDelsService.execute(goodsNums, session);
	}
	@PostMapping("wishDels") // POST 요청을 처리
    public String deleteWishlist(@RequestBody String[] goodsNums, HttpSession session) {
        return goodsDelsService.deleteWishlistItems(goodsNums, session);
    }

}

