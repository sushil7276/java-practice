import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> num = List.of(11,21,10,111,232,4442,11111111,22323,111111111);
         num.stream().filter(n -> String.valueOf(n).startsWith("1")).forEach(n-> System.out.print(n+" "));


    }
}

