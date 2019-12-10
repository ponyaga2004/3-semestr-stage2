package sundukov.andrey.calls.command;

import sundukov.andrey.calls.Calls;
import sundukov.andrey.calls.Record;

import java.util.List;

public class DeleteCommand implements Command {
    public DeleteCommand(Record record) {
        this.record = record;
    }

    @Override
    public void execute(Calls calls) {
        List<Record> records = calls.getRecords();
        records.remove(this.record);
        calls.setRecords(records);
    }

    private final Record record;
}
