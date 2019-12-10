package sundukov.andrey.calls.command;

import sundukov.andrey.calls.Calls;
import sundukov.andrey.calls.Record;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ShowInRangeCommand implements Command {
    @Override
    public void execute(Calls calls) {
        String from = JOptionPane.showInputDialog("From date (YYYY.MM.DD)");
        String to = JOptionPane.showInputDialog("To date (YYYY.MM.DD)");

        LocalDate fromDate = LocalDate.parse(from, Record.DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(to, Record.DATE_FORMATTER);

        List<Record> records = calls.getRecords();
        List<Record> filtered = records.stream().filter(
                r -> !fromDate.isAfter(r.getDate()) && !toDate.isBefore(r.getDate())).collect(Collectors.toList());
        calls.setVisible(filtered);
    }
}
