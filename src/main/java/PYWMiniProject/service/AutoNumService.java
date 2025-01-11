package PYWMiniProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import PYWMiniProject.mapper.AutoNumMapper;

@Service
public class AutoNumService {
	@Autowired
	AutoNumMapper autoNumMapper;
	
	public String execute(String sep, String columnName, Integer len, String tableName) {
		String autoNum = autoNumMapper.AutoNumSelect(sep, columnName, len, tableName);
		if (autoNum == null || autoNum.isEmpty()) {
	        System.out.println("AutoNum 생성 실패");
	    } else {
	        System.out.println("Generated AutoNum: " + autoNum);
	    }        
		return autoNum;
	}
}
