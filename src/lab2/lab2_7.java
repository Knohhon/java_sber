package lab2;
import java.util.ArrayList;
import java.util.List;

public class lab2_7 {

    interface BankAccount {
        void deposit(double amount);           // Пополнение
        void withdraw(double amount);          // Снятие
        double getBalance();                   // Текущий баланс
        String getAccountNumber();             // Номер счёта
        List<String> getTransactionHistory();  // История транзакций
    }

    static class StandardAccount implements BankAccount {

        private String accountNumber;
        private String ownerName;
        private double balance;
        private List<String> history;

        public StandardAccount(String accountNumber, String ownerName, double initialBalance) {
            if (initialBalance < 0)
                throw new IllegalArgumentException("Начальный баланс не может быть отрицательным!");
            this.accountNumber = accountNumber;
            this.ownerName     = ownerName;
            this.balance       = initialBalance;
            this.history       = new ArrayList<>();
            history.add("Счёт открыт. Начальный баланс: " + initialBalance + " руб.");
        }

        @Override
        public void deposit(double amount) {
            if (amount <= 0) {
                System.out.println("Сумма пополнения должна быть положительной!");
                return;
            }
            balance += amount;
            history.add("Пополнение: +" + amount + " руб. | Баланс: " + balance + " руб.");
            System.out.println("Пополнение на " + amount + " руб. Баланс: " + balance + " руб.");
        }

        @Override
        public void withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("Сумма снятия должна быть положительной!");
                return;
            }
            if (amount > balance) {
                System.out.println("Недостаточно средств! Баланс: " + balance + " руб.");
                history.add("Отказ в снятии: " + amount + " руб. | Недостаточно средств.");
                return;
            }
            balance -= amount;
            history.add("Снятие: -" + amount + " руб. | Баланс: " + balance + " руб.");
            System.out.println("Снятие " + amount + " руб. Баланс: " + balance + " руб.");
        }

        @Override
        public double getBalance() { return balance; }

        @Override
        public String getAccountNumber() { return accountNumber; }

        @Override
        public List<String> getTransactionHistory() { return history; }

        public String getOwnerName() { return ownerName; }

        @Override
        public String toString() {
            return "StandardAccount { номер='" + accountNumber +
                    "', владелец='" + ownerName +
                    "', баланс=" + balance + " руб. }";
        }
    }


    static class SavingsAccount implements BankAccount {

        private String accountNumber;
        private String ownerName;
        private double balance;
        private double interestRate; // процентная ставка (например, 0.05 = 5%)
        private List<String> history;

        public SavingsAccount(String accountNumber, String ownerName,
                              double initialBalance, double interestRate) {
            if (initialBalance < 0)
                throw new IllegalArgumentException("Начальный баланс не может быть отрицательным!");
            if (interestRate < 0)
                throw new IllegalArgumentException("Процентная ставка не может быть отрицательной!");
            this.accountNumber = accountNumber;
            this.ownerName     = ownerName;
            this.balance       = initialBalance;
            this.interestRate  = interestRate;
            this.history       = new ArrayList<>();
            history.add("Сберегательный счёт открыт. Баланс: " + initialBalance +
                    " руб. | Ставка: " + (interestRate * 100) + "%");
        }

        public void applyInterest() {
            double interest = balance * interestRate;
            balance += interest;
            history.add("Начисление процентов: +" + String.format("%.2f", interest) +
                    " руб. | Баланс: " + String.format("%.2f", balance) + " руб.");
            System.out.printf("Начислены проценты: +%.2f руб. Баланс: %.2f руб.%n", interest, balance);
        }

        @Override
        public void deposit(double amount) {
            if (amount <= 0) {
                System.out.println("Сумма пополнения должна быть положительной!");
                return;
            }
            balance += amount;
            history.add("Пополнение: +" + amount + " руб. | Баланс: " + balance + " руб.");
            System.out.println("Пополнение на " + amount + " руб. Баланс: " + balance + " руб.");
        }

        @Override
        public void withdraw(double amount) {
            if (amount <= 0) {
                System.out.println("Сумма снятия должна быть положительной!");
                return;
            }
            if (amount > balance) {
                System.out.println("Недостаточно средств! Баланс: " + balance + " руб.");
                history.add("Отказ в снятии: " + amount + " руб. | Недостаточно средств.");
                return;
            }
            balance -= amount;
            history.add("Снятие: -" + amount + " руб. | Баланс: " + balance + " руб.");
            System.out.println("Снятие " + amount + " руб. Баланс: " + balance + " руб.");
        }

        @Override
        public double getBalance() { return balance; }

        @Override
        public String getAccountNumber() { return accountNumber; }

        @Override
        public List<String> getTransactionHistory() { return history; }

        public String getOwnerName() { return ownerName; }
        public double getInterestRate() { return interestRate; }

        @Override
        public String toString() {
            return "SavingsAccount { номер='" + accountNumber +
                    "', владелец='" + ownerName +
                    "', баланс=" + String.format("%.2f", balance) +
                    " руб., ставка=" + (interestRate * 100) + "% }";
        }
    }

    public static class BankApp {

        static void printHistory(BankAccount account) {
            System.out.println("\n История транзакций [" + account.getAccountNumber() + "]:");
            for (String record : account.getTransactionHistory()) {
                System.out.println("  • " + record);
            }
            System.out.println();
        }

        public static void main(String[] args) {

            System.out.println("========== Стандартный счёт ==========");
            StandardAccount standard = new StandardAccount("ACC-001", "Алексей Иванов", 1000.0);
            System.out.println(standard);
            System.out.println();

            standard.deposit(500.0);
            standard.deposit(-100.0);       // валидация
            standard.withdraw(200.0);
            standard.withdraw(5000.0);      // недостаточно средств
            standard.withdraw(0);           // валидация

            printHistory(standard);

            System.out.println("========== Сберегательный счёт ==========");
            SavingsAccount savings = new SavingsAccount("ACC-002", "Мария Петрова", 2000.0, 0.05);
            System.out.println(savings);
            System.out.println();

            savings.deposit(1000.0);
            savings.applyInterest();        // начисление 5%
            savings.withdraw(500.0);
            savings.applyInterest();        // ещё начисление

            printHistory(savings);

            System.out.println("========== Сводка по всем счетам ==========");
            BankAccount[] accounts = { standard, savings };
            for (BankAccount acc : accounts) {
                System.out.printf("Счёт: %-10s | Баланс: %.2f руб.%n",
                        acc.getAccountNumber(), acc.getBalance());
            }
        }
    }
}
