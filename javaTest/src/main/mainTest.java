package main;

/**
 * @author:lezzy
 * @Date:2020/4/27 9:27
 */

public class mainTest {
    public static void main(String[] args) {
        String a = "2018.06";
        String b = "2016.18.12";
        String c = "2013-12";
        //System.out.println(a);
        String[] splitA = a.split("\\.");
        String[] splitB = b.split("\\.");
        String d = a.replace(".", "");
        //System.out.println(d);
       /* for (String str:splitA){
           // System.out.println(Integer.parseInt(str));
        }
        Integer[] intArry = new Integer[5];
        for (int i = 0; i <splitB.length ; i++) {
            intArry[i]=Integer.parseInt(splitB[i]);
        }
        for (Integer integer:intArry){
            System.out.println(integer);
        }*/
        //doHandlerTimeFormat(b);
        String time="1987.0";
        System.out.println((int)Double.parseDouble(time));

    }

}
