package com.student.data.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.student.data.service.BaseService;
import com.student.jdbc.JdbcUtils;

public class MoneyDao implements BaseService {
	private JdbcUtils jdbcUtils;

	public MoneyDao() {
		jdbcUtils = new JdbcUtils();
	}
	
	public boolean updateSHouRuType(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  typeincomemsg set typeIncomeName =?  where typeIncomeId = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	
	public boolean updateZhuChuType(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  typepaymsg set typePayName =?  where typePayId = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	
	
	public List<Map<String, Object>> listPayTongJiTu(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select COUNT(*) as totalNumber,lookmoneymsg.lookMoneyPayType from lookmoneymsg where lookMoneyUserId =?  GROUP BY lookMoneyPayType ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}
	
	
	public List<Map<String, Object>> listTongJiTu(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "SELECT sum(lookMoneyMoney) as totalMoney ,lookMoneyTypeName FROM lookmoneymsg   where lookMoneyUserId =?   GROUP BY  lookMoneyTypeName";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}
	
	
	
	public List<Map<String, Object>> listUserMoneyYear(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "SELECT DATE_FORMAT(lookMoneyTime,'%Y年') as timeMsg,sum(lookMoneyMoney)  money FROM lookmoneymsg where lookMoneyUserId =?   and typeMessage = ?  GROUP BY  timeMsg ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}
	
	
	public List<Map<String, Object>> listUserMoneyMonth(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "SELECT DATE_FORMAT(lookMoneyTime,'%Y年%m月') as timeMsg,sum(lookMoneyMoney)  money FROM lookmoneymsg  where lookMoneyUserId =?   and typeMessage = ?  GROUP BY  timeMsg";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}
	
	
	
	public List<Map<String, Object>> listUserMoneyWeek(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "SELECT DATE_FORMAT(lookMoneyTime,'%Y年%u周') as timeMsg,sum(lookMoneyMoney)  money FROM lookmoneymsg where lookMoneyUserId =? and typeMessage = ?   GROUP BY  timeMsg ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}
	
	
	
	public List<Map<String, Object>> listPhoneMoneyAll(String lookMoneyUserId,String typeMessage) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from lookMoneymsg where lookMoneyUserId = "+lookMoneyUserId+" and typeMessage = "+typeMessage;
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}
	
	
	
	public List<Map<String, Object>> listTongJiMessage(List<Object> params) {
		// TODO Auto-generated method stub

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from typeIncomemsg where typeIncomeUserId= ?  ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
//
			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				List<Object> paramsFocus = new ArrayList<Object>();
				paramsFocus.clear();
				paramsFocus.add(list.get(i).get("typeIncomeName") + "");
				mapResult.put("typeMoney", queryShop(paramsFocus));
				
				List<Object> paramsTotal = new ArrayList<Object>();
				paramsTotal.clear();
				paramsTotal.add("1");
				mapResult.put("totalMoney", queryTotalMoney(paramsTotal));
				listResult.add(mapResult);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}
	
	
	public List<Map<String, Object>> listBorrow(List<Object> params) {
		// TODO Auto-generated method stub

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from borrowtb where borrowUserId = ?  order by borrowId desc";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	
	public boolean addBorrow(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into borrowtb (borrowRealName,borrowMoney,borrowType,borrowUserId,borrowTime) values  (?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	
	
	public boolean addReply(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into replytb (replyAnswerId,replyMessage,replyUserId,replyUserName,replyCreatime,replyTeaMessage) values  (?,?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}


	
	public List<Map<String, Object>> queryReply(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from replytb where replyAnswerId=?";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public List<Map<String, Object>> listAnswerPhoneMessage() {
		// TODO Auto-generated method stub

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from answertb order by answerId desc";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				List<Object> paramsFocus = new ArrayList<Object>();
				paramsFocus.clear();
				paramsFocus.add(list.get(i).get("answerId") + "");
				List<Map<String, Object>> queryReply = queryReply(paramsFocus);
				mapResult.put("replyMsg", queryReply);

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	
	public boolean deleteBBS(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from answertb where answerId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean addAnswer(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into answertb (answerMessage,answerUserId,answerUserName,answerCreatime) values  (?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}
	

	

	public String queryShop(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "SELECT SUM(lookMoneyMoney) as typeMoney from lookmoneymsg where lookMoneyTypeName = ? ";
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return map.get("typeMoney").toString();
	}
	

	public List<Map<String, Object>> listPcInOrOutMoney(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from lookMoneymsg where typeMessage = ? and lookMoneyUserId = ? ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean deleteZhiChuTypeMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from typeincomemsg where typeIncomeId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean deletePayTypeMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from typepaymsg where typePayId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean updateMoney(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  lookMoneymsg set lookMoneyMoney =?,lookMoneyTypeName =?,lookMoneyTime =?,tipMessage = ?  where lookMoneyId = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean deleteMoney(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from lookMoneymsg where lookMoneyId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listPcUserMoney(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from lookMoneymsg where lookMoneyUserId =? order by STR_TO_DATE( `lookMoneyTime`,'%Y-%m-%d') asc ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public List<Map<String, Object>> listPcMoney() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from lookMoneymsg";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}
	
	public List<Map<String, Object>> listusermoney(String username) {
		// TODO Auto-generated method stub
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				String sql = "select * from lookMoneymsg where lookMoneyUserName=?";
				String params=username;
				try {
					jdbcUtils.getConnection();
					list = jdbcUtils.findMoreResult1(sql, params);
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					jdbcUtils.releaseConn();
				}
				return list;
			}


	public List<Map<String, Object>> listMoney(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// String sql = "select * from lookMoneymsg where typeMessage= ?";
		String sql = "select *,SUM(lookMoneyMoney) as totalMoney from lookmoneymsg  where lookMoneyUserId =? and typeMessage = ? group by monthMessage ";

		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean addMoney(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into lookMoneymsg (lookMoneyUserId,lookMoneyUserName,lookMoneyTypeId,"
					+ "lookMoneyTypeName,lookMoneyMoney,lookMoneyTime,typeMessage,monthMessage,tipMessage,lookMoneyPayType) values  (?,?,?,?,?,?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean addPay(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into typePaymsg (typePayName,typePayUserId) values  (?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listPay(List<Object> params) {
		

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;
		
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from typePaymsg where typePayUserId = ?  ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
			
			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				List<Object> paramsFocus = new ArrayList<Object>();
				paramsFocus.clear();
				paramsFocus.add(list.get(i).get("typePayName") + "");
				
				
				if(queryShop(paramsFocus)==""){
					mapResult.put("typeMoney", "0");
				}else{
					mapResult.put("typeMoney", queryShop(paramsFocus));
				}
				
				
//				mapResult.put("typeMoney", queryShop(paramsFocus));
				
//				List<Object> paramsTotal = new ArrayList<Object>();
//				paramsTotal.clear();
//				paramsTotal.add("2");
//				mapResult.put("totalMoney", queryTotalMoney(paramsTotal));
				listResult.add(mapResult);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}
	
public List<Map<String, Object>> zxlistPay(List<Object> params) {
		

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;
		
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "SELECT SUM(lookMoneyMoney) as money,lookMoneyTime  FROM lookmoneymsg where typeMessage = 2 and lookMoneyUserId = ? GROUP BY lookMoneyTime";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}
	
	

	public boolean addIncome(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into typeIncomemsg (typeIncomeName,typeIncomeUserId) values  (?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listIncome(List<Object> params) {
		// TODO Auto-generated method stub

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from typeIncomemsg where typeIncomeUserId= ?  ";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
//
			for (int i = 0; i < list.size(); i++) {
				mapResult = list.get(i);
				List<Object> paramsFocus = new ArrayList<Object>();
				paramsFocus.clear();
				paramsFocus.add(list.get(i).get("typeIncomeName") + "");
				if(queryShop(paramsFocus)==""){
					mapResult.put("typeMoney", "0");
				}else{
					mapResult.put("typeMoney", queryShop(paramsFocus));
				}
				
				listResult.add(mapResult);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public List<Map<String, Object>> zxIncome(List<Object> params) {
		// TODO Auto-generated method stub

		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapResult;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "SELECT SUM(lookMoneyMoney) as money,lookMoneyTime  FROM lookmoneymsg where typeMessage = 1 and lookMoneyUserId = ? GROUP BY lookMoneyTime";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
			

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	
	public String queryTotalMoney(List<Object> params) {
		Map<String, Object> map = null;
		String sql = "SELECT SUM(lookMoneyMoney) as typeMoney from lookmoneymsg where typeMessage = ? ";
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return map.get("typeMoney").toString();
	}
	

	

	public boolean UpdateMemorandum(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  memorandummsg set memorandumMessage =?,memorandumTime =? where memorandumId = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean deleteMemorandum(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from memorandummsg where memorandumId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean updateCourse(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "update  coursemsg set courseName =?,courseTime =?,courseAddress =?,courseClassId =?,courseClassName = ? where courseId = ?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean deleteCourse(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from coursemsg where courseId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public List<Map<String, Object>> listCourseMessage(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from coursemsg where courseUserId = ?";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public List<Map<String, Object>> listMemorandumMessage(List<Object> params) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from memorandummsg where memorandumUserId = ?";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public boolean addMemorandum(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into memorandummsg (memorandumMessage,memorandumTime,memorandumUserId) values  (?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public boolean addCourse(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "insert into coursemsg (courseName,courseTime,courseClassId,courseClassName,courseAddress,courseUserId) values  (?,?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public List<Map<String, Object>> listMessage() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from newsmessage";
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	@Override
	public boolean deleteMessage(List<Object> params) {
		boolean flag = false;
		try {
			String sql = "delete from newsmessage where newsId=?";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	@Override
	public boolean addMessage(List<Object> params) {
		// TODO Auto-generated method stub
		return false;
	}
}
