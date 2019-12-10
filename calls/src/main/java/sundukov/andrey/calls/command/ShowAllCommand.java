package sundukov.andrey.calls.command;

import sundukov.andrey.calls.Calls;

public class ShowAllCommand implements Command {
    @Override
    public void execute(Calls calls) {
        calls.setVisible(calls.getRecords());
    }
}
