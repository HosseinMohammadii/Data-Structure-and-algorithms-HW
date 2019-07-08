import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int size;
        size = Integer.parseInt(s.nextLine());
        BinarySearchTree2 tree = new BinarySearchTree2();
        int j = 0;
        for(int i = 0 ; i< size ; i++){
            if(s.nextLong()==1){
                long t = s.nextLong();
                tree.insert_data(t);
                j++;
                }

            else {
                if(j<3)
                    System.out.println("No reviews yet");
                else{
                    System.out.println(tree.get_kth_min2(j-(j/3)+1));
                }
            }
        }
    }
}
class Node{
    public long data;
    public long lCount;
    public long rCount;
    public Node left;
    public Node right;

    public Node(){

        lCount = 0;
        rCount = 0;
        left = null;
        right = null;
    }
    public Node(long data){
        this.data = data;
        lCount = 0;
        left = null;
        right = null;
    }

}

class BinarySearchTree2 {
    Node root ;
    long itr;
    long req;
    boolean flag;
    long kk;
    public BinarySearchTree2(Node root){
        this.root = root;
    }
    public BinarySearchTree2(){
        root = null;
    }


    public void insert_data( long data){
        Node pTraverse = root ;
        Node currentParent = root ;

        while(pTraverse != null){
            currentParent = pTraverse;
            if(data < pTraverse.data){
                pTraverse.lCount=pTraverse.lCount+1;
                pTraverse = pTraverse.left;
            }
            else{
//                pTraverse.rCount=pTraverse.rCount+1;
                pTraverse = pTraverse.right;
            }

        }

        if(root == null)
            root = new Node(data);
        else if ( data < currentParent.data)
            currentParent.left = new Node(data);
        else
            currentParent.right = new Node(data);
//        display(root);
//        System.out.println();
    }

    public long get_kth_min(long k){
        kk = k;
        itr = 0;
        flag = true;
        display2(root);
        return req;
    }

    public long get_kth_min2(long k){
        req = -1;
        kk = k;
        Node pTraverse = root;
        while (pTraverse != null){
            if(pTraverse.lCount+1 == kk){
                req = pTraverse.data;
                break;
            }
            else if( kk > pTraverse.lCount )
            {
                kk = kk - (pTraverse.lCount + 1);
                pTraverse = pTraverse.right;
            }
            else
            {
                pTraverse = pTraverse.left;
            }
        }
        return req;
    }
    public void display2(Node root){
        if(root!=null && flag){
            display2(root.right);
            itr++;
            if(itr==kk){
                req = root.data;
                flag = false;
            }
            display2(root.left);
        }
    }
    public void display(Node root){
        if(root!=null){
            display(root.right);
            System.out.print(" " + root.data);
            display(root.left);
        }
    }
}
