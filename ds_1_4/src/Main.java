import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        MyMergeSort mms = new MyMergeSort();
        int minIndex;
        int maxIndex;
        int req = 0;
        int a = s.nextInt();
        int x = s.nextInt();
        int[] ar = new int[a];
        for (int i = 0; i < a; i++) {
            ar[i]=s.nextInt();
        }
        mms.sort(ar);
//        for (int i = 0; i < a; i++) {
//            System.out.print(ar[i]+"    ");
//        }
        minIndex = 0;
        maxIndex = a-1;
        while (minIndex<=maxIndex){
            if ((ar[maxIndex] + ar[minIndex]) <= x) {
                minIndex++;
                maxIndex--;
            }
//                System.out.println("--two--"+"min is " + min + " max is " + max + " x is " + x);
            else {
                 maxIndex--;
//                System.out.println("--one--"+"min is " + min + " max is " + max + " x is " + x);
            }
            req++;
//            for(Integer o : ar){
//                System.out.print(o+",,");
//            }
//            System.out.println(" ");
        }
        System.out.println(req);
    }
}

class MyMergeSort {

    private int[] array;
    private int[] tempMergArr;
    private int length;

    public void sort(int inputArr[]) {
        this.array = inputArr;
        this.length = inputArr.length;
        this.tempMergArr = new int[length];
        doMergeSort(0, length - 1);
    }

    private void doMergeSort(int lowerIndex, int higherIndex) {

        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }

    private void mergeParts(int lowerIndex, int middle, int higherIndex) {

        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                array[k] = tempMergArr[i];
                i++;
            } else {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }

    }
}
