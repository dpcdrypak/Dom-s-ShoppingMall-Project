package PYWMiniProject.command;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GoodsCommand {
	String goodsNum;
	@NotEmpty(message = "이름을 입력해주세요")
	String goodsName;
	@NotNull(message = "가격을 입력해주세요.")
	Integer goodsPrice;
	@NotEmpty(message = "설명을 입력해주세요")
	String goodsContents;
	String empNum;
	Integer visitCount;
	Date goodsRegist;
	String updateEmpNum;
	Date goodsUpdateDate;
	MultipartFile goodsMainImage;
	MultipartFile goodsDetailImage[];

}
