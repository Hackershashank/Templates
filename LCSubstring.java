import java.util.*;

public class Main {
    static String lcs(String s,String t){
        int n=s.length();
        int m=t.length();
        int dp[][]=new int[n+1][m+1];
        int max=0;
        int end=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(s.charAt(i-1)==t.charAt(j-1)){
                    dp[i][j]=1+dp[i-1][j-1];
                    if(max<=dp[i][j]){
                        max=dp[i][j];
                        end=i;
                    }
                }
            }
        }
        return s.substring(end-max,end);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        String t=sc.next();
        String lcs=lcs(s,t);
        System.out.println(lcs);
    }
}
