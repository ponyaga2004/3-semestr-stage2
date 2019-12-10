package sundukov.andrey.calls.command;

import sundukov.andrey.calls.Calls;
import sundukov.andrey.calls.Record;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortedByAgeCommand implements Command {
    @Override
    public void execute(Calls calls) {
        List<Record> records = new ArrayList<>(calls.getRecords());
        records.sort(Comparator.comparing(r -> LocalDateTime.of(r.getDate(), r.getTime())));
        calls.setVisible(records);
    }
}
