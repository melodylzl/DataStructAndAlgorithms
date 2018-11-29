package base.Arrays;

public class MaximumSubArray {

    public static void main(String[] args){
        int[] a = new int[]{1,2,-3,4,5};
        System.out.print(new MaximumSubArray().maxSubArray(a));
    }

    public int maxSubArray(int[] a) {
        if (null == a || a.length == 0){
            return 0;
        }
        int maxEndingHere = a[0];
        int maxSofar = a[0];
        for (int i = 1; i < a.length; ++i){
            maxEndingHere = Math.max(maxEndingHere + a[i],a[i]);
            maxSofar = Math.max(maxSofar,maxEndingHere);
        }
        return maxSofar;
    }
}
