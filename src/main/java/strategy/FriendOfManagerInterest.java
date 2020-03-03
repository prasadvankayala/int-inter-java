package strategy;

public class FriendOfManagerInterest implements InterestCalculator {
    @Override
    public long calculateInterest(long balance) {
        return (long)(balance * 0.15);
    }
}
