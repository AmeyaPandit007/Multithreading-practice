package Test;

import java.util.HashMap;
import java.util.Map;

public class TestMain2 {
    public static void main(String[] args) {
        String name = "Ameya";

        Map<Character,Integer> map = new HashMap<>();

        for(char c : name.toLowerCase().toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c,map.get(c)+1);
            }
            else {
                map.put(c,1);
            }
        }

        int oddCount = 0;
        int evenCount = 0;
        for (Map.Entry<Character,Integer> entry : map.entrySet()) {
            if(entry.getValue()%2==0) {
                evenCount++;
            }
            else {
                oddCount++;
            }
        }

        if(oddCount == 0 || oddCount == 1) {
            System.out.println("Can Make a Palindrome String out of it.!");
        }
        else {
            System.out.println("Cannot Make a Palindrome String out of it.!");
        }
    }
}
