package data;

import com.opencsv.CSVReader;
import model.Book;
import model.BookImpl;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVBookData implements BookData {
    private String path;

    public CSVBookData(String path) {
        this.path = path;
    }

    @Override
    public List<Book> getAllBook() {
        List<Book> bookList = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());

        final int COL_ID = 0,
                COL_ISBN = 5,
                COL_TITLE = 9,
                COL_AUTHORS = 7,
                COL_RATING = 12,
                COL_LANGUAGE = 11,
                COL_YEAR = 8;

        try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
            String[] row;
            // Đọc lần đầu để bỏ qua tiêu đề cột
            csvReader.readNext();

            while ((row = csvReader.readNext()) != null) {
                BookImpl book = new BookImpl(
                        Long.parseLong(row[COL_ID]),
                        row[COL_ISBN],
                        row[COL_TITLE],
                        row[COL_AUTHORS],
                        Double.parseDouble(row[COL_RATING]),
                        row[COL_LANGUAGE],
                        row[COL_YEAR].isEmpty() ? 0 : (int) Float.parseFloat(row[COL_YEAR])
                );
                bookList.add(book);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return bookList;
    }
}
