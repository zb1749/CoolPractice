package problem;

/**
 * Created by Kevin on 2016/12/8.
 */
public class a {
    public static void main(String[] args) {
        String monthlyCloseDate = "2016-07-22 00:00:00";
        System.out.println(convertMonthlyCloseDate(monthlyCloseDate));
    }

    public static String convertMonthlyCloseDate(String fromMonthlyCloseDate) {
        String monthlyCloseDate = fromMonthlyCloseDate;
        if (monthlyCloseDate != null) {
            if (monthlyCloseDate.length() == 19) {
                monthlyCloseDate = monthlyCloseDate.substring(0, 10);
            } else if (monthlyCloseDate.length() == 7) {
                monthlyCloseDate = monthlyCloseDate + "-01";
            }
        }
        return monthlyCloseDate;
    }
}
