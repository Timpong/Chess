import java.util.HashMap;

public class Test {

    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>() {{
            put(1, "value1");
            put(2, "value2");
        }};
        System.out.println(map); 
        System.out.println(map.get(1)); 
    }
}
