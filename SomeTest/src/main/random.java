package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author:lezzy
 * @Date:2020/5/14 14:14
 */
public class random {
    public static void main(String[] args) {
        Map<Integer,Integer> map=new HashMap<>();
        map.put(3,0);
        map.put(4,0);
        map.put(5,0);
        map.put(6,0);
        for (int i = 0; i < 1000000; i++) {
            Random random=new Random();
            int r = random.nextInt(4) + 3;
            map.put(r,map.containsKey(r)?map.get(r)+1:0);
        }
        System.out.println(map);
    }
}
