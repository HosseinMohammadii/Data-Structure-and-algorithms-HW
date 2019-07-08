import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String temp;
        boolean f=true;
        int t=0;
        String[] tm;
        int a;
        int n;
        LinkedList<Integer> q = new LinkedList<>();;
        a=s.nextInt();
//        try{a=Integer.parseInt(temp);}
//        catch (Exception e){}
        for(int i=-1;i<a;i++){
            temp= s.nextLine();
            if(temp.contains("enqueue")){
                tm=temp.split(" ");
                n=(Integer.parseInt(tm[1]));
                q.addLast(n);
                f=true;
            }
            else if(temp.contains("pop")) {
                f=false;
                t=q.poll();
                System.out.println(t);
            }
            else if(temp.contains("undo"))
            {
                if(f)
                    q.removeLast();
                else
                    q.addFirst(t);

            }
//            System.out.println(q);

        }
    }

    }
