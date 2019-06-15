package com.dragon.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dragon.common.LoginRegister;
import com.dragon.common.StringConstant;
import com.dragon.service.LoginRegisterService;

/**
 * Servlet implementation class LoginRegisterServlet
 */
@WebServlet("/LoginRegister")
public class LoginRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginRegisterServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 设置请求数据的解码字符集
	
		HttpSession session = request.getSession();
		
		String loginRegist = request.getParameter("loginRegist");
		
		int studentId = Integer.parseInt(request.getParameter("input-login-register"));
		
		String password = request.getParameter("inputPassword");
		
		LoginRegisterService loginRegisterService = LoginRegisterService.INSTANCE;
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("LoginRegist.html");
		
		if(loginRegist.equals("login")) {
			LoginRegister loginRegistNameAndPassword = loginRegisterService.
					retrieveLoginRegistNameAndPassword(studentId);
			if(studentId == loginRegistNameAndPassword.getStudentId() && password.equals(loginRegistNameAndPassword.getPassword())) {
				dispatcher = request.getRequestDispatcher("index.html");
			    session.setAttribute(StringConstant.SESSION_STUDENTID, studentId);
			}
		}else if(loginRegist.equals("register")) {
			loginRegisterService.createLoginRegist(studentId, password);
		}
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int studentId = Integer.parseInt(request.getParameter("studentId"));
		String studentName = request.getParameter("name");
		String sign = request.getParameter("autograph");
		String sex = request.getParameter("optionsRadios");
		String hone = request.getParameter("hometown");
		String email = request.getParameter("email");
		LoginRegister loginRegister = new LoginRegister();
		loginRegister.setEmail(email);
		loginRegister.setHone(hone);
		loginRegister.setSex(sex);
		loginRegister.setSign(sign);
		loginRegister.setStudentId(studentId);
		loginRegister.setStudentName(studentName);
		LoginRegisterService loginRegisterService = new LoginRegisterService();
		loginRegisterService.updateLoginRegistByStudentId(loginRegister);
		RequestDispatcher dispatcher = request.getRequestDispatcher("borrowrecord");
		dispatcher.forward(request, response);
	}
}
