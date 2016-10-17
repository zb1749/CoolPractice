package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CompareDate {
    public static void main(String[] args) {
        String date1 = "2016-03-06 00:00";
        String date2 = "2016-03-13 00:00";
        String format = "yyyyMMdd HH:mm";

        String another = DateUtil.getAnotherDay(-7, format);
        System.out.println(another);

        System.out.println(DateUtil.getCurrentTime());
        System.out.println(DateUtil.getCurrentTime(DateUtil.FORMAT_YYYY_MM_DD));

        String start = "20151101000000";
        String end = "20160817023555";


        CompareDate cd = new CompareDate();
        System.out.println(cd.isValidDateFormat(start));
        System.out.println(cd.isValidDateFormat(end));
        System.out.println(cd.isValidDateScope(start, end));

    }

    /**
     * 校验操作时间
     *
     * @param str
     * @return
     */
    private boolean isValidDateFormat(String str) {

        boolean convertSuccess = true;
        if(str==null||str.length()!=14){
            return false;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");// 24小时制
        try {
            // 设置lenient为false，严格验证
            format.setLenient(false);
            System.out.println(format.parse(str));

        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 验证操作时间范围是否合法
     *
     * @param startTime
     * @param endTime
     * @return
     */
    private boolean isValidDateScope(String startTime, String endTime) {
        String format = "yyyyMMddHHmmss";
        try {
            Date beginDate = DateUtil.parseDate(startTime, format);
            Date endDate = DateUtil.parseDate(endTime, format);
            Calendar calendarStart = Calendar.getInstance();
            calendarStart.setTime(beginDate);
            Calendar calendarEnd = Calendar.getInstance();
            calendarEnd.setTime(endDate);
            Calendar calendarNow = Calendar.getInstance();
            calendarNow.setTime(new Date());
            System.out.println("now: "+calendarNow.getTime());
            System.out.println("start before now: "+calendarStart.before(calendarNow));
            System.out.println("start before now end: "+calendarStart.before(calendarEnd));

            if (calendarStart.before(calendarNow)
                    && calendarStart.before(calendarEnd)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
