package PYWMiniProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PYWMiniProject.mapper.LoginMapper;

@Service
public class IdCheckService {
	@Autowired
	LoginMapper loginMapper;
	public Integer execute(String userId) {
		return loginMapper.idCheckSelectOne(userId);
	}
}