package com.finance.app;

import java.util.Map;

import android.app.Application;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import com.finance.LoginActivity;
import com.finance.PswdMessageActivity;
import com.finance.UpdatePswdActivity;
import com.finance.UserActivity;
import com.finance.db.DBHelper;
import com.finance.db.MemberUserUtils;
import com.finance.model.UserModel;

/**
 * 
 * @author 自定义Application
 * 
 */
public class FinanceApplication extends Application {

	// 用于存放倒计时时间
	public static Map<String, Long> map;
	// 当前用户的Sec账户信息实体
	public UserModel mUser = null;
	private static FinanceApplication INSTANCE = null;
	public static SQLiteDatabase db;
	public static boolean messageFlag = false;

	@Override
	public void onCreate() {
		super.onCreate();
		INSTANCE = this;

	
		DBHelper dbHelper = new DBHelper(getApplicationContext(), DBHelper.DB_NAME);
		// 在执行了getWritableDatabase的时候才会创建数据库
		db = dbHelper.getWritableDatabase();
	}

	public static synchronized FinanceApplication getInstance() {
		return INSTANCE;
	}

	public void setUser(UserModel user) {
		this.mUser = user;
	}

	public UserModel getUser() {
		return this.mUser;
	}

}
