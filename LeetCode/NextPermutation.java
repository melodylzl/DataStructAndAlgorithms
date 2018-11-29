package LeetCode;

/**
 * 下一个排列
 * 1,2,3-->1,3,2-->2,1,3-->2,3,1-->3,1,2-->3,2,1
 */
public class NextPermutation {

    public static void main(String[] args){
        NextPermutation nextPermutation = new NextPermutation();
        int[] p = new int[]{1,2,3};
        for (int i = 0; i < 6; ++i){
            p = nextPermutation.nextPermutation(p);
            for (int j = 0; j < p.length; ++j){
                System.out.print(p[j]+",");
            }
            System.out.println();
        }
    }

    public int[] nextPermutation(int[] p){
        int length = p.length;
        int i = length - 1;
        for (; i > 0; --i){
            if (p[i-1] < p[i]){
                break;
            }
        }
        if (i == 0){
            reverse(p,0,length - 1);
            return p;
        }
        for (int j = length - 1; j >= i; --j){
            if (p[j] > p[i-1]){
                swap(p,j,i-1);
                reverse(p,i,length-1);
                break;
            }
        }
        return p;
    }

    public void swap(int[] p,int i,int j){
        int temp = p[i];
        p[i] = p[j];
        p[j] = temp;
    }

    public void reverse(int[] p,int start,int end){
        int mid = (start + end)/2;
        for (int i = start; i <= mid; ++i){
            swap(p,i,end - (i - start));
        }
    }




}
