import java.util.*;

public class Main{
    static int solve(String num,int n,int x,int tight,int dp[][][]){
        // if sum<0 return 0 as number number can have negative sum
        if (x < 0) return 0;
        if(n==1){
            if(x>=0 && x<=9) return 1;
            else return 0;
        }
        if(dp[n][x][tight]!=-1) return dp[n][x][tight];
        int ans=0;
        int ub=9;
        //if tight==1 then the number at that position should not be exceeded
        //else upto 9 can be taken 
        if(tight==1) ub=(num.charAt(num.length()-n)-'0');
        for(int dig=0;dig<=ub;dig++){
            if(dig==ub) ans+=solve(num,n-1,x-dig,1,dp);
            else ans+=solve(num,n-1,x-dig,0,dp);
        }
        return dp[n][x][tight]=ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // this is for the count of number whose sum of digits==x 
        // to find for a range [l,r] do solve(r)-solve(l-1)
        String s=sc.next();
        int sum=sc.nextInt();
        int n=s.length();
        int dp[][][]=new int[n+1][sum+1][2];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=sum;j++){
                Arrays.fill(dp[i][j],-1);
            }
        }
        System.out.println(solve(s,n,sum,1,dp));

    }
}
