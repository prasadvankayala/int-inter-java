package hello.streams;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

class ImmutAverage {
    private long count = 0;
    private double sum = 0;

    public ImmutAverage(long count, double sum) {
        this.count = count;
        this.sum = sum;
    }

    public ImmutAverage include(double d) {
        return new ImmutAverage(this.count + 1, this.sum + d);
    }

    public ImmutAverage merge(ImmutAverage other) {
        return new ImmutAverage(this.count + other.count, this.sum + other.sum);
    }

    public Optional<Double> get() {
        if (count != 0) return Optional.of(sum / count);
        else return Optional.empty();
    }
}

public class ImmutAverager {
    public static void main(String[] args) {
        long start = System.nanoTime();
        ThreadLocalRandom.current().doubles(3_000_000_000L, -Math.PI, Math.PI)
                .boxed()
                .parallel()
                .reduce(new ImmutAverage(0, 0), (ia, d) -> ia.include(d), (ia1, ia2) -> ia1.merge(ia2))
                .get()
                .map(a -> "The average is " + a)
                .ifPresent(m -> System.out.println(m));
        long time = System.nanoTime() - start;

        System.out.printf("Time computing %8.5f seconds\n", (time / 1_000_000_000.0));
    }
}
