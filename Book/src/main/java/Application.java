import data.BookStoreData;
import data.FactoryBookStoreData;
import data.TypeBookStoreData;
import model.BookStore;

import java.util.*;

public class Application {
    private static void menu() {
        System.out.println("--- BOOK MANAGER ---");
        System.out.println("1. List all books");
        System.out.println("2. Search books by author, ISBN, or title");
        System.out.println("3. Sort books by increasing rating");
        System.out.println("4. Get top 10 books with highest rating");
        System.out.println("0. Exit");
        System.out.println("Choose an option: ");
    }

    public static void main(String[] args) {
        BookStoreData bookStoreData = FactoryBookStoreData.getBookData(TypeBookStoreData.CSV);
        // Lay danh sach sach
        List<BookStore> books = bookStoreData.getAllBook();
        Scanner in = new Scanner(System.in);
        int choice = -1;
        do {
            menu();
            try {
                choice = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid choice.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("List of all books:");
                    for (int i = 0; i < books.size(); i++) {
                        System.out.printf("[STT = %d, ISBN = %s, TITLE = %s]%n", i + 1, books.get(i).getIsbn(), books.get(i).getTitle());
                    }
                    break;

                case 2:
                    System.out.println("Enter author, ISBN, or title of the book:");
                    String keyword = in.nextLine();
                    List<BookStore> searchResults = searchBooks(books, keyword);
                    if (searchResults.isEmpty()) {
                        System.out.println("No matching books found.");
                    } else {
                        System.out.println("Search results:");
                        for (int i = 0; i < searchResults.size(); i++) {
                            System.out.printf("[STT = %d, ISBN = %s, TITLE = %s]%n", i + 1, searchResults.get(i).getIsbn(), searchResults.get(i).getTitle());
                        }
                    }
                    break;

                case 3:
                    sortBooksByRating(books);
                    System.out.println("List of books sorted by increasing rating:");
                    for (int i = 0; i < books.size(); i++) {
                        System.out.printf("[STT = %d, ISBN = %s, TITLE = %s, RATING = %.2f]%n", i + 1, books.get(i).getIsbn(), books.get(i).getTitle(), books.get(i).getAverageRating());
                    }
                    break;

                case 4:
                    List<BookStore> topRatedBooks = getTopRatedBooks(books, 10);
                    System.out.println("Top 10 books with highest rating:");
                    for (int i = 0; i < topRatedBooks.size(); i++) {
                        System.out.printf("[STT = %d, ISBN = %s, TITLE = %s, RATING = %.2f]%n", i + 1, topRatedBooks.get(i).getIsbn(), topRatedBooks.get(i).getTitle(), topRatedBooks.get(i).getAverageRating());
                    }
                    break;

                case 0:
                    System.out.println("Exiting the program.");
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 0);
    }

    static List<BookStore> searchBooks(List<BookStore> books, String keyword) {
        List<BookStore> searchResults = new ArrayList<>();
        for (BookStore book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthors().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getIsbn().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(book);
            }
        }
        return searchResults;
    }

    static void sortBooksByRating(List<BookStore> books) {
        Collections.sort(books, Comparator.comparingDouble(BookStore::getAverageRating));
    }

    static List<BookStore> getTopRatedBooks(List<BookStore> books, int count) {
        List<BookStore> topRatedBooks = new ArrayList<>(books);
        topRatedBooks.sort(Comparator.comparingDouble(BookStore::getAverageRating).reversed());
        return topRatedBooks.subList(0, Math.min(count, topRatedBooks.size()));
    }
}
