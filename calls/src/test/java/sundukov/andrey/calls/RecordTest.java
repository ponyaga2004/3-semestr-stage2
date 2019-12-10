package sundukov.andrey.calls;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class RecordTest {
    @Test
    void toStringSanityCheck() {
        final LocalDate date = LocalDate.of(2019, 12, 8);
        final LocalTime time = LocalTime.of(13, 24, 35);
        final Record record = new Record("SSS", "DDD", date, time, 300);
        final String repr = record.toString();
        assertEquals("SSS\tDDD\t2019.12.08\t13:24:35\t300", repr);
    }

    @Test
    void fromStringSanityCheck() {
        final Record record = Record.fromString("SSS\tDDD\t2019.12.08\t13:24:35\t300");
        assertEquals("SSS", record.getSource());
        assertEquals("DDD", record.getDestination());
        assertEquals(LocalDate.of(2019, 12, 8), record.getDate());
        assertEquals(LocalTime.of(13, 24, 35), record.getTime());
        assertEquals(300, record.getDuration());
    }

    @Test
    void toStringAndFromStringAreSymmetric() {
        final Record record = new Record("source", "destination", LocalDate.of(2019, 12, 8), LocalTime.of(13, 24, 35), 444);
        final Record actual = Record.fromString(record.toString());
        assertEquals(record, actual);
    }

    @Test
    void predicates() {
        final Record record = new Record("source", "destination", LocalDate.of(2019, 12, 8), LocalTime.of(13, 24, 35), 444);
        assertTrue(record.isSource("source"));
        assertTrue(record.isDestination("destination"));
        assertFalse(record.isSource("abc"));
        assertFalse(record.isSource("destination"));
        assertFalse(record.isDestination("abc"));
        assertFalse(record.isDestination("source"));
    }
}
