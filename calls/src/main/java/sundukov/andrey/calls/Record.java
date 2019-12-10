package sundukov.andrey.calls;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Record {
    public final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuu.MM.dd");
    public final static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Record(String source, String destination, LocalDate date, LocalTime time, int duration) {
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.duration = duration;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isSource(String source) {
        return this.source.equals(source);
    }

    public boolean isDestination(String destination) {
        return this.destination.equals(destination);
    }

    public static Record fromString(String line) {
        String[] parts = line.split("\t");
        if (parts.length != 5) {
            return null;
        }

        LocalDate date;
        LocalTime time;
        int duration;
        try {
            date = LocalDate.parse(parts[2], DATE_FORMATTER);
            time = LocalTime.parse(parts[3], TIME_FORMATTER);
            duration = Integer.parseInt(parts[4]);
        } catch (Exception e) {
            return null;
        }

        return new Record(parts[0], parts[1], date, time, duration);
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s\t%s\t%d",
                source,
                destination,
                date.format(DATE_FORMATTER),
                time.format(TIME_FORMATTER),
                duration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return duration == record.duration &&
                source.equals(record.source) &&
                destination.equals(record.destination) &&
                date.equals(record.date) &&
                time.equals(record.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination, date, time, duration);
    }

    private String source;
    private String destination;
    private LocalDate date;
    private LocalTime time;
    private int duration;
}
