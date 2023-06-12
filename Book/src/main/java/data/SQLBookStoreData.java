package data;

import model.BookStore;

import java.util.List;

public class SQLBookStoreData implements BookStoreData{
    public SQLBookStoreData(){
        throw new RuntimeException("SQL chưa hỗ trợ!");
    }
    @Override
    public List<BookStore> getAllBook() {
        return null;
    }
}
