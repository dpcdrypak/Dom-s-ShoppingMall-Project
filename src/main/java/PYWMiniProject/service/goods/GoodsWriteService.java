package PYWMiniProject.service.goods;

import java.io.File;
import java.net.URL;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import PYWMiniProject.command.GoodsCommand;
import PYWMiniProject.domain.AuthInfoDTO;
import PYWMiniProject.domain.GoodsDTO;
import PYWMiniProject.mapper.EmployeeMapper;
import PYWMiniProject.mapper.GoodsMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class GoodsWriteService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(GoodsCommand goodsCommand, HttpSession session) {
	    GoodsDTO dto = new GoodsDTO();
	    dto.setGoodsContents(goodsCommand.getGoodsContents());
	    dto.setGoodsName(goodsCommand.getGoodsName());
	    dto.setGoodsNum(goodsCommand.getGoodsNum());
	    dto.setGoodsPrice(goodsCommand.getGoodsPrice());

	    AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
	    String empNum = employeeMapper.getEmpNum(auth.getUserId());
	    dto.setEmpNum(empNum);

	    // 경로 설정: static/upload
	    URL resource = getClass().getClassLoader().getResource("static/upload");
	    if (resource == null) {
	        throw new IllegalStateException("Upload directory does not exist.");
	    }
	    String fileDir = resource.getPath();

	    File uploadDir = new File(fileDir);
	    if (!uploadDir.exists()) {
	        uploadDir.mkdirs(); // 경로가 없으면 생성
	    }

	    // 메인 이미지 저장
	    MultipartFile mf = goodsCommand.getGoodsMainImage();
	    String originalFile = mf.getOriginalFilename();
	    String extension = originalFile.substring(originalFile.lastIndexOf("."));
	    String storeName = UUID.randomUUID().toString().replace("-", "");
	    String storeFileName = storeName + extension;

	    File file = new File(uploadDir, storeFileName);
	    try {
	        mf.transferTo(file);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    dto.setGoodsMainImage(originalFile);
	    dto.setGoodsMainStoreImage(storeFileName);

	    // 상세 이미지 저장
	    if (goodsCommand.getGoodsDetailImage() != null && goodsCommand.getGoodsDetailImage().length > 0) {
	        StringBuilder originalTotal = new StringBuilder();
	        StringBuilder storeTotal = new StringBuilder();

	        for (MultipartFile mpf : goodsCommand.getGoodsDetailImage()) {
	            originalFile = mpf.getOriginalFilename();
	            extension = originalFile.substring(originalFile.lastIndexOf("."));
	            storeName = UUID.randomUUID().toString().replace("-", "");
	            storeFileName = storeName + extension;

	            file = new File(uploadDir, storeFileName);
	            try {
	                mpf.transferTo(file);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }

	            originalTotal.append(originalFile).append("/");
	            storeTotal.append(storeFileName).append("/");
	        }

	        dto.setGoodsDetailImage(originalTotal.toString());
	        dto.setGoodsDetailStoreImage(storeTotal.toString());
	    }

	    goodsMapper.goodsInsert(dto);
	}
}
















