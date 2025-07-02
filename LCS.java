import java.util.*;

public class Main {
    static String lcs(String s,String t){
        int n=s.length();
        int m=t.length();
        int dp[][]=new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(s.charAt(i-1)==t.charAt(j-1)) dp[i][j]=1+dp[i-1][j-1];
                else dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        StringBuilder sb=new StringBuilder();
        int i=n;
        int j=m;
        while(i>0 && j>0){
            if(s.charAt(i-1)==t.charAt(j-1)){
                sb.append(s.charAt(i-1));
                i--;
                j--;
            }
            else{
                if(dp[i-1][j]>dp[i][j-1]) i--;
                else j--;
            }
        }
        return sb.reverse().toString();
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        String t=sc.next();
        String lcs=lcs(s,t);
        System.out.println(lcs);
    }
}
