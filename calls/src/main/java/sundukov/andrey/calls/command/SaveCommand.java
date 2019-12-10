package sundukov.andrey.calls.command;

import sundukov.andrey.calls.Calls;
import sundukov.andrey.calls.Record;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class SaveCommand implements Command {
    @Override
    public void execute(Calls calls) {
        FileDialog dialog = new FileDialog(calls.getFrame(), "Save", FileDialog.SAVE);
        dialog.doLayout();
        dialog.show();
        String filename = dialog.getFile();
        if (filename == null) {
            return;
        }
        String directory = dialog.getDirectory();

        List<Record> records = calls.getRecords();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(directory + filename));
            for (Record record : records) {
                writer.write(record.toString());
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(calls.getFrame(), "Write file error: " + e.getMessage());
        }
    }
}
