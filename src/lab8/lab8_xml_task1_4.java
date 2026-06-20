package lab8;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class lab8_xml_task1_4 {

    public static class XmlBookManager {

        // Путь к XML-файлу, с которым работаем
        private static final String FILE_PATH = "src/lab8/example.xml";

        public static void main(String[] args) {
            // 1. Добавляем новую книгу в XML-файл
            addBook("1984", "Джордж Оруэлл", "1949");

            // 2. Выводим всех книг файла (для наглядности)
            System.out.println("=== Все книги после добавления ===");
            printBooks(getAllBooks());

            // 3. Поиск по автору
            System.out.println("\n=== Поиск по автору: Лев Толстой ===");
            printBooks(findBooksByAuthor("Лев Толстой"));

            // 4. Поиск по году издания
            System.out.println("\n=== Поиск по году: 1967 ===");
            printBooks(findBooksByYear("1967"));

            // 5. Удаление книги по названию
            System.out.println("\n=== Удаляем книгу '1984' ===");
            removeBookByTitle("1984");

            System.out.println("\n=== Все книги после удаления ===");
            printBooks(getAllBooks());
        }

        public static void addBook(String title, String author, String year) {
            try {
                Document doc = loadDocument();
                Element root = doc.getDocumentElement();

                Element book = doc.createElement("book");

                Element titleEl = doc.createElement("title");
                titleEl.appendChild(doc.createTextNode(title));
                book.appendChild(titleEl);

                Element authorEl = doc.createElement("author");
                authorEl.appendChild(doc.createTextNode(author));
                book.appendChild(authorEl);

                Element yearEl = doc.createElement("year");
                yearEl.appendChild(doc.createTextNode(year));
                book.appendChild(yearEl);

                root.appendChild(book);

                saveDocument(doc);
                System.out.println("Книга \"" + title + "\" успешно добавлена.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static List<Element> findBooksByAuthor(String author) {
            List<Element> result = new ArrayList<>();
            try {
                Document doc = loadDocument();
                NodeList books = doc.getElementsByTagName("book");

                for (int i = 0; i < books.getLength(); i++) {
                    Element book = (Element) books.item(i);
                    String bookAuthor = getElementText(book, "author");
                    if (bookAuthor != null && bookAuthor.equalsIgnoreCase(author)) {
                        result.add(book);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        public static List<Element> findBooksByYear(String year) {
            List<Element> result = new ArrayList<>();
            try {
                Document doc = loadDocument();
                NodeList books = doc.getElementsByTagName("book");

                for (int i = 0; i < books.getLength(); i++) {
                    Element book = (Element) books.item(i);
                    String bookYear = getElementText(book, "year");
                    if (bookYear != null && bookYear.equals(year)) {
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
                Document doc = loadDocument();
                NodeList books = doc.getElementsByTagName("book");

                List<Node> toRemove = new ArrayList<>();
                for (int i = 0; i < books.getLength(); i++) {
                    Element book = (Element) books.item(i);
                    String bookTitle = getElementText(book, "title");
                    if (bookTitle != null && bookTitle.equalsIgnoreCase(title)) {
                        toRemove.add(book);
                    }
                }

                if (toRemove.isEmpty()) {
                    System.out.println("Книга \"" + title + "\" не найдена.");
                    return;
                }

                for (Node node : toRemove) {
                    node.getParentNode().removeChild(node);
                }

                saveDocument(doc);
                System.out.println("Книга \"" + title + "\" успешно удалена.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static List<Element> getAllBooks() {
            List<Element> result = new ArrayList<>();
            try {
                Document doc = loadDocument();
                NodeList books = doc.getElementsByTagName("book");
                for (int i = 0; i < books.getLength(); i++) {
                    result.add((Element) books.item(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        private static void printBooks(List<Element> books) {
            if (books.isEmpty()) {
                System.out.println("Ничего не найдено.");
                return;
            }
            for (Element book : books) {
                System.out.println("Название: " + getElementText(book, "title")
                        + " | Автор: " + getElementText(book, "author")
                        + " | Год: " + getElementText(book, "year"));
            }
        }

        private static String getElementText(Element parent, String tagName) {
            NodeList nodes = parent.getElementsByTagName(tagName);
            if (nodes.getLength() == 0) {
                return null;
            }
            return nodes.item(0).getTextContent();
        }

        private static Document loadDocument() throws Exception {
            File inputFile = new File(FILE_PATH);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            return doc;
        }

        private static void saveDocument(Document doc) throws Exception {
            doc.setXmlStandalone(true);
            doc.normalizeDocument();

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(FILE_PATH));
            transformer.transform(source, result);
        }
    }
}
