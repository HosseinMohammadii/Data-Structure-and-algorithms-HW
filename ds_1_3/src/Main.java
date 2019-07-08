import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        Scanner s = new Scanner(System.in);
        int size;
        String temp;
        size = Integer.parseInt(s.nextLine());
        temp = s.nextLine();
        int max=1;
        int t=1;
        char [] chars;
        chars=temp.toCharArray();
        for(int i = 0 ; i< size ; i++){
            if(chars[i]=='('){
                stack.push(i+1);
            }
            else if (chars[i]==')'){
                t=(i+1)-stack.pop();
            }
            if(t>max)
                max=t;
        }

        System.out.println(max);
    }
}
