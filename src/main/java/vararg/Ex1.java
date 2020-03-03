package vararg;

public class Ex1 {
    public static void showAll(String ... sa) {
        for (String s : sa) {
            System.out.println("> " + s);
        }
    }

    public static void main(String[] args) {
        String [] names = {"Fred", "Jim", "Sheila"};
        showAll(names);
        System.out.println("-----------------");
        showAll("Alan", "Susan", "Mary");
        System.out.println("-----------------");
        names[0] = "Dr. Evil";
        showAll(names);
    }
}
