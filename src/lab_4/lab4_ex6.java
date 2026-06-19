package lab_4;

public class lab4_ex6 {
    public static void main(String[] args) {
        try {
            System.out.println("0");
            throw new NullPointerException("ошибка");
        } catch (ArithmeticException e) {
            System.out.println("1");
        } catch (RuntimeException e) {   // ← было после Exception, перенесено сюда
            System.out.println("3");
        } catch (Exception e) {          // ← теперь самый общий блок — последний
            System.out.println("2");
        }
        System.out.println("4");
    }
}
