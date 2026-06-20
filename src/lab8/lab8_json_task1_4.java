package lab8;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class lab8_json_task1_4 {

    public static class JsonBookManager {

        // Путь к JSON-файлу, с которым работаем
        private static final String FILE_PATH = "src/lab8/example-json.json";

        public static void main(String[] args) {
            // 1. Добавляем новую книгу в JSON-файл
            addBook("1984", "Джордж Оруэлл", 1949);

            // 2. Выводим все книги после добавления
            System.out.println("=== Все книги после добавления ===");
            printBooks(getAllBooks());

            // 3. Поиск по автору
            System.out.println("\n=== Поиск по автору: Лев Толстой ===");
            printBooks(findBooksByAuthor("Лев Толстой"));

            // 4. Удаление книги по названию
            System.out.println("\n=== Удаляем книгу '1984' ===");
            removeBookByTitle("1984");

            System.out.println("\n=== Все книги после удаления ===");
            printBooks(getAllBooks());
        }

        public static void addBook(String title, String author, int year) {
            try {
                JSONObject library = loadLibrary();
                JSONArray books = (JSONArray) library.get("books");

                JSONObject book = new JSONObject();
                book.put("title", title);
                book.put("author", author);
                book.put("year", year);

                books.add(book);

                saveLibrary(library);
                System.out.println("Книга \"" + title + "\" успешно добавлена.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static List<JSONObject> findBooksByAuthor(String author) {
            List<JSONObject> result = new ArrayList<>();
            try {
                JSONObject library = loadLibrary();
                JSONArray books = (JSONArray) library.get("books");

                for (Object o : books) {
                    JSONObject book = (JSONObject) o;
                    String bookAuthor = String.valueOf(book.get("author"));
                    if (bookAuthor.equalsIgnoreCase(author)) {
                        result.add(book);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        public static void removeBookByTitle(String title) {
            try {
                JSONObject library = loadLibrary();
                JSONArray books = (JSONArray) library.get("books");

                List<Object> toRemove = new ArrayList<>();
                for (Object o : books) {
                    JSONObject book = (JSONObject) o;
                    String bookTitle = String.valueOf(book.get("title"));
                    if (bookTitle.equalsIgnoreCase(title)) {
                        toRemove.add(book);
                    }
                }

                if (toRemove.isEmpty()) {
                    System.out.println("Книга \"" + title + "\" не найдена.");
                    return;
                }

                books.removeAll(toRemove);

                saveLibrary(library);
                System.out.println("Книга \"" + title + "\" успешно удалена.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @SuppressWarnings("unchecked")
        public static List<JSONObject> getAllBooks() {
            List<JSONObject> result = new ArrayList<>();
            try {
                JSONObject library = loadLibrary();
                JSONArray books = (JSONArray) library.get("books");
                for (Object o : books) {
                    result.add((JSONObject) o);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        private static void printBooks(List<JSONObject> books) {
            if (books.isEmpty()) {
                System.out.println("Ничего не найдено.");
                return;
            }
            for (JSONObject book : books) {
                System.out.println("Название: " + book.get("title")
                        + " | Автор: " + book.get("author")
                        + " | Год: " + book.get("year"));
            }
        }

        private static JSONObject loadLibrary() throws Exception {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(FILE_PATH));
            return (JSONObject) obj;
        }

        private static void saveLibrary(JSONObject library) throws Exception {
            try (FileWriter file = new FileWriter(FILE_PATH)) {
                file.write(library.toJSONString());
            }
        }
    }
}
