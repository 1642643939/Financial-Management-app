package com.finance.rili;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.R;


/**
 * Created by Administrator on 2016/7/31.
 */
public class GridCalendarView extends LinearLayout implements View.OnClickListener {
    private WeekView weekView;
    private CircleMonthView gridMonthView;
    private TextView textViewYear,textViewMonth;
    public GridCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        LayoutParams llParams =
                new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(context).inflate(R.layout.display_grid_date,null);
        weekView = new WeekView(context,null);
        gridMonthView = new CircleMonthView(context,null);
        addView(view,llParams);
        addView(weekView,llParams);
        addView(gridMonthView,llParams);

        view.findViewById(R.id.left).setOnClickListener(this);
        view.findViewById(R.id.right).setOnClickListener(this);
        textViewYear = (TextView) view.findViewById(R.id.year);
//        textViewMonth = (TextView) view.findViewById(R.id.month);
        gridMonthView.setMonthLisener(new MonthView.IMonthLisener() {
            @Override
            public void setTextMonth() {
                textViewYear.setText(gridMonthView.getSelYear()+"年"+(gridMonthView.getSelMonth() + 1)+"月");
//                textViewMonth.setText((gridMonthView.getSelMonth() + 1)+"月");
            }
        });
    }

    /**
     * 设置日历点击事件
     * @param dateClick
     */
    public void setDateClick(MonthView.IDateClick dateClick){
        gridMonthView.setDateClick(dateClick);
    }

    /**
     * 设置星期的形�?
     * @param weekString
     */
    public void setWeekString(String[] weekString){
        weekView.setWeekString(weekString);
    }


    public void setDayTheme(IDayTheme theme){
        gridMonthView.setTheme(theme);
    }

    public void setWeekTheme(IWeekTheme weekTheme){
        weekView.setWeekTheme(weekTheme);
    }

    
	/**
	 * 设置显示当前日期的控件
	 * @param tv_date
	 * 		显示日期
	 * @param tv_week
	 * 		显示周
	 */
	public void setTextView(int tv_date,int tv_week){
        textViewYear.setText(tv_date+"年"+tv_week+"月");
        
	}
	
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.left){
            gridMonthView.onLeftClick();
        }else{
            gridMonthView.onRightClick();
        }
    }
}