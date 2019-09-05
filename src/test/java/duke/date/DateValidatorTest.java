package duke.date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DateValidatorTest {

    @Test
    public void validateDate_validDatesDeadline_trueReturned() {
        try {
            DateValidator v = new DateValidator();
            assertEquals(true, v.validateDate("11/01/2000 1800", false));
            assertEquals(true, v.validateDate("15/03/2019 0815", false));
            assertEquals(true, v.validateDate("12/12/2024 0000", false));
            assertEquals(true, v.validateDate("11/11/2005 2000", false));
        } catch (InvalidDateDukeException e) {
            fail();
        }
    }

    @Test
    public void validateDate_invalidDateFormatDeadline_falseReturned() {
        try {
            DateValidator v = new DateValidator();
            assertEquals(false, v.validateDate("11/01/20001800", false));
            assertEquals(false, v.validateDate("15/03/2019 0815-0830", false));
            assertEquals(false, v.validateDate("12/12/2024 2500", false));
            assertEquals(false, v.validateDate("11/14/2005 2060", false));
            assertEquals(false, v.validateDate("11/11/2005 2060", false));
        } catch (InvalidDateDukeException e) {
            assertEquals("Invalid date format! Please ensure your date sticks to this format:\n"
                    + "    Deadlines : \"DD/MM/YYYY HHMM\"\n"
                    + "    Events : \"DD/MM/YYYY HHMM-HHMM\"", e.getMessage());
        }
    }

    @Test
    public void validateDate_validDatesEvent_trueReturned() {
        try {
            DateValidator v = new DateValidator();
            assertEquals(true, v.validateDate("11/01/2000 1800-2000", false));
            assertEquals(true, v.validateDate("15/03/2019 0815-0830", false));
            assertEquals(true, v.validateDate("12/12/2024 2300-0000", false));
            assertEquals(true, v.validateDate("11/11/2005 2000-2115", false));
            assertEquals(true, v.validateDate("11/10/1982 0000-0615", false));
        } catch (InvalidDateDukeException e) {
            fail();
        }
    }

}
