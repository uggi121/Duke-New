package duke.date;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator {

    public boolean validateDate(String date, boolean hasEndTime) throws InvalidDateDukeException {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(date);
        ArrayList<String> dateParams = new ArrayList<>();
        while (m.find()) {
            dateParams.add(m.group());
        }
        if (hasEndTime) {
            if (dateParams.size() != 5) {
                return false;
            }
        } else {
            if (dateParams.size() != 4) {
                return false;
            }
        }
        try {
            Month month = Month.of(Integer.parseInt(dateParams.get(1)));
            return checkValidity(dateParams, hasEndTime, month);
        } catch (DateTimeException e) {
            throw new InvalidDateDukeException("Invalid date format! Please ensure your date sticks to this format:\n"
                    + "    Deadlines : \"DD/MM/YYYY HHMM\"\n"
                    + "    Events : \"DD/MM/YYYY HHMM-HHMM\"");
        }
    }

    private boolean checkValidity(ArrayList<String> dateParams, boolean hasEndTime, Month month)
            throws InvalidDateDukeException {
        try {
            int day = Integer.parseInt(dateParams.get(0));
            int year = Integer.parseInt(dateParams.get(2));
            String start = dateParams.get(3);
            int startHours = Integer.parseInt(start.substring(0, 2));
            int startMinutes = Integer.parseInt(start.substring(2));
            if (hasEndTime) {
                String end = dateParams.get(4);
                int endHours = Integer.parseInt(end.substring(0, 2));
                int endMinutes = Integer.parseInt(end.substring(2));
                return checkStartEnd(day, month, year, startHours, startMinutes, endHours, endMinutes);
            } else {
                return checkStart(day, month, year, startHours, startMinutes);
            }
        } catch (NumberFormatException e) {
            throw new InvalidDateDukeException("Invalid date format! Please ensure your date sticks to this format:\n"
                    + "    Deadlines : \"DD/MM/YYYY HHMM\"\n"
                    + "    Events : \"DD/MM/YYYY HHMM-HHMM\"");
        }
    }

    private boolean checkStart(int day, Month month, int year, int startHours, int startMinutes) {
        try {
            LocalDateTime dateTime = LocalDateTime.of(year, month, day, startHours, startMinutes);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    private boolean checkStartEnd(int day, Month month, int year, int startHours, int startMinutes,
                                  int endHours, int endMinutes) {
        try {
            LocalDateTime dateTimeStart = LocalDateTime.of(year, month, day, startHours, startMinutes);
            LocalDateTime dateTimeEnd = LocalDateTime.of(year, month, day, endHours, endMinutes);
            if (dateTimeEnd.isAfter(dateTimeStart)) {
                return true;
            } else {
                return false;
            }
        } catch (DateTimeException e) {
            return false;
        }
    }
}
