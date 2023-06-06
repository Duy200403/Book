package data;

import model.Book;

import java.util.List;

public class MySQLBookData implements BookData{
    public MySQLBookData (){
        throw new RuntimeException("MYSQL chưa hỗ trợ!");
    }
    @Override
    public List<Book> getAllBook() {
        return null;
    }
}
