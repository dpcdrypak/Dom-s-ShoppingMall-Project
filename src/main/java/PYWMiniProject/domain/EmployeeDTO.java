package PYWMiniProject.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data

@Alias("employeeDTO")
public class EmployeeDTO {
	String empNum;
	String empName;
	String empId;
	String empPw;
	String empAddr;
	String empAddrDetail;
	String empPost;
	String empPhone;
	String empJumin;
	String empEmail;
	Date empHireDate;
	String empImag;
	

}
