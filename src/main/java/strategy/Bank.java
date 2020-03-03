package strategy;

public class Bank {
    public static void main(String[] args) {
        Account ac = new Account(
                new SavingsInterestCalculator(10), 5000);
        ac.withdraw(3500);
        ac.endOfMonth();
        ac.withdraw(1000);
        ac.endOfMonth();
        ac.setInterestCalculator(new FriendOfManagerInterest());
        ac.endOfMonth();
        ac.endOfMonth();
    }
}
