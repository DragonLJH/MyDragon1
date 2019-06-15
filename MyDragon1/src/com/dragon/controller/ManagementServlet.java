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

import com.dragon.common.Management;
import com.dragon.common.StringConstant;
import com.dragon.service.ManagementService;

/**
 * Servlet implementation class ManagementServlet
 */
@WebServlet("/management")
public final class ManagementServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ManagementServlet() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  
    String selectName = request.getParameter("selectName");
    String optionName = request.getParameter("optionName");
    String skip = request.getParameter("skip");
    
    ManagementService managementService = ManagementService.INSTANCE;
    
    List<Management> listManagement = new ArrayList<Management>();
    if (selectName != null) {
      listManagement = managementService.retrieveManagementByselectName(optionName,selectName);
      request.setAttribute(StringConstant.REQ_MANAGEMENT, listManagement);
    }
    
    List<Management> ListManagementAllName = managementService.retrieveManagementAllNameAndImages();
      
    request.setAttribute(StringConstant.REQ_ALLMANAGEMENTNAME,ListManagementAllName);

    RequestDispatcher dispatcher = request.getRequestDispatcher("book.jsp");
    
    if(skip != null){
    	Management management = listManagement.get(0);
    	int booknumber = management.getNumber();
    	request.setAttribute(StringConstant.REQ_MANAGEMENT_BOOKNUMBER, booknumber);
    	dispatcher = request.getRequestDispatcher("discuss");
    }
    
    dispatcher.forward(request, response);

    
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
