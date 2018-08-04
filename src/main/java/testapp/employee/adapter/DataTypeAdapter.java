package testapp.employee.adapter;

import javax.xml.bind.DatatypeConverter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Igor on 04.05.2018.
 */
public class DataTypeAdapter {
    public static Date parse(String xmlDateTime) {
        return DatatypeConverter.parseDateTime(xmlDateTime).getTime();
    }

    public static String printDate(Date dt) {
        if (dt == null) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(dt);

        return DatatypeConverter.printDate(c);
    }

    public static Date parseDateTime(String s) {
        if (s == null) {
            return null;
        }

        return DatatypeConverter.parseDateTime(s).getTime();
    }

    public static String printDateTime(Date dt) {
        if (dt == null) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(dt);

        return DatatypeConverter.printDateTime(c);
    }
}
