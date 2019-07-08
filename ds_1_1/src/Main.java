import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String temp;
        String ans = "";
        String[] tm;
        int a;
        int n;
        int min=0;
//        ArrayList<Integer> stack = new ArrayList<>(200000);
        Stack stack = new Stack();
        Stack stack2 = new Stack();
        a=s.nextInt();
//        try{a=Integer.parseInt(temp);}
//        catch (Exception e){}
        for(int i=-1;i<a;i++){
            temp= s.nextLine();
            if(temp.contains("push")){
                tm=temp.split(" ");
                n=(Integer.parseInt(tm[1]));
                if(stack.isEmpty()){
                    stack2.push(n);
                }
                else if(n<=stack2.peek()){
                    stack2.push(n);
                }
                stack.push(n);
            }
            else if(temp.contains("pop"))
            {
//                if(stack.isEmpty())
//                ans=ans+"stack is empty\n";
                int t=stack.pop();
                if(t==stack2.peek())
                    stack2.pop();}
            else if(temp.contains("spell"))
            {
//                if(stack.isEmpty())
//                ans=ans+"stack is empty\n";
//            else
//                ans=ans+stack2.peek()+"\n";
                System.out.println(stack2.peek());
            }

    }
//        System.out.print(ans);
}

static class Stack{
    int[] a = new int[200000];
    int length;
    public Stack(){
        length=0;
    }
    public void push(int n){
        a[length]=n;
        length++;
    }
    public int pop(){
        length--;
        return a[length];
    }
    public int peek(){
        return a[length-1];
    }
    public boolean isEmpty(){
        return length==0;
    }

}
}

