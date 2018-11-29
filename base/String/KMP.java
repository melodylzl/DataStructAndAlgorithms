package base.String;

public class KMP {

    public static void main(String[] args){
        GetNext("ABCDABD",new int[8]);
    }


    public static void GetNext(String pattern, int next[]) {
        int length = pattern.length();
        int i = 0;   //P的下标
        int j = -1;  //相同前后缀的长度
        next[0] = -1;

        while (i < length)
        {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j))
            {
                i++;
                j++;
                next[i] = j;
            }
            else
                j = next[j];
        }
    }
}
