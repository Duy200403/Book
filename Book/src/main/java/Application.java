import data.BookData;
import data.FactoryBookData;
import data.TypeBookData;
import model.Book;

import java.util.*;

public class Application {
    private static void menu() {
        System.out.println("--- BOOK MANAGER ---");
        System.out.println("1. Danh sach cac cuon sach");
        System.out.println("2. Tim kiem sach theo tac gia, ISBN hoac tieu de");
        System.out.println("3. Sap xep theo diem rating tang dan");
        System.out.println("4. Lay ra 10 cuon sach co rating cao nhat");
        System.out.println("0. Thoat");
        System.out.println("nhap lua chon cua ban");
    }

    public static void main(String[] args) {
        BookData bookData = FactoryBookData.getBookData(TypeBookData.CSV);
        // Lay danh sach sach
        List<Book> books = bookData.getAllBook();
        Scanner in = new Scanner(System.in);
        int choice = -1;
        do {
            menu();
            try {
                choice = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap mot lua chon hop le.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Danh sach cac cuon sach:");
                    for (int i = 0; i < books.size(); i++) {
                        System.out.printf("[STT = %d, ISBN = %s, TITLE = %s]%n", i + 1, books.get(i).getIsbn(), books.get(i).getTitle());
                    }
                    break;

                case 2:
                    System.out.println("Nhap tac gia, ISBN hoac tieu de sach:");
                    String keyword = in.nextLine();
                    List<Book> searchResults = searchBooks(books, keyword);
                    if (searchResults.isEmpty()) {
                        System.out.println("Khong tim thay cuon sach nao phu hop.");
                    } else {
                        System.out.println("Ket qua tim kiem:");
                        for (int i = 0; i < searchResults.size(); i++) {
                            System.out.printf("[STT = %d, ISBN = %s, TITLE = %s]%n", i + 1, searchResults.get(i).getIsbn(), searchResults.get(i).getTitle());
                        }
                    }
                    break;

                case 3:
                    sortBooksByRating(books);
                    System.out.println("Danh sach cac cuon sach da duoc sap xep theo diem rating tang dan:");
                    for (int i = 0; i < books.size(); i++) {
                        System.out.printf("[STT = %d, ISBN = %s, TITLE = %s, RATING = %.2f]%n", i + 1, books.get(i).getIsbn(), books.get(i).getTitle(), books.get(i).getAverageRating());
                    }
                    break;

                case 4:
                    List<Book> topRatedBooks = getTopRatedBooks(books, 10);
                    System.out.println("Danh sach 10 cuon sach co rating cao nhat:");
                    for (int i = 0; i < topRatedBooks.size(); i++) {
                        System.out.printf("[STT = %d, ISBN = %s, TITLE = %s, RATING = %.2f]%n", i + 1, topRatedBooks.get(i).getIsbn(), topRatedBooks.get(i).getTitle(), topRatedBooks.get(i).getAverageRating());
                    }
                    break;

                case 0:
                    System.out.println("Thoat chuong trinh.");
                    break;

                default:
                    System.out.println("Lua chon khong hop le.");
                    break;
            }
        } while (choice != 0);
    }

    private static List<Book> searchBooks(List<Book> books, String keyword) {
        List<Book> searchResults = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthors().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getIsbn().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(book);
            }
        }
        return searchResults;
    }

    private static void sortBooksByRating(List<Book> books) {
        Collections.sort(books, Comparator.comparingDouble(Book::getAverageRating));
    }

    private static List<Book> getTopRatedBooks(List<Book> books, int count) {
        List<Book> topRatedBooks = new ArrayList<>(books);
        topRatedBooks.sort(Comparator.comparingDouble(Book::getAverageRating).reversed());
        return topRatedBooks.subList(0, Math.min(count, topRatedBooks.size()));
    }
}
