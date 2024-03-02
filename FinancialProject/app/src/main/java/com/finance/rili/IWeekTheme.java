package com.finance.rili;

/**
 * Created by Administrator on 2016/7/31.
 */
public interface IWeekTheme {
    /**
     * 顶部线颜�?
     * @return 16进制颜色�? hex color
     */
    public int colorTopLinen();

    /**
     * 底部颜色
     * @return 16进制颜色�? hex color
     */
    public int colorBottomLine();

    /**
     * 工作日颜�?
     * @return 16进制颜色�? hex color
     */
    public int colorWeekday();

    /**
     * 周末颜色
     * @return 16进制颜色�? hex color
     */
    public int colorWeekend();

    /**
     * 星期的整个背景色
     * @return
     */
    public int colorWeekView();
    /**
     * 线的宽度
     * @return px
     */
    public int sizeLine();

    /**
     * 字体大小
     * @return px
     */
    public int sizeText();
}
