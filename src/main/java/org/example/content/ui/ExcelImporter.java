package org.example.content.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class ExcelImporter extends JFrame
        implements ActionListener {
    private JTable table;
    private JButton importButton;

    public ExcelImporter() {
        // Create a new frame
        super("Excel Importer");
        this.setBounds(100,100,600,600);
        // Create a new panel to hold the table and button
        JPanel panel = new JPanel();

        // Create a new table with no data
        table = new JTable(new DefaultTableModel());

        // Create a new button to import Excel data
        importButton = new JButton("Import Excel");
        importButton.addActionListener(this);
        String[] columnNames = {"Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        Object[][] rowData = readExcelFile("C:/Users/23305/Desktop/table.xls");

        // Create a new table with the data
        JTable table = new JTable(new DefaultTableModel(rowData, columnNames));

        // Use a cell renderer to display multi-line text in the table
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            JTextArea textArea = new JTextArea();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                // If the value in the cell is a String, set the text of the JTextArea to it
                if (value instanceof String) {
                    textArea.setText((String) value);
                }
                // Otherwise,set the text of the JTextArea to an empty string
                else {
                    textArea.setText("");
                }

                // Set the size of the JTextArea to match the cell
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setSize(table.getColumnModel().getColumn(column).getWidth(),
                        table.getRowHeight(row));

                // Return the JTextArea as the cell renderer component
                return textArea;
            }
        };

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        table.getTableHeader().setVisible(false);
        table.setRowHeight(170);
        table.setRowHeight(0,30);
        panel.add(new JScrollPane(table));
        panel.add(importButton);
        add(panel);
        setResizable(false);
        setVisible(true);
    }
    private Object[][] readExcelFile(String filePath) {
        try {
            // Create a new Workbook object from the Excel file
            Workbook workbook = WorkbookFactory.create(new File(filePath));

            // Get the first sheet in the workbook
            Sheet sheet = workbook.getSheetAt(0);

            // Define the size of the 2D array based on the number of rows and columns in the sheet
            int numRows = sheet.getLastRowNum() + 1;
            int numCols = sheet.getRow(0).getLastCellNum();
            // Create a new 2D array to hold the data from the sheet
            Object[][] data = new Object[numRows][numCols];
            // Iterate over the rows and columns in the sheet, and fill the 2D array with the cell values
            for (int i = 0; i < numRows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < numCols; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING:
                                data[i][j] = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                data[i][j] = cell.getNumericCellValue();
                                break;
                            case BOOLEAN:
                                data[i][j] = cell.getBooleanCellValue();
                                break;
                            default:
                                data[i][j] = "";
                                break;
                        }
                    } else {
                        data[i][j] = "";
                    }
                }
            }
            // Return the 2D array of data
            return data;
        } catch (IOException e) {
            // Handle any exceptions that occur while reading the file
            e.printStackTrace();
            return new Object[0][0];
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the selected file from the file chooser
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xls", "xlsx");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            try {
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                Workbook workbook = WorkbookFactory.create(fileInputStream);
                Sheet sheet = workbook.getSheetAt(0);
                int numRows = sheet.getLastRowNum();
                int numCols = sheet.getRow(0).getLastCellNum();
                DefaultTableModel model = new DefaultTableModel(numRows + 1, numCols);
                for (int i = 0; i <= numRows; i++) {
                    Row row = sheet.getRow(i);
                    for (int j = 0; j < numCols; j++) {
                        Cell cell = row.getCell(j);
                        if (cell != null) {
                            model.setValueAt(cell.getStringCellValue(), i, j);
                        } else {
                            model.setValueAt("", i, j);
                        }
                    }
                }
                table.setModel(model);
                fileInputStream.close();
                workbook.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}