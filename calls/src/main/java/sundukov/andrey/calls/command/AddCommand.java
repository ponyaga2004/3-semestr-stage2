package sundukov.andrey.calls.command;

import sundukov.andrey.calls.Calls;
import sundukov.andrey.calls.Record;

import javax.swing.*;
import java.util.List;

public class AddCommand implements Command {
    @Override
    public void execute(Calls calls) {
        List<Record> records = calls.getRecords();

        String source = JOptionPane.showInputDialog("Source");
        String destination = JOptionPane.showInputDialog("Destination");
        String date = JOptionPane.showInputDialog("Date (YYYY.MM.DD)");
        String time = JOptionPane.showInputDialog("Time (HH:MM:SS)");
        String duration = JOptionPane.showInputDialog("Duration");

        Record record = Record.fromString(String.format("%s\t%s\t%s\t%s\t%s", source, destination, date, time, duration));
        if (record == null) {
            JOptionPane.showMessageDialog(calls.getFrame(), "Parsing error");
            return;
        }

        records.add(record);
        calls.setRecords(records);
    }
}
