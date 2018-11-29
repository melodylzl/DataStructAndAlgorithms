package base.Arrays;

public class RotateArray {

    public static void main(String[] args){
        int[] a = new int[]{1,2,3,4,5};
        new RotateArray().rotate(a,2);
        for (int i = 0; i < a.length; ++i){
            System.out.print(a[i] + ",");
        }
    }

    public void rotate(int[] a, int k) {
        k %= a.length;
        reverse(a,0,a.length - 1);
        reverse(a,0,k - 1);
        reverse(a,k,a.length - 1);
    }

    public void reverse(int[] a, int low,int high){
        while (low < high){
            int temp = a[low];
            a[low] = a[high];
            a[high] = temp;
            ++low;
            --high;
        }
    }



}
