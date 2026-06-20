package lab8;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class lab8_html_task1_4 {

    public static class NewsParserImproved {

        private static final String URL = "http://fat.urfu.ru/index.html";
        private static final String OUTPUT_FILE = "src/lr10/example4/news.txt";

        private static final int MAX_ATTEMPTS = 3;
        private static final int RETRY_DELAY_MS = 2000;

        public static void main(String[] args) {
            Document doc = connectWithRetry(URL, MAX_ATTEMPTS, RETRY_DELAY_MS);

            if (doc == null) {
                System.out.println("Не удалось получить страницу после " + MAX_ATTEMPTS + " попыток. Завершение работы.");
                return;
            }

            List<String> newsList = parseNews(doc);

            if (newsList.isEmpty()) {
                System.out.println("Новости не найдены — возможно, изменилась структура страницы.");
                return;
            }

            newsList.forEach(System.out::println);

            saveNewsToFile(newsList, OUTPUT_FILE);
        }

        private static Document connectWithRetry(String url, int maxAttempts, long delayMs) {
            for (int attempt = 1; attempt <= maxAttempts; attempt++) {
                try {
                    System.out.println("Попытка подключения " + attempt + " из " + maxAttempts + "...");
                    Document doc = Jsoup.connect(url)
                            .timeout(5000)
                            .get();
                    System.out.println("Подключение успешно установлено.");
                    return doc;
                } catch (IOException e) {
                    System.out.println("Ошибка при подключении к " + url + ": " + e.getMessage());

                    if (attempt < maxAttempts) {
                        System.out.println("Повторная попытка через " + (delayMs / 1000) + " сек...");
                        try {
                            Thread.sleep(delayMs);
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                            System.out.println("Ожидание прервано.");
                            return null;
                        }
                    }
                }
            }
            return null;
        }

        private static List<String> parseNews(Document doc) {
            List<String> result = new ArrayList<>();

            try {
                Elements newsParent = doc
                        .select("body > table > tbody > tr > td > div > table > " +
                                "tbody > tr:nth-child(5) > td:nth-child(3) > table > tbody > " +
                                "tr > td:nth-child(1)");

                if (newsParent.isEmpty()) {
                    System.out.println("Предупреждение: контейнер новостей не найден на странице.");
                    return result;
                }

                List<Node> nodes = newsParent.get(0).childNodes();

                for (int i = 3; i < nodes.size() && i < 20; i++) {
                    if (!(i % 2 == 0)) {
                        Element item = (Element) nodes.get(i);

                        String title = item.getElementsByClass("blocktitle").isEmpty()
                                ? "Без темы"
                                : item.getElementsByClass("blocktitle").get(0).childNodes().get(0).toString();

                        String date = item.getElementsByClass("blockdate").isEmpty()
                                ? "Без даты"
                                : item.getElementsByClass("blockdate").get(0).childNodes().get(0).toString();

                        result.add("Тема : " + title + "\nДата : " + date + "\n");
                    }
                }
            } catch (Exception e) {
                System.out.println("Ошибка при разборе HTML-структуры: " + e.getMessage());
            }

            return result;
        }

        private static void saveNewsToFile(List<String> newsList, String filePath) {
            try (FileWriter writer = new FileWriter(filePath)) {
                for (String news : newsList) {
                    writer.write(news);
                    writer.write("\n");
                }
                System.out.println("\nНовости успешно сохранены в файл: " + filePath);
            } catch (IOException e) {
                System.out.println("Ошибка при записи новостей в файл: " + e.getMessage());
            }
        }
    }
}
