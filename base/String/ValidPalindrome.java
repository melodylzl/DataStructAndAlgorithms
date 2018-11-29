package base.String;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring
 * cases.
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome. "race a car" is not a palindrome.
 * Note: Have you consider that the string might be empty? This is a good question to ask during an
 * interview.
 * For the purpose of this problem, we define empty string as valid palindrome.
 */
public class ValidPalindrome {

    public static void main(String[] args){
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }

    public static boolean isPalindrome(String s){
        if (s == null || s.isEmpty()){
            return true;
        }
        s = s.toLowerCase();
        int i = 0,j = s.length() -1;
        while (i < j){
            if (!Character.isLetterOrDigit(s.charAt(i))){
                ++i;
            } else if(!Character.isLetterOrDigit(s.charAt(j))){
                --j;
            } else if (s.charAt(i) != s.charAt(j)){
                return false;
            } else{
                ++i;
                --j;
            }
        }
        return true;
    }

}
