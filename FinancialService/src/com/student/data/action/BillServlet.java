package com.student.data.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.data.dao.MoneyDao;

public class BillServlet extends HttpServlet {

  private MoneyDao billDao = new MoneyDao();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  System.out.println("1133");
    String username = request.getParameter("username");
    List<Map<String, Object>> list = billDao.listusermoney(username);
    request.setAttribute("bill", list);
    request.getRequestDispatcher("../moneyMessage2.jsp").forward(request, response);
  }

}