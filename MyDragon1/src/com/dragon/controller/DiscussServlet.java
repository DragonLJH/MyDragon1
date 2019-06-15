package com.dragon.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dragon.common.Discuss;
import com.dragon.common.Management;
import com.dragon.common.StringConstant;
import com.dragon.service.DiscussService;
import com.dragon.service.ManagementService;

/**
 * Servlet implementation class DiscussServlet
 */
@WebServlet("/discuss")
public final class DiscussServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DiscussServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpServletRequest httpReq = (HttpServletRequest) request; // 为用getSession方法

		HttpSession session = httpReq.getSession();

		int studentId = (int) session.getAttribute(StringConstant.SESSION_STUDENTID);

		int booknumber = (int) request.getAttribute(StringConstant.REQ_MANAGEMENT_BOOKNUMBER);

		List listManagement = (List) request.getAttribute(StringConstant.REQ_MANAGEMENT);

		request.setAttribute(StringConstant.REQ_MANAGEMENT, listManagement);

		DiscussService discussService = new DiscussService();

		List discussList = discussService.retrieveDiscussByBooknumber(booknumber);

		request.setAttribute(StringConstant.REQ_DISCUSS, discussList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("discuss.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpServletRequest httpReq = (HttpServletRequest) request; // 为用getSession方法

		HttpSession session = httpReq.getSession();
		int studentId = (int) session.getAttribute(StringConstant.SESSION_STUDENTID);

		int booknumber = Integer.parseInt(request.getParameter("booknumber"));
		String selectName = request.getParameter("selectName");
		
		String summary = request.getParameter("summary");

		DiscussService discussService = new DiscussService();

		Discuss discuss = new Discuss();

		discuss.setBooknumber(booknumber);
		discuss.setStudentId(studentId);
		discuss.setDiscuss(summary);

		discussService.createDiscuss(discuss,studentId);
		
		request.setAttribute(StringConstant.REQ_MANAGEMENT_BOOKNUMBER,booknumber);
		
		ManagementService managementService = ManagementService.INSTANCE;
		List<Management> listManagement = managementService.retrieveManagementByselectName("name",selectName);
		request.setAttribute(StringConstant.REQ_MANAGEMENT,listManagement);

		doGet(request, response);

	}

}
