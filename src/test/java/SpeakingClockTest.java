import com.nt.InvalidTimeException;
import com.nt.SpeakingTimeClock;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SpeakingClockTest {

    private SpeakingTimeClock speakingClock;

    @Before
    public void setup() {
        this.speakingClock = new SpeakingTimeClock();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void convertReturnsAmTime() {
        Object convert = this.speakingClock.convert("08:34");

        assertThat(convert, is(
                allOf(notNullValue(), instanceOf(String.class), equalTo("It's eight thirty four"))));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void convertReturnsMidnight() {
        Object convert = this.speakingClock.convert("00:00");

        assertThat(convert, is(
                allOf(notNullValue(), instanceOf(String.class), equalTo("It's Midnight"))));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void convertReturnsMidday() {
        Object convert = this.speakingClock.convert("12:00");

        assertThat(convert, is(
                allOf(notNullValue(), instanceOf(String.class), equalTo("It's Midday"))));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void convertReturnsJustBeforeMidnight() {
        Object convert = this.speakingClock.convert("23:59");

        assertThat(convert, is(
                allOf(notNullValue(), instanceOf(String.class), equalTo("It's twenty three fifty nine"))));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void convertReturnsJustAfterMidnight() {
        Object convert = this.speakingClock.convert("00:01");

        assertThat(convert, is(
                allOf(notNullValue(), instanceOf(String.class), equalTo("It's zero one"))));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void convertReturnsJustBeforeMidday() {
        Object convert = this.speakingClock.convert("11:59");

        assertThat(convert, is(
                allOf(notNullValue(), instanceOf(String.class), equalTo("It's eleven fifty nine"))));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void convertReturnsJustAfterMidday() {
        Object convert = this.speakingClock.convert("12:01");

        assertThat(convert, is(
                allOf(notNullValue(), instanceOf(String.class), equalTo("It's twelve one"))));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void convertReturnsOnePm() {
        Object convert = this.speakingClock.convert("13:00");

        assertThat(convert, is(
                allOf(notNullValue(), instanceOf(String.class), equalTo("It's thirteen zero"))));
    }

    @Test(expected = InvalidTimeException.class)
    public void convertReturnsBlankTime() {
        this.speakingClock.convert("");
    }

    @Test(expected = InvalidTimeException.class)
    public void convertReturnsBlankHours() {
        this.speakingClock.convert(":01");
    }

    @Test(expected = InvalidTimeException.class)
    public void convertReturnsBlankMinutes() {
        this.speakingClock.convert("01:");
    }

    @Test(expected = InvalidTimeException.class)
    public void convertReturnsTimeWithoutSeparator() {
        this.speakingClock.convert("0159");
    }

    @Test(expected = InvalidTimeException.class)
    public void convertReturnsTimeWithOneDigitLess() {
        this.speakingClock.convert("01:5");
    }

    @Test(expected = InvalidTimeException.class)
    public void convertReturnsInvalidHours() {
        this.speakingClock.convert("HH:05");
    }

    @Test(expected = InvalidTimeException.class)
    public void convertReturnsInvalidMinutes() {
        this.speakingClock.convert("01:mm");
    }

    @Test(expected = InvalidTimeException.class)
    public void convertReturnsHoursLessRange() {
        this.speakingClock.convert("-1:05");
    }

    @Test(expected = InvalidTimeException.class)
    public void convertReturnsHoursMoreThanRange() {
        this.speakingClock.convert("24:05");
    }

    @Test(expected = InvalidTimeException.class)
    public void convertReturnsMinutesLessRange() {
        this.speakingClock.convert("01:-1");
    }

    @Test(expected = InvalidTimeException.class)
    public void convertReturnsMinutesMoreThanRange() {
        this.speakingClock.convert("01:60");
    }
}
