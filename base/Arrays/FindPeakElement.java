package base.Arrays;

public class FindPeakElement {

    public int findPeakElement(int[] a) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high){
            int mid = low + (high - low)/2;
            if (mid > 0 && mid < a.length -1
                    && a[mid] > a[mid-1] && a[mid] > a[mid+1]){
                return mid;
            }
            if (mid < a.length -1 && a[mid] < a[mid + 1]){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return low;
    }
}
