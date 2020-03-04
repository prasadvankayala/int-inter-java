package hello.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<T> implements Iterable<T> {
    private Iterable<T> self;

    public SuperIterable(Iterable<T> target) {
        self = target;
    }


    public void forEvery(Consumer<T> op) {
        for (T s : self) {
            op.accept(s);
        }
    }

    public <U> SuperIterable<U> map(Function<T, U> op) {
        List<U> out = new ArrayList<>();
        self.forEach(i -> out.add(op.apply(i)));
        return new SuperIterable<>(out);
    }

    public SuperIterable<T> filter(/*SuperIterable<T> this, */Predicate<T> crit) {
        List<T> out = new ArrayList<>();
        for (T s : self) {
            if (crit.test(s)) {
                out.add(s);
            }
        }
        return new SuperIterable(out);
    }


    @Override
    public Iterator<T> iterator() {
        return self.iterator();
    }
}
