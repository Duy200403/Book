package model;

public class BookStoreImpl implements BookStore {
    private long id;
    private String isbn;
    private String title;
    private String author;
    private double averageRating;
    private String language;
    private int year;

    public BookStoreImpl(long id, String isbn, String title, String author, double averageRating, String language, int year) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.averageRating = averageRating;
        this.language = language;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String getAuthors() {
        return author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "BookImpl{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", averageRating=" + averageRating +
                ", language='" + language + '\'' +
                ", year=" + year +
                '}';
    }
}
