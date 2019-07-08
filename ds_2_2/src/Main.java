import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int size;

        size = s.nextInt();
        MinHeap m = new MinHeap(size);
        for(long i = 0; i < size ; i++){
                long t = s.nextLong();
                m.insert(t);
        }
//        System.out.println("----" + m.size + "-----" + m.existedNumbers);
        long r=0;
        long u;
        long v;
        for(long g =0 ; g<size ; g++){
//            for(int y = 0 ; y<m.existedNumbers ; y++)
//                System.out.println(m.array[y]+" | ");
//            System.out.println();

        u = m.extractMin();
        if(g==size-1)
            v=0;
        else
            v = m.extractMin();
//            System.out.println(u + "," + v);
        r+=u+v;
        m.insert(u+v);
//            System.out.prlongln("-----" + m.existedNumbers);

        }
        System.out.println(r);
    }
}
class MinHeap{
    long [] array;
    long size;
    int existedNumbers;
    public MinHeap(int size){
        array = new long[size];
        this.size=size;
        existedNumbers = 0;
    }
    public void insert(long in){
        existedNumbers++;
        int i = existedNumbers-1;
        array[i] = in;
        while (i != 0 && array[(i-1)/2] > array[i])
        {
            swap(i, (i-1)/2);
            i =(i-1)/2;
        }
    }

    public long extractMin(){
        long r = array[0];
        array[0]=array[existedNumbers -1];
        existedNumbers--;
        size--;
        minHeapify(0);
        return r;
    }
    public void extractMinandSub(long in){
        array[0]=array[0]+in;
        minHeapify(0);
    }

    public void minHeapify(int i){
        int right=2*i+2;
        int left =2*i+1;
        int smallest = i;
        if (left < existedNumbers && array[left] < array[i])
            smallest = left;
        if (right < existedNumbers && array[right] < array[smallest])
            smallest = right;
        if (smallest != i)
        {
            swap(i,smallest);
            minHeapify(smallest);
        }
    }

    public void swap(int x, int y)
    {
        long temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

}

