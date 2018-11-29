package base.Sort;

public class QuickSort {

    public static void main(String[] args){
        int[] a = new int[]{8,3,6,1,4};
        sort(a);
        for (int i = 0; i < a.length; ++i){
            System.out.print(a[i] + ",");
        }
    }

    public static void sort(int[] a) {
        sort(a,0,a.length - 1);
    }

    public static void sort(int[] a,int low,int high){
        if (low < high){
            int partition = partition(a,low,high);
            sort(a,low,partition - 1);
            sort(a,partition + 1,high);
        }
    }

    public static int partition(int[] a,int low,int high){
        int pivot = a[low];
        while (low < high){
            while (high > low && a[high] >= pivot){
                --high;
            }
            a[low] = a[high];
            while (low < high && a[low] <= pivot){
                ++low;
            }
            a[high] = a[low];
        }
        a[low] = pivot;
        return low;
    }

}
