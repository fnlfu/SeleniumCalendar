import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class stringToData {
    private String input;
    private int day;
    private int month;
    private int year;

    public stringToData(String input) {
        this.input = input;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");


        if (input.contains("/")) {
            simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        }

        try {

            Date date;
            if (input.contains("today")) {
                date = Calendar.getInstance().getTime();
            }else {
                date = simpleDateFormat.parse(input);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            this.day = calendar.get(Calendar.DAY_OF_MONTH);
            this.month = calendar.get(Calendar.MONTH) + 1;
            this.year = calendar.get(Calendar.YEAR);
        } catch (Exception e) {
        }
    }

    public String expected() {
        return Integer.toString(month) + '/' + Integer.toString(day) + '/' + Integer.toString(year);
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
