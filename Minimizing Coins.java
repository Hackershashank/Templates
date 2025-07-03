import java.util.*;
public class Main {
    static int recursive(int arr[],int n,int i){
        if(i<0){
            if(n==0) return 0;
            else return (int)1e8;
        }
        int take=(int)1e8;
        if(n>=arr[i]) take=recursive(arr,n-arr[i],i)+1;
        int nottake=recursive(arr,n,i-1);
        return Math.min(take,nottake);
    }
    static int memo(int arr[],int n,int i,int dp[][]){
        if(i<0){
            if(n==0) return 0;
            else return (int)1e8;
        }
        if(dp[i][n]!=-1) return dp[i][n];
        int take=(int)1e8;
        if(n>=arr[i]) take=memo(arr,n-arr[i],i,dp)+1;
        int nottake=memo(arr,n,i-1,dp);
        return dp[i][n]=Math.min(take,nottake);
    }
    static int tabulation(int arr[],int amt){
        int n=arr.length;
        int dp[][]=new int[n+1][amt+1];
        for(int i=0;i<=n;i++){
            Arrays.fill(dp[i],(int)1e8);
        }
        for(int i=0;i<=n;i++){
            dp[i][0]=0;
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=amt;j++){
                if(j>=arr[i-1]) dp[i][j]=Math.min(1+dp[i][j-arr[i-1]],dp[i-1][j]);
                else dp[i][j]=dp[i-1][j];
            }
        }
        if(dp[n][amt]==(int)1e8) return -1;
        return dp[n][amt];
    }
    static int spaceOptimised(int arr[],int x){
        int n= arr.length;
        int prev[]=new int[x+1];
        int curr[]=new int[x+1];
        Arrays.fill(prev,(int)1e8);
        for(int i=1;i<=n;i++){
            prev[0]=0;
            for(int j=1;j<=x;j++){
                if(j>=arr[i-1]){
                    curr[j]=Math.min(1+curr[j-arr[i-1]],prev[j]);
                }
                else curr[j]=prev[j];
            }
            prev=curr.clone();
        }
        if(prev[x]!=(int)1e8) return prev[x];
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int x=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int dp[][]=new int[n][x+1];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }
        System.out.println(spaceOptimised(arr,x));
        System.out.println(tabulation(arr,x));
        System.out.println(memo(arr,x,n-1,dp)!=(int)1e8?memo(arr,x,n-1,dp):-1);
        System.out.println(recursive(arr,x,n-1)!=(int)1e8?recursive(arr,x,n-1):-1);
    }
}
