package date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseDateTest {
    public static void main(String[] args) {
        String dateOrg = "2018-07-16 10:30:00.5";
        String dateF = format(dateOrg,"yyyy-MM-dd HH:mm:ss");
        System.out.println(dateOrg);
        System.out.println(dateF);
        System.out.println(dateF.substring(0,19));
        System.out.println(dateOrg.substring(0,19));
    }

    public static String format(String sourceDateTime, String toFormat)
    {
        Date date = null;
        String toDateTime = "";
        try
        {
            if (sourceDateTime != null)
            {
                date = new Date(sourceDateTime);
                SimpleDateFormat sdf2 = new SimpleDateFormat(toFormat);
                toDateTime = sdf2.format(date);
            }
        }
        catch (Exception e)
        {
            return sourceDateTime;
        }
        return toDateTime;
    }
}
