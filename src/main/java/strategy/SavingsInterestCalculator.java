package strategy;

public class SavingsInterestCalculator implements InterestCalculator {
    private double interestRate;

    public SavingsInterestCalculator(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public long calculateInterest(long balance) {
        System.out.println("Calculating savings-like interest on "
                + balance + " at " + interestRate + "percent");
        return (balance > 1000) ? (long)(interestRate * balance / 100 / 12) : 0L;
    }
}
