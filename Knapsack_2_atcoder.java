// https://atcoder.jp/contests/dp/tasks/dp_e

//Value-Based DP (your approach)

// dp[v] = min weight required to achieve value v
// Time Complexity: O(n * sum of values)
// Works well when weights are large (like ≤ 1e9), but total value sum is small (like ≤ 1e5)
// Used in problems where W is too large to make weight-based DP feasible

// dp[j] = min(dp[j], dp[j - v[i]] + w[i]);
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=1;
        while(t-->0){
            int n=sc.nextInt();
            int W=sc.nextInt();
            int w[]=new int[n+1];
            int v[]=new int[n+1];
            for(int i=1;i<=n;i++){
                w[i]=sc.nextInt();
                v[i]=sc.nextInt();
            }
            long dp[]=new long[100005];
            Arrays.fill(dp,(long)1e18);
            dp[0]=0;
            for(int i=1;i<=n;i++){
                for(int j=(int)1e5;j>=v[i];j--){
                    dp[j]=Math.min(dp[j],dp[j-v[i]]+w[i]);
                }
            }
            long ans=0;
            for(int i=(int)1e5;i>=0;i--){
                if(dp[i]<=W) ans=Math.max(ans,i);
            }
            System.out.println(ans);

        }
    }
}
