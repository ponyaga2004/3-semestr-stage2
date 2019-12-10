package sundukov.andrey.calls.command;

import sundukov.andrey.calls.Calls;
import sundukov.andrey.calls.Record;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LoadCommand implements Command {
    @Override
    public void execute(Calls calls) {
        FileDialog dialog = new FileDialog(calls.getFrame(), "Load", FileDialog.LOAD);
        dialog.doLayout();
        dialog.show();
        String filename = dialog.getFile();
        if (filename == null) {
            return;
        }
        String directory = dialog.getDirectory();

        List<Record> loaded = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(directory + filename));
            while (true) {
                String line = reader.readLine();
                if (line == null) break;

                Record record = Record.fromString(line);
                if (record == null) throw new RuntimeException(String.format("invalid line `%s`", line));

                loaded.add(record);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(calls.getFrame(), e.getMessage());
            return;
        }

        calls.setRecords(loaded);
    }
}
