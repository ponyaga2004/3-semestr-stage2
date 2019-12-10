package sundukov.andrey.calls.command;

import sundukov.andrey.calls.Calls;
import sundukov.andrey.calls.Record;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortByDurationCommand implements Command {
    @Override
    public void execute(Calls calls) {
        List<Record> records = new ArrayList<>(calls.getRecords());
        records.sort(Comparator.comparing(Record::getDuration));
        calls.setVisible(records);
    }
}
