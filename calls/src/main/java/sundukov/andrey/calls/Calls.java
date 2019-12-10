package sundukov.andrey.calls;

import sundukov.andrey.calls.command.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.function.Supplier;

public class Calls {
    public Calls() {
        records = new ArrayList<>();
        visible = new ArrayList<>();

        frame = new JFrame("Calls");
        frame.setSize(1400, 800);
        frame.setLayout(new BorderLayout());
        buildLayout(frame);
        frame.setVisible(true);
    }

    private void buildLayout(final JFrame container) {
        headers = new Vector<>();
        headers.add("Source");
        headers.add("Destination");
        headers.add("Date");
        headers.add("Time");
        headers.add("Duration");

        GridLayout gridLayout = new GridLayout(2, 4);

        JPanel buttons = new JPanel();
        buttons.setLayout(gridLayout);

        buttons.add(createButton("Load", LoadCommand::new));
        buttons.add(createButton("Add", AddCommand::new));
        buttons.add(createButton("Show All", ShowAllCommand::new));
        buttons.add(createButton("Show For Source", ShowForSourceCommand::new));
        buttons.add(createButton("Sorted By Age", SortedByAgeCommand::new));
        buttons.add(createButton("Save", () -> {
            if (records.size() == 0) {
                return null;
            }
            return new SaveCommand();
        }));
        buttons.add(createButton("Delete", () -> {
            JTable table = (JTable) tableHolder.getViewport().getView();
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                return null;
            }
            return new DeleteCommand(visible.get(selectedRow));
        }));
        buttons.add(createButton("Show In Range", ShowInRangeCommand::new));
        buttons.add(createButton("Show For Destination", ShowForDestinationCommand::new));
        buttons.add(createButton("Sorted By Duration", SortByDurationCommand::new));

        tableHolder = new JScrollPane();
        updateTableView();

        container.add(tableHolder, BorderLayout.CENTER);
        container.add(buttons, BorderLayout.SOUTH);
    }

    private JButton createButton(String title, Supplier<Command> commandSupplier) {
        JButton button = new JButton(title);
        button.addActionListener(actionEvent -> {
            Command command = commandSupplier.get();
            if (command != null) {
                command.execute(this);
            }
        });
        return button;
    }

    public JFrame getFrame() {
        return frame;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
        this.visible = this.records;
        updateTableView();
    }

    public void setVisible(List<Record> visible) {
        this.visible = visible;
        updateTableView();
    }

    private void updateTableView() {
        Vector<Vector<String>> rows = new Vector<>();
        visible.forEach(record -> {
            Vector<String> row = new Vector<>();
            row.add(record.getSource());
            row.add(record.getDestination());
            row.add(record.getDate().format(Record.DATE_FORMATTER));
            row.add(record.getTime().format(Record.TIME_FORMATTER));
            row.add(String.valueOf(record.getDuration()));
            rows.add(row);
        });
        JTable table = new JTable(rows, headers);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableHolder.getViewport().setView(table);
    }

    private JFrame frame;
    private JScrollPane tableHolder;
    private Vector<Object> headers;
    private java.util.List<Record> records;
    private java.util.List<Record> visible;
}
