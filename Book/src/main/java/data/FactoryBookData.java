package data;

public class FactoryBookData {
    private FactoryBookData(){}
    public static BookData getBookData(TypeBookData typeBookData){
        switch (typeBookData){
            case CSV:
                return new CSVBookData("books.csv");
            case MY_SQL:
                return new MySQLBookData();
            case API:
                return new APIBookData();
            default:
                throw new RuntimeException("chua ho tro !");
        }
    }
}
