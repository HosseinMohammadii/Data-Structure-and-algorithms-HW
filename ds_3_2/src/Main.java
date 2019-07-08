import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String text = s.nextLine();
        System.out.println(findMin(text,0,text.length()-1));

    }

    public static String findMin(String text, int f, int l){
        if( l<f )
            return null;
        if( f == l)
            return String.valueOf(text.charAt(f));
        if( l == f+1) {
            if (text.charAt(l) == text.charAt(f))
                return text.substring(f,l+1);
        }
        if (text.charAt(l) == text.charAt(f)){
            String t = String.valueOf(text.charAt(f));
            return t+findMin(text,f+1,l-1)+t;
        }
        else{
            String t1 = text.charAt(f)+findMin(text,f+1,l)+text.charAt(f);
            String t2 = text.charAt(l)+findMin(text,f,l-1)+text.charAt(l);
            if(t1.length()<t2.length())
                return t1;
            else if( t2.length()<t1.length())
                return t2;
            else
                return alphabet_priority(t1,t2);
        }
    }


    public static String alphabet_priority(String s1, String s2){
        int su1=0,su2 =0;
        int length = s1.length();
        for (int i = 0 ; i< length ; i++){
            su1+= (int)s1.charAt(i);
            su2+= (int)s2.charAt(i);
            if(su1<su2)
                return s1;
            if(su2<su1)
                return s2;
        }
        return s1;
    }
}
