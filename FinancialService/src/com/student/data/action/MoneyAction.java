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

import com.student.data.dao.MoneyDao;

public class MoneyAction extends HttpServlet {

	private MoneyDao courseDao;

	public MoneyAction() {
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
		if (action_flag.equals("addCourse")) {
			addCourse(request, response);
		} else if (action_flag.equals("listCourseMessage")) {
			listCourseMessage(request, response);
		} else if (action_flag.equals("deleteCourse")) {
			deleteCourse(request, response);
		} else if (action_flag.equals("updateCourse")) {
			updateCourse(request, response);
		} else if (action_flag.equals("addMemorandum")) {
			addMemorandum(request, response);
		} else if (action_flag.equals("ListMemorandum")) {
			ListMemorandum(request, response);
		} else if (action_flag.equals("deleteMemorandum")) {
			deleteMemorandum(request, response);
		} else if (action_flag.equals("updateMemorandum")) {
			updateMemorandum(request, response);
		}

		else if (action_flag.equals("addIncome")) {
			addIncome(request, response);
		} else if (action_flag.equals("listIncome")) {
			listIncome(request, response);
		} else if (action_flag.equals("zxIncome")) {
			zxIncome(request, response);
		} else if (action_flag.equals("addPay")) {
			addPay(request, response);
		} else if (action_flag.equals("listPay")) {
			listPay(request, response);
		} else if (action_flag.equals("zxlistPay")) {
			zxlistPay(request, response);
		} else if (action_flag.equals("addMoney")) {
			addMoney(request, response);
		}

		else if (action_flag.equals("listPcPay")) {
			listPcPay(request, response);
		} else if (action_flag.equals("listPcIncome")) {
			listPcIncome(request, response);
		} else if (action_flag.equals("listMoney")) {
			listMoney(request, response);
		} else if (action_flag.equals("listPcMoney")) {
			listPcMoney(request, response);
		} else if (action_flag.equals("listAllMoney")) {
			listAllMoney(request, response);
		} else if (action_flag.equals("listAllUserMoney")) {
			listAllUserMoney(request, response);
		} else if (action_flag.equals("deleteMoney")) {
			deleteMoney(request, response);
		} else if (action_flag.equals("updateMoney")) {
			updateMoney(request, response);
		} else if (action_flag.equals("deleteTypeMessage")) {
			deleteTypeMessage(request, response);
		} else if (action_flag.equals("deleteTypeZhiChuMessage")) {
			deleteTypeZhiChuMessage(request, response);
		} else if (action_flag.equals("listPcInOrOutMoney")) {
			listPcInOrOutMoney(request, response);
		} else if (action_flag.equals("addTypeMessage")) {
			addTypeMessage(request, response);
		} else if (action_flag.equals("listTypeMoney")) {
			listTypeMoney(request, response);
		} else if (action_flag.equals("updateType")) {
			updateType(request, response);
		} else if (action_flag.equals("addAnswer")) {
			addAnswer(request, response);
		} else if (action_flag.equals("listAnswerPhoneMessage")) {
			listAnswerPhoneMessage(request, response);
		} else if (action_flag.equals("listBBS")) {
			listBBS(request, response);
		} else if (action_flag.equals("deleteBBS")) {
			deleteBBS(request, response);
		} else if (action_flag.equals("addReply")) {
			addReply(request, response);
		} else if (action_flag.equals("addBorrow")) {
			addBorrow(request, response);
		} else if (action_flag.equals("listBorrow")) {
			listBorrow(request, response);
		} else if (action_flag.equals("listPhoneMoneyAll")) {
			listPhoneMoneyAll(request, response);
		} else if (action_flag.equals("listAllTongJiMoney")) {
			listAllTongJiMoney(request, response);
		} else if (action_flag.equals("listTongJiTu")) {
			listTongJiTu(request, response);
		} else if (action_flag.equals("listPayTongJiTu")) {
			listPayTongJiTu(request, response);
		}
	}

	public void init() throws ServletException {

		courseDao = new MoneyDao();
	}

	private void listPayTongJiTu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lookMoneyUserId = request.getParameter("lookMoneyUserId");
		List<Object> params = new ArrayList<Object>();
		params.add(lookMoneyUserId);
		List<Map<String, Object>> list = courseDao.listPayTongJiTu(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listTongJiTu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lookMoneyUserId = request.getParameter("lookMoneyUserId");
		List<Object> params = new ArrayList<Object>();
		params.add(lookMoneyUserId);
		List<Map<String, Object>> list = courseDao.listTongJiTu(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listAllTongJiMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String lookMoneyUserId = request.getParameter("lookMoneyUserId");
		String typeMessage = request.getParameter("typeMessage");
		String queryTime = request.getParameter("queryTime");

		System.out.println(lookMoneyUserId);
		System.out.println(typeMessage);
		System.out.println(queryTime);

		List<Object> params = new ArrayList<Object>();
		params.add(lookMoneyUserId + "");
		params.add(typeMessage + "");
		// params.add(typeMessage + "");

		List<Map<String, Object>> list = null;
		if (queryTime.equals("1")) {
			list = courseDao.listUserMoneyWeek(params);
		} else if (queryTime.equals("2")) {
			list = courseDao.listUserMoneyMonth(params);
		} else if (queryTime.equals("3")) {
			list = courseDao.listUserMoneyYear(params);
		}

		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listPhoneMoneyAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lookMoneyUserId = request.getParameter("lookMoneyUserId");
		String typeMessage = request.getParameter("typeMessage");
		List<Object> params = new ArrayList<Object>();
		params.add(lookMoneyUserId);
		params.add(typeMessage);
		List<Map<String, Object>> list = courseDao.listPhoneMoneyAll(lookMoneyUserId, typeMessage);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listBorrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String borrowUserId = request.getParameter("borrowUserId");
		List<Object> params = new ArrayList<Object>();
		params.add(borrowUserId);
		List<Map<String, Object>> list = courseDao.listBorrow(params);
		// 生成json字符串
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void addBorrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String borrowRealName = request.getParameter("borrowRealName");
		String borrowMoney = request.getParameter("borrowMoney");
		String borrowType = request.getParameter("borrowType");
		String borrowUserId = request.getParameter("borrowUserId");
		String borrowTime = request.getParameter("borrowTime");

		List<Object> params = new ArrayList<Object>();
		params.add(borrowRealName);
		params.add(borrowMoney);
		params.add(borrowType);
		params.add(borrowUserId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(borrowTime);

		boolean flag = courseDao.addBorrow(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加成功");
			jsonmsg.put("repCode", "666");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加失败");
			jsonmsg.put("repCode", "111");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		}

	}

	private void addReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String replyAnswerId = request.getParameter("replyAnswerId");
		String replyMessage = request.getParameter("replyMessage");
		String replyUserId = request.getParameter("replyUserId");
		String replyUserName = request.getParameter("replyUserName");
		List<Object> params = new ArrayList<Object>();
		params.add(replyAnswerId);
		params.add(replyMessage);
		params.add(replyUserId);
		params.add(replyUserName);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));
		params.add("-1");
		boolean flag = courseDao.addReply(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "评论成功");
			jsonmsg.put("repCode", "666");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加失败");
			jsonmsg.put("repCode", "111");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		}

	}

	private void deleteBBS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String answerId = request.getParameter("answerId");
		List<Object> params = new ArrayList<Object>();
		params.add(answerId);
		boolean flag = courseDao.deleteBBS(params);

		if (flag) {
			listBBS(request, response);

		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "删除失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listBBS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 已经进行分页之后的数据集合
		List<Map<String, Object>> list = courseDao.listAnswerPhoneMessage();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../bbsMessage.jsp").forward(request, response);
	}

	private void listAnswerPhoneMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		List<Map<String, Object>> list = courseDao.listAnswerPhoneMessage();
		// 生成json字符串
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void addAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		String answerMessage = request.getParameter("answerMessage");
		String answerUserId = request.getParameter("answerUserId");
		String answerUserName = request.getParameter("answerUserName");
		List<Object> params = new ArrayList<Object>();
		params.add(answerMessage);
		params.add(answerUserId);
		params.add(answerUserName);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		params.add(df.format(new Date()));
		boolean flag = courseDao.addAnswer(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加成功");
			jsonmsg.put("repCode", "666");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加失败");
			jsonmsg.put("repCode", "111");
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
			System.out.println(jsonmsg);
		}

	}

	private void updateType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String typeName = request.getParameter("typeName");
		String typeFlag = request.getParameter("typeFlag");
		String typeId = request.getParameter("typeId");

		List<Object> params = new ArrayList<Object>();
		params.add(typeName);
		params.add(typeId);
		
		System.out.println(typeName);
		System.out.println(typeFlag);
		System.out.println(typeId);
		boolean flag;
		if (typeFlag.equals("1")) {
			flag = courseDao.updateSHouRuType(params);
		} else {
			flag = courseDao.updateZhuChuType(params);
		}

		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "修改成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "修改失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listTypeMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String typeMessage = request.getParameter("typeMessage");
		String lookMoneyUserId = request.getParameter("lookMoneyUserId");

		List<Object> params = new ArrayList<Object>();
		params.add(typeMessage);
		params.add(lookMoneyUserId);
		List<Map<String, Object>> list = courseDao.listPcInOrOutMoney(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void addTypeMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String typeIncomeUserId = request.getParameter("typeIncomeUserId");
		String typeIncomeName = request.getParameter("typeIncomeName");
		List<Object> params = new ArrayList<Object>();
		params.add(typeIncomeName);
		params.add(typeIncomeUserId);
		boolean flag = courseDao.addIncome(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "提交失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listPcInOrOutMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typeMessage = request.getParameter("typeMessage");
		String lookMoneyUserId = request.getParameter("lookMoneyUserId");

		List<Object> params = new ArrayList<Object>();
		params.add(typeMessage);
		params.add(lookMoneyUserId);
		List<Map<String, Object>> list = courseDao.listPcInOrOutMoney(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void deleteTypeZhiChuMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String typeIncomeId = request.getParameter("typeIncomeId");
		List<Object> params = new ArrayList<Object>();
		params.add(typeIncomeId);
		System.out.println("-----" + typeIncomeId);
		boolean flag = courseDao.deleteZhiChuTypeMessage(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "修改失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void deleteTypeMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String typePayId = request.getParameter("typePayId");
		List<Object> params = new ArrayList<Object>();
		params.add(typePayId);

		System.out.println("===" + typePayId);
		boolean flag = courseDao.deletePayTypeMessage(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "修改失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void updateMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String lookMoneyMoney = request.getParameter("lookMoneyMoney");
		String lookMoneyTypeName = request.getParameter("lookMoneyTypeName");
		String lookMoneyTime = request.getParameter("lookMoneyTime");
		String tipMessage = request.getParameter("tipMessage");
		String lookMoneyId = request.getParameter("lookMoneyId");

		List<Object> params = new ArrayList<Object>();
		params.add(lookMoneyMoney);
		params.add(lookMoneyTypeName);
		params.add(lookMoneyTime);
		params.add(tipMessage);
		params.add(lookMoneyId);

		boolean flag = courseDao.updateMoney(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "修改成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "修改失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void deleteMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String lookMoneyId = request.getParameter("lookMoneyId");
		List<Object> params = new ArrayList<Object>();
		params.add(lookMoneyId);
		boolean flag = courseDao.deleteMoney(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "删除成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "删除失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listAllUserMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String lookMoneyUserId = request.getParameter("lookMoneyUserId");
		List<Object> params = new ArrayList<Object>();
		params.add(lookMoneyUserId + "");

		List<Map<String, Object>> list = courseDao.listPcUserMoney(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listAllMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Map<String, Object>> list = courseDao.listPcMoney();
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listPcMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Map<String, Object>> list = courseDao.listPcMoney();
		request.setAttribute("listMessage", list);
		request.getRequestDispatcher("../moneyMessage.jsp").forward(request, response);

	}

//	private void listPcMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("11122");
//		Map<String, List<Map<String, Object>>> dataMap = courseDao.listPcMoney1();
//		request.setAttribute("dataMap", dataMap);
//		request.getRequestDispatcher("../moneyMessage.jsp").forward(request, response);
//
//	}
//	
	private void listMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lookMoneyUserId = request.getParameter("lookMoneyUserId");
		String typeMessage = request.getParameter("typeMessage");
		List<Object> params = new ArrayList<Object>();
		params.add(lookMoneyUserId + "");
		params.add(typeMessage + "");
		System.out.println(typeMessage);
		List<Map<String, Object>> list = courseDao.listMoney(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listPcIncome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		// List<Map<String, Object>> list = courseDao.listIncome();
		// request.setAttribute("listMessage", list);
		// request.getRequestDispatcher("../incomeMessage.jsp").forward(request,
		// response);

	}

	private void listPcPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();
		// List<Map<String, Object>> list = courseDao.listPay();
		// request.setAttribute("listMessage", list);
		// request.getRequestDispatcher("../payMessage.jsp").forward(request,
		// response);

	}

	private void addMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String lookMoneyUserId = request.getParameter("lookMoneyUserId");
		String lookMoneyUserName = request.getParameter("lookMoneyUserName");
		String lookMoneyTypeId = request.getParameter("lookMoneyTypeId");
		String lookMoneyTypeName = request.getParameter("lookMoneyTypeName");
		String lookMoneyMoney = request.getParameter("lookMoneyMoney");
		String lookMoneyTime = request.getParameter("lookMoneyTime");
		String typeMessage = request.getParameter("typeMessage");
		String monthMessage = request.getParameter("monthMessage");
		String tipMessage = request.getParameter("tipMessage");
		String lookMoneyPayType = request.getParameter("lookMoneyPayType");

		List<Object> params = new ArrayList<Object>();
		params.add(lookMoneyUserId + "");
		params.add(lookMoneyUserName);
		params.add(lookMoneyTypeId + "");
		params.add(lookMoneyTypeName);
		params.add(lookMoneyMoney);
		params.add(lookMoneyTime);
		params.add(typeMessage);
		params.add(monthMessage);
		params.add(tipMessage);
		params.add(lookMoneyPayType);
		System.out.println(lookMoneyUserId);
		System.out.println(lookMoneyUserName);
		System.out.println(lookMoneyTypeId);
		System.out.println(lookMoneyTypeName);
		System.out.println(lookMoneyMoney);
		System.out.println(lookMoneyTime);

		List<Object> paramsCheckTotal = new ArrayList<Object>();
		paramsCheckTotal.add(lookMoneyTypeName + "");
		courseDao.queryShop(paramsCheckTotal);

		boolean flag = courseDao.addMoney(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "提交失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listIncome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		List<Map<String, Object>> list = courseDao.listIncome(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}
	
	private void zxIncome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		List<Map<String, Object>> list = courseDao.zxIncome(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void listPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		List<Map<String, Object>> list = courseDao.listPay(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}
	
	private void zxlistPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		List<Map<String, Object>> list = courseDao.zxlistPay(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}


	private void addPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String typePayName = request.getParameter("typePayName");
		String typePayUserId = request.getParameter("typePayUserId");
		List<Object> params = new ArrayList<Object>();
		params.add(typePayName);
		params.add(typePayUserId);
		boolean flag = courseDao.addPay(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "提交失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void addIncome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String typeIncomeName = request.getParameter("typeIncomeName");
		String typeIncomeUserId = request.getParameter("typeIncomeUserId");
		List<Object> params = new ArrayList<Object>();
		params.add(typeIncomeName);
		params.add(typeIncomeUserId);
		boolean flag = courseDao.addIncome(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "提交失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void updateMemorandum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String memorandumMessage = request.getParameter("memorandumMessage");
		String memorandumTime = request.getParameter("memorandumTime");
		String memorandumId = request.getParameter("memorandumId");

		List<Object> params = new ArrayList<Object>();
		params.add(memorandumMessage);
		params.add(memorandumTime);
		params.add(memorandumId);

		boolean flag = courseDao.UpdateMemorandum(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "修改成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "修改失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void deleteMemorandum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String memorandumId = request.getParameter("memorandumId");
		List<Object> params = new ArrayList<Object>();
		params.add(memorandumId);
		boolean flag = courseDao.deleteMemorandum(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "删除成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "删除失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void ListMemorandum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memorandumUserId = request.getParameter("memorandumUserId");
		System.out.println(memorandumUserId);
		List<Object> params = new ArrayList<Object>();
		params.add(memorandumUserId);
		List<Map<String, Object>> list = courseDao.listMemorandumMessage(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void addMemorandum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String memorandumMessage = request.getParameter("memorandumMessage");
		String memorandumTime = request.getParameter("memorandumTime");
		String memorandumUserId = request.getParameter("memorandumUserId");
		List<Object> params = new ArrayList<Object>();
		params.add(memorandumMessage);
		params.add(memorandumTime);
		params.add(memorandumUserId);
		boolean flag = courseDao.addMemorandum(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "提交失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void updateCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String courseName = request.getParameter("courseName");
		String courseTime = request.getParameter("courseTime");
		String courseAddress = request.getParameter("courseAddress");
		String courseClassId = request.getParameter("courseClassId");
		String courseClassName = request.getParameter("courseClassName");
		String courseId = request.getParameter("courseId");

		List<Object> params = new ArrayList<Object>();
		params.add(courseName);
		params.add(courseTime);
		params.add(courseAddress);
		params.add(courseClassId);
		params.add(courseClassName);
		params.add(courseId);

		boolean flag = courseDao.updateCourse(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "修改成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "修改失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String courseId = request.getParameter("courseId");
		List<Object> params = new ArrayList<Object>();
		params.add(courseId);
		boolean flag = courseDao.deleteCourse(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "删除成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "删除失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}

	private void listCourseMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseUserId = request.getParameter("courseUserId");

		System.out.println(courseUserId);
		List<Object> params = new ArrayList<Object>();
		params.add(courseUserId);
		List<Map<String, Object>> list = courseDao.listCourseMessage(params);
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repMsg", "请求成功");
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list);
		System.out.println(jsonmsg);
		response.getWriter().print(jsonmsg);// 将路径返回给客户端

	}

	private void addCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath();

		String courseName = request.getParameter("courseName");
		String courseTime = request.getParameter("courseTime");
		String courseAddress = request.getParameter("courseAddress");
		String courseUserId = request.getParameter("courseUserId");

		String courseClassId = request.getParameter("courseClassId");
		String courseClassName = request.getParameter("courseClassName");

		List<Object> params = new ArrayList<Object>();
		params.add(courseName);
		params.add(courseTime);

		params.add(courseClassId);
		params.add(courseClassName);

		params.add(courseAddress);
		params.add(courseUserId);

		boolean flag = courseDao.addCourse(params);
		if (flag) {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "添加成功");
			jsonmsg.put("repCode", "666");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		} else {
			JSONObject jsonmsg = new JSONObject();
			jsonmsg.put("repMsg", "提交失败");
			jsonmsg.put("repCode", "111");
			System.out.println(jsonmsg);
			response.getWriter().print(jsonmsg);// 将路径返回给客户端
		}

	}
}
