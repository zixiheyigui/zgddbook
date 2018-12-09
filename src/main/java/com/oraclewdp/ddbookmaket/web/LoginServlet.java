package com.oraclewdp.ddbookmaket.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import com.oraclewdp.ddbookmaket.biz.AdminBiz;
import com.oraclewdp.ddbookmaket.biz.impl.AdminBizImpl;
import com.oraclewdp.ddbookmaket.model.Admin;
import com.oraclewdp.ddbookmaket.util.MyBeanUtils;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("utf-8");
		//获取参数值
		Admin admin=new Admin();
	    MyBeanUtils.populate(admin, request.getParameterMap());
	   //获取验证码进行比对错了回填，对了继续执行
	     String vcode=request.getParameter("Vcode");
	    String serverVcode= (String) request.getSession().getAttribute("validateCode");
//	   if (!(serverVcode.equalsIgnoreCase(vcode))){
//	    	request.setAttribute("msg","验证码错误");
//			request.setAttribute("admin", admin);
//			request.getRequestDispatcher("login.jsp").forward(request, response);
//			return;
//		}
//		ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();
//		Validator validator=validatorFactory.getValidator();
//		Set<ConstraintViolation<Admin>> validatResult=validator.validate(admin);
//		if(validatResult.size()>0){//验证没有通过
//			Map<String, String>errors=new HashMap<>();
//			for (ConstraintViolation<Admin> cv:
//				validatResult ) {
//				errors.put(cv.getPropertyPath().toString(),cv.getMessage());
//			}
//			request.setAttribute("errors",errors);
//			request.setAttribute("admin", admin);
//			request.getRequestDispatcher("login.jsp").forward(request, response);
//			return;
//		}
		Map<String, String>errors=new HashMap<>();
		if (!(serverVcode.equalsIgnoreCase(vcode))){
	    	errors.put("Vcode","验证码错误");
		}
		ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();
	     Validator validator=validatorFactory.getValidator();
		Set<ConstraintViolation<Admin>> validatResult=validator.validate(admin);
		if(validatResult.size()>0) {//验证没有通过
			for (ConstraintViolation<Admin> cv: validatResult ) {
				errors.put(cv.getPropertyPath().toString(),cv.getMessage());
			}
		}
		if (errors.size()>0){
			request.setAttribute("errors",errors);
			request.setAttribute("admin", admin);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}


           //到数据库进行比较
			AdminBiz adminBiz=new AdminBizImpl();
			boolean ret=adminBiz.findNameAdmin(admin);
			if (ret) {
				request.getSession().setAttribute("hasLongined",true);
			request.getRequestDispatcher("main.jsp").forward(request, response);
		} else {
			request.setAttribute("admin", admin);
			request.getRequestDispatcher("login.jsp").forward(request, response);

		}
	}

}
