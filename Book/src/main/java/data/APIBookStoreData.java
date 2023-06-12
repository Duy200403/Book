package data;

import model.BookStore;

import java.util.List;

public class APIBookStoreData implements BookStoreData {
    public APIBookStoreData(){
        throw new RuntimeException("API chưa hỗ trợ!");
    }
    public List<BookStore> getAllBook() {
        return null;
    }
}
