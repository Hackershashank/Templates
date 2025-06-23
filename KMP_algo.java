import java.util.*;

public class KMP_algo {
    static int[] lps(String s){
        int i=1;
        int len=0;
        int n=s.length();
        int lps[]=new int[n];
        while(i<n){
            if(s.charAt(i)==s.charAt(len)){
                len++;
                lps[i]=len;
                i++;
            }
            else{
                if(len!=0){
                    len=lps[len-1];
                }
                else{
                    lps[i]=0;
                    i++;
                }
            }
        }
        return lps;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        String pat=sc.next();
        int n=s.length();
        int m=pat.length();
        int lps[]=lps(pat);
        int i=0;
        int j=0;
        while(i<n){
            if(s.charAt(i)==pat.charAt(j)){
                i++;
                j++;
            }
            if(j==m){
                System.out.printf("idx: %d\n",(i-j));
                j=lps[j-1];
            }
            else if(i<n && s.charAt(i)!=pat.charAt(j)){
                if(j!=0){
                    j=lps[j-1];
                }
                else i++;
            }
        }
    }
}