package hello.streams;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;


class Average {
    private long count = 0;
    private double sum = 0;

    public Average() {}

    public Average(long count, double sum) {
        this.count = count;
        this.sum = sum;
    }

    public void include(double d) {
        this.count += 1;
        this.sum += d;
    }

    public void merge(Average other) {
        this.count += other.count;
        this.sum += other.sum;
    }

    public Optional<Double> get() {
        if (count != 0) return Optional.of(sum / count);
        else return Optional.empty();
    }

    private static Collector<Double, Average, Average> collectorInstance = new MyAverager();

    public static Collector<Double, Average, Average> getAveragingCollector() {
        return collectorInstance;
    }

    private static class MyAverager implements Collector<Double, Average, Average> {

        @Override
        public Supplier<Average> supplier() {
            return () -> new Average();
        }

        @Override
        public BiConsumer<Average, Double> accumulator() {
            return (a, d) -> a.include(d);
        }

        @Override
        public BinaryOperator<Average> combiner() {
            return (a1, a2) -> new Average(a1.count + a2.count, a1.sum + a2.sum);
        }

        @Override
        public Function<Average, Average> finisher() {
            return null;
        }

        @Override
        public Set<Characteristics> characteristics() {
            Set<Characteristics> res = new HashSet<>();
            res.add(Characteristics.IDENTITY_FINISH);
            res.add(Characteristics.UNORDERED);
            return res;
        }
    }
}

public class MutatingAverager {
    public static void main(String[] args) {
        long start = System.nanoTime();
        ThreadLocalRandom.current().doubles(6_000_000_000L, -Math.PI, Math.PI)
                .boxed()
                .parallel()
//                .collect(
////                        () -> new Average(),
//                        Average::new,
////                        (a, d) -> a.include(d),
//                        Average::include,
////                        (a1, a2) -> a1.merge(a2)
//                        Average::merge
//                )
                .collect(Average.getAveragingCollector())
                .get()
                .map(a -> "The average is " + a)
                .ifPresent(m -> System.out.println(m));
        long time = System.nanoTime() - start;

        System.out.printf("Time computing %8.5f seconds\n", (time / 1_000_000_000.0));
    }
}
