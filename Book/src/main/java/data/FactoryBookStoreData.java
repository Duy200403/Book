package data;

public class FactoryBookStoreData {
    private FactoryBookStoreData(){}
    public static BookStoreData getBookData(TypeBookStoreData typeBookData){
        switch (typeBookData){
            case CSV:
                return new CSVBookStoreData("books.csv");
            case MY_SQL:
                return new MySQLBookStoreData();
            case API:
                return new APIBookStoreData();
            case SQL:
                return new SQLBookStoreData();
            default:
                throw new RuntimeException("chua ho tro !");
        }
    }
}
