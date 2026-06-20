package lab8;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class lab8_excel_task1 {

    public static class ReadExcelFileImproved {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            boolean success = false;

            while (!success) {
                System.out.print("Введите путь к Excel-файлу (например, src/lr10/example3.xlsx): ");
                String filePath = scanner.nextLine().trim();

                System.out.print("Введите название листа для чтения (например, Товары): ");
                String sheetName = scanner.nextLine().trim();

                success = readExcelFile(filePath, sheetName);

                if (!success) {
                    System.out.print("\nПопробовать снова? (да/нет): ");
                    String answer = scanner.nextLine().trim().toLowerCase();
                    if (!answer.equals("да")) {
                        System.out.println("Завершение работы программы.");
                        break;
                    }
                    System.out.println();
                }
            }

            scanner.close();
        }

        private static boolean readExcelFile(String filePath, String sheetName) {
            File file = new File(filePath);

            if (!file.exists()) {
                System.out.println("Ошибка: файл \"" + filePath + "\" не найден.");
                System.out.println("Рекомендация: проверьте правильность пути и убедитесь, что файл существует.");
                return false;
            }

            if (!filePath.toLowerCase().endsWith(".xlsx") && !filePath.toLowerCase().endsWith(".xls")) {
                System.out.println("Ошибка: файл \"" + filePath + "\" имеет неподдерживаемое расширение.");
                System.out.println("Рекомендация: используйте файл формата .xlsx или .xls.");
                return false;
            }

            try (FileInputStream fis = new FileInputStream(file);
                 Workbook workbook = WorkbookFactory.create(fis)) {

                Sheet sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    System.out.println("Ошибка: лист с названием \"" + sheetName + "\" не найден в файле.");
                    System.out.println("Доступные листы в файле:");
                    for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                        System.out.println("  - " + workbook.getSheetName(i));
                    }
                    System.out.println("Рекомендация: укажите одно из названий листов выше.");
                    return false;
                }

                if (sheet.getPhysicalNumberOfRows() == 0) {
                    System.out.println("Предупреждение: лист \"" + sheetName + "\" пуст, данных для вывода нет.");
                    return true;
                }

                System.out.println("\nСодержимое листа \"" + sheetName + "\":");
                for (Row row : sheet) {
                    StringBuilder rowText = new StringBuilder();
                    for (Cell cell : row) {
                        rowText.append(getCellValueAsString(cell)).append(" | ");
                    }
                    System.out.println(rowText);
                }

                return true;

            } catch (FileNotFoundException e) {
                System.out.println("Ошибка: файл \"" + filePath + "\" недоступен для чтения.");
                System.out.println("Рекомендация: убедитесь, что файл не был удалён или перемещён, и что у вас есть права на чтение.");
                return false;

            } catch (IOException e) {
                System.out.println("Ошибка ввода-вывода при чтении файла: " + e.getMessage());
                System.out.println("Рекомендация: закройте файл, если он открыт в другой программе (например, в Excel), и попробуйте снова.");
                return false;

            } catch (Exception e) {
                System.out.println("Непредвиденная ошибка при обработке файла: " + e.getMessage());
                System.out.println("Рекомендация: проверьте корректность файла или обратитесь к разработчику с текстом этой ошибки.");
                return false;
            }
        }

        private static String getCellValueAsString(Cell cell) {
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        return cell.getDateCellValue().toString();
                    }
                    return String.valueOf(cell.getNumericCellValue());
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                case FORMULA:
                    return cell.getCellFormula();
                case BLANK:
                    return "";
                default:
                    return "?";
            }
        }
    }
}
