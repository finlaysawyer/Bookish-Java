package org.softwire.training.bookish;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class ImportCsvFile {

    public void importFile(String fileLocation) {

        CSVReader records = null;

        try {
            records = new CSVReader(new FileReader(fileLocation));
            String[] record = null;
            records.readNext();

            while ((record = records.readNext()) != null) {

            }
            records.close();

        } catch (IOException | NumberFormatException | CsvValidationException e) {
            System.out.println("Couldn't find file or encountered a formatting exception: " + e);
        }
    }

}
