package LeetCode;

public class LeetCode_34 {

    public static void main(String[] args){
       int[] result = new LeetCode_34().searchRange(
               new int[]{5,6,7,8,8,10},8);
       for (int i = 0; i < result.length; ++i){
           System.out.print(result[i] + ",");
       }
    }

    public int[] searchRange(int[] nums,int target){
        int[] result = {-1,-1};
        int length = nums.length;
        int left = 0,right = length -1;
        while (left <= right){
            int mid = (left + right) / 2;
            if (nums[mid] < target){
               left = mid + 1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else{
                left = right = mid;
                while (nums[--left] == target && left > 0);
                while (nums[++right] == target && right < length - 1);
                result[0] = left + 1;
                result[1] = right - 1;
                break;
            }
        }
        return result;
    }

}
