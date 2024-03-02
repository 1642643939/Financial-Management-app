package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Test;

import com.student.data.dao.MoneyDao;
import com.student.data.dao.RegisterDao;

public class RegTest {

	private MoneyDao imageDao;

	@Test
	public void Reg() {
		imageDao = new MoneyDao();
		 List<Object> params = new ArrayList<Object>();
		 params.add("32");
		List<Map<String, Object>> list_msg = imageDao.listIncome(params);

		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list_msg);
		System.out.println(jsonmsg);
	}
}
