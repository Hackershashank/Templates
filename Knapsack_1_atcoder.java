//https://atcoder.jp/contests/dp/tasks/dp_d

// Classical (Weight-Based DP)
// dp[i][w] = max value you can get using first i items with total weight ≤ w
  
// Time Complexity: O(n * W)
// Works well when W (max weight capacity) is small (like ≤ 10^5)
// You usually implement with 1D or 2D DP:

// dp[w] = max(dp[w], dp[w - weight[i]] + value[i])
  
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int W=sc.nextInt();
        int wt[]=new int[n];
        long val[]=new long[n];
        for(int i=0;i<n;i++){
            wt[i]=sc.nextInt();
            val[i]=sc.nextLong();
        }
        long prev[]=new long[W+1];
        long curr[]=new long[W+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=W;j++){
                if(j>=wt[i-1]){
                    curr[j]=Math.max(prev[j-wt[i-1]]+val[i-1],prev[j]);
                }
                else curr[j]=prev[j];
            }
            prev=curr.clone();
        }
        System.out.println(prev[W]);
    }
}
