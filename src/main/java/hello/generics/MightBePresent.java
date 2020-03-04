package hello.generics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MightBePresent {
    public static void main(String[] args) {
        Map<String, String> names = new HashMap<>();
        names.put("Fred", "Jones");

        String FIRSTNAME = "Freddy";

        String lastname = names.get(FIRSTNAME);
        if (lastname != null) {
            String message1 = lastname.toUpperCase();
            String messageFinal = "Dear " + message1;

            System.out.println(messageFinal);
        }
        new SuperIterable<>(Arrays.asList(names))
                .mapNull(m -> m.get(FIRSTNAME))
                .mapNull(s -> s.toUpperCase())
                .mapNull(s -> "Dear " + s)
                .forEach(s -> System.out.println(s));

        Optional.of(names)
                .map(m -> m.get(FIRSTNAME))
                .map(s -> s.toUpperCase())
                .map(s -> "Dear " + s)
                .ifPresent(s -> System.out.println(s));
    }
}
