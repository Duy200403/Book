package data;

import model.BookStore;

import java.util.List;

public class MySQLBookStoreData implements BookStoreData {
    public MySQLBookStoreData(){
        throw new RuntimeException("MYSQL chưa hỗ trợ!");
    }
    @Override
    public List<BookStore> getAllBook() {
        return null;
    }
}
