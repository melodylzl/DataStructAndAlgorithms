package base.Arrays;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

    public static void main(String[] args){
        int[] a = new int[]{1,2,3,3,5};
        System.out.println(new ContainsDuplicate().containsDuplicate(a));
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0 ; i < nums.length; ++i){
            if (!set.add(nums[i])){
                return true;
            }
        }
        return false;
    }

}
