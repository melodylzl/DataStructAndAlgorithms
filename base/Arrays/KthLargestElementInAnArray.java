package base.Arrays;

public class KthLargestElementInAnArray {

    public static void main(String[] args){
        int[] a = new int[]{8,3,6,1,4};
        System.out.print(findKthLargest(a,2));
    }

    public static int findKthLargest(int[] nums, int k) {
        int partition = partition(nums,0,nums.length - 1);
        while (partition != nums.length - k){
            if (partition < nums.length - k){
                partition = partition(nums,partition + 1,nums.length - 1);
            }else{
                partition = partition(nums,0,partition - 1);
            }
        }
        return nums[partition];
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
