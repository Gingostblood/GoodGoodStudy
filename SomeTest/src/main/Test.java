package main;


import com.sun.deploy.util.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:lezzy
 * @Date:2020/4/15 11:43
 */
public class Test {
//    public static void main(String[] args) {
//        List<String> roles=new ArrayList<>();
//        roles.add("管理员，admin");
//        roles.add("管理员");
//
//    }

    public Timestamp strFormatTotme(String str) {
        String[] array = str.split("-");
        //Timestamp timestamp=Timestamp.valueOf(LocalDateTime.of(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]),0,0,0));
        Timestamp timestamp = Timestamp.valueOf(str);

        return timestamp;
    }

    public static void main(String[] args) {
        Test test = new Test();
        Timestamp timestamp = test.strFormatTotme("2020-02-05 14:20:56");
        System.out.println(timestamp);
    }
}
