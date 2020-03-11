import java.text.NumberFormat;
import java.util.regex.*;
import java.util.*;

public class test
{
    public static String sumReciprocals(int n)
    {
        double ReciprocalsSum = 0;
        double i = 1;

        while(i <= n)
        {
            ReciprocalsSum += 1/i;
            i++;
        }

        String answer = String.format("%.4f",ReciprocalsSum);
        return answer;
    }


    public static String makeRepetitions(int n, String s)
    {
//x100 Easier, possibility of getting zero if they say no built in functions
//        String repeated = s.repeat(n);
//        return repeated;
        String Repeated = "";
        int count = 0;

        while (count < n)
        {
            Repeated += s;
            count++;
        }

        return Repeated;
    }

    public static String removePrefix(String s1, String s2)
    {
        return s2.substring(s2.indexOf(s1));
    }

    public static String allPos(String s1, String s2)
    {
        int i = s1.indexOf(s2);
        String IndexValues = "";
        while(i >= 0 )
        {
            IndexValues += i + ",";
            i = s1.indexOf(s2, i+1);
        }
        IndexValues = IndexValues.replaceAll(",$", "");
        return IndexValues;
    }


    public static int findPlateNo(String s)
    {
        String regex = "[A-Za-z]{3}[0-9]{3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(s);
        int count = 0;
        return m.find() ? m.start() : -1;
    }



    public static void main(String[] args)
    {
        System.out.println(test.sumReciprocals(2));
        System.out.println(test.makeRepetitions(3,"abc"));
        System.out.println(test.removePrefix("cde", "abcdefghijk"));
        System.out.println(test.allPos("abcdeabcdacacdf","cd" ));
        System.out.println(test.findPlateNo("The MHD233 and KLTH978."));
    }
}


