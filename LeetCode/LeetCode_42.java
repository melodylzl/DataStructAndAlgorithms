package LeetCode;

public class LeetCode_42 {


    public static void main(String[] args){
       int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
       System.out.print(new LeetCode_42().trap(height));
    }


    public int trap(int[] height){
        int max = 0,rains = 0;
        for (int i = 0 ; i < height.length; ++i){
            max = Math.max(max,height[i]);
        }
        for (int floor = 1; floor <= max; ++floor){
            int left = 0;
            while (height[left] == 0){
                height[left] = 1;
                ++left;
            }
            int right = height.length - 1;
            while (height[right] == 0){
                height[right] = 1;
                --right;
            }
            for (int i = 0; i < height.length; ++i){
                if (height[i] == 0){
                    ++rains;
                }else{
                    --height[i];
                }
            }
        }
        return rains;
    }

}
