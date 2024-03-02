package com.student.data.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.student.data.dao.NewsDao;

public class NewsAction extends HttpServlet {

	private NewsDao newsDao;

	public NewsAction() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getContextPath();
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String action_flag = request.getParameter("action_flag");
		if (action_flag.equals("addMessage")) {
			addMessage(request, response);
		} else if (action_flag.equals("listMessage")) {
			listMessage(request, response);
		} else if (action_flag.equals("listPhoneMessage")) {
			listPhoneMessage(request, response);
		} else if (action_flag.equals("updateStudent")) {
			updateStudent(request, response);
		} else if (action_flag.equals("deleteMessage")) {
			deleteMessage(request, response);
		} else if (action_flag.equals("listMessageChoice")) {
			listMessageChoice(request, response);
		} else if (action_flag.equals("loginMessage")) {
			loginMessage(request, response);
		} else if (action_flag.equals("updatePswd")) {
			updatePswd(request, response);
		} else if (action_flag.equals("CheckStudent")) {
			CheckStudent(request, response);
		} else if (action_flag.equals("addType")) {
			addType(request, response);
		} else if (action_flag.equals("listMessageType")) {
			listMessageType(request, response);
		} else if (action_flag.equals("listMessageTypeChoice")) {
			listMessageTypeChoice(request, response);
		} else if (action_flag.equals("listMessageTypePhone")) {
			listMessageTypePhone(request, response);
		}else if (action_flag.equals("deleteType")) {
			deleteType(request, response);
		} else if (action_flag.equals("queryMessage")) {
			queryMessage(request, response);
		} else if (action_flag.equals("UpdateMessage")) {
			UpdateMessage(request, response);
		} 

	}

	public void init() throws ServletException {

		newsDao = new NewsDao();
	}
	
	private void UpdateMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String typeName = request.getParameter("typeName");
		String typeId = request.getParameter("typeId");
		System.out.println(typeName);
		System.out.println(typeId);
		
		
		List<Object> params = new ArrayList<Object>();
		params.add(typeName);
		params.add(typeId);
		boolean flag = newsDao.updatePcType(params);
		if (flag) {
			listMessageType(request, response);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			// response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}
	private void queryMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String typeId = request.getParameter("typeId");
		List<Object> params = new ArrayList<Object>();
		params.add(typeId);
		Map<String, Object> list = newsDao.queryType(params);
		request.setAttribute("mapmsg", list);
		request.getRequestDispatcher("../formUpdateType.jsp").forward(request, response);
	}
		
	private void deleteType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String typeId = request.getParameter("typeId");
		List<Object> params = new ArrayList<Object>();
		params.add(typeId);
		boolean flag = newsDao.deleteTypeMessage(params);
		if (flag) {
			listMessageType(request, response);
		}

	}

	private void listMessageTypePhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Map<String, Object>> list = newsDao.listMessageType();
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}
	

	private void listMessageTypeChoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = newsDao.listMessageType();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../formNews.jsp").forward(request, response);

	}

	private void listMessageType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = newsDao.listMessageType();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../typeMessage.jsp").forward(request, response);

	}

	private void addType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String typeName = request.getParameter("typeName");
		SimpleDateFormat dfNo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		List<Object> params = new ArrayList<Object>();
		params.add(typeName);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));
		boolean flag = newsDao.addType(params);
		if (flag) {
			listMessageType(request, response);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "提交失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void CheckStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String stuNo = request.getParameter("stuNo");

		List<Object> params_check_login = new ArrayList<Object>();
		params_check_login.add(stuNo);
		boolean flag = newsDao.CheckStudent(params_check_login);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "验证成功");
			jsonmsg.put("repCode", "666");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "验证失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}
	}

	private void updatePswd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String stuId = request.getParameter("stuId");
		String stuPswd = request.getParameter("stuPswd");
		List<Object> params = new ArrayList<Object>();
		params.add(stuPswd);
		params.add(stuId);
		System.out.println(stuPswd);
		boolean flag = newsDao.updatePswd(params);

		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			// response.sendRedirect(path +
			// "/servlet/NoticeAction?action_flag=listMessage");

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			// response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void loginMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String stuNo = request.getParameter("stuNo");
		String stuPswd = request.getParameter("stuPswd");

		List<Object> params_check_login = new ArrayList<Object>();
		params_check_login.add(stuNo);
		params_check_login.add(stuPswd);
		boolean flag = newsDao.Login(params_check_login);
		if (flag) {
			Map<String, Object> map = newsDao.queryLoginStudent(params_check_login);
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "登录成功");
			jsonmsg.put("repCode", "666");
			jsonmsg.put("data", map);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "用户名或密码不正确");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}
	}

	private void deleteMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String newsId = request.getParameter("newsId");
		List<Object> params = new ArrayList<Object>();
		params.add(newsId);
		boolean flag = newsDao.deleteMessage(params);
		if (flag) {
			listMessage(request, response);
		}

	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String stuNo = request.getParameter("stuNo");
		String sStuName = request.getParameter("sStuName");
		List<Object> params = new ArrayList<Object>();
		params.add(sStuName);
		params.add(stuNo);
		System.out.println(stuNo);
		System.out.println(sStuName);
		boolean flag = newsDao.updateStudent(params);

		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			// response.sendRedirect(path +
			// "/servlet/NoticeAction?action_flag=listMessage");

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "更新失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			// response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listPhoneMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Map<String, Object>> list = newsDao.listPhoneMessage();
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listMessageChoice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = newsDao.listMessage();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../formShop.jsp").forward(request, response);

	}

	private void listMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		List<Map<String, Object>> list = newsDao.listMessage();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../newsMessage.jsp").forward(request, response);

	}

	private void addMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String newsTitle = request.getParameter("newsTitle");
		String newsContent = request.getParameter("newsContent");


		List<Object> params = new ArrayList<Object>();
		params.add(newsTitle);
		params.add(newsContent);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));
		
		
		boolean flag = newsDao.addMessage(params);
		if (flag) {
			listMessage(request, response);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "提交失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

}
