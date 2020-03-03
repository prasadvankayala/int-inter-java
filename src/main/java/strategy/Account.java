package strategy;

public class Account {
    private InterestCalculator ic;
    private long balance;

    public Account(InterestCalculator ic, long balance) {
        this.ic = ic;
        this.balance = balance;
    }

    public long getBalance() {
        return balance;
    }

    public boolean withdraw(long amount) {
        // verify if permitted
        // withdrawCalc.permitWithdraw(...)
        balance -= amount;
        System.out.println("Withdrawing " + amount);
        System.out.println("Resulting balance " + balance);
        return true;
    }

    public void endOfMonth() {
        long interest = ic.calculateInterest(balance);
        balance += interest;
        System.out.println("Interest amount is " + interest);
        System.out.println("End of month balance is " + this.balance);
    }

    public void setInterestCalculator(InterestCalculator ic) {
        this.ic = ic;
    }
}
