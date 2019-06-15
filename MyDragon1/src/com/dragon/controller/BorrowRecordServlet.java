package com.dragon.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dragon.common.LoginRegister;
import com.dragon.common.Management;
import com.dragon.common.StringConstant;
import com.dragon.service.BorrowRecordService;
import com.dragon.service.LoginRegisterService;
import com.dragon.service.ManagementService;

/**
 * Servlet implementation class BorrowRecordServlet
 */
@WebServlet("/borrowrecord")
public final class BorrowRecordServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public BorrowRecordServlet() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession();
    
    int studentId = (int) session
        .getAttribute(StringConstant.SESSION_STUDENTID);
    
    //获取用户信息
    LoginRegisterService LoginRegisterService = new LoginRegisterService(); 
    LoginRegister loginRegister = LoginRegisterService.retrieveLoginRegistAllByStudentId(studentId);
    request.setAttribute(StringConstant.REQ_LOGINREGISTER, loginRegister);
    
    String booknumber = request.getParameter("booknumber");

    booknumber = booknumber != null ? booknumber : "0";

    BorrowRecordService borrowRecordService = new BorrowRecordService();

    ManagementService managementService = new ManagementService();

    request.setAttribute(StringConstant.REQ_UPDATEBORROW, 0);

    if (!booknumber.equals("0")) {
      int result = borrowRecordService.createBorrow(studentId,
          Integer.parseInt(booknumber));

      request.setAttribute(StringConstant.REQ_UPDATEBORROW, result);
    }

    List<Integer> recordList = borrowRecordService
        .retrieveBooknumberByStudentId(studentId);

    List<Management> managementList = new ArrayList<Management>();

    for (int i = 0; i < recordList.size(); i++) {

      Management management = new Management();

      management = managementService
          .retrieveManagementAllNameAndImagesByBooknumber(recordList.get(i));

      managementList.add(management);
    }

    request.setAttribute(StringConstant.REQ_RECORD, managementList);

    // 设置转发页面为user.jsp
    RequestDispatcher dispatcher = request
        .getRequestDispatcher("user.jsp");
    // 转发
    dispatcher.forward(request, response);

  }
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
	  doGet(request, response);
  }

}
