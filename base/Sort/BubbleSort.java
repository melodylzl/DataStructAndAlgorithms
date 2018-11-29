package base.Sort;

public class BubbleSort {

    public static void main(String[] args){
        int[] a = new int[]{8,3,6,1,4};
        sort(a);
        for (int i = 0; i < a.length; ++i){
            System.out.print(a[i] + ",");
        }
    }

    public static void sort(int[] a) {
        for (int i = 0; i < a.length; ++i){
            for (int j = 1; j < a.length - i; ++j){
                if (a[j] < a[j -1]){
                    int temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
    }
}
