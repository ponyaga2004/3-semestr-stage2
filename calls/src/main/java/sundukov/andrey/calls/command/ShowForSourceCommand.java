package sundukov.andrey.calls.command;

import sundukov.andrey.calls.Calls;
import sundukov.andrey.calls.Record;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class ShowForSourceCommand implements Command {
    @Override
    public void execute(Calls calls) {
        String source = JOptionPane.showInputDialog("Source");

        List<Record> records = calls.getRecords();
        calls.setVisible(records.stream().filter(record -> record.isSource(source)).collect(Collectors.toList()));
    }
}
