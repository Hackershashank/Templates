class Solution {
    // dp to store the states namely idx currSum tight
    int dp[][][];
    int num[];
    int mx,mn;
    int mod=(int)1e9+7;
    // tight
    // 1 means you are in tight condition and cannot exceed the current max dig
    // 0 means you are free to take any digits
    int solve(int idx,int sum,int tight){
        // base condition
        if(idx==num.length){
            // if sum is in range add 1 to fin_res
            if(sum>=mn && sum<=mx) return 1;
            else return 0;
        }
        // avoid recomputations
        if(dp[idx][sum][tight]!=-1) return dp[idx][sum][tight];
        // deciding the upperbound or limit
        int lim=(tight==0)?9:num[idx];
        int ans=0;
        for(int i=0;i<=lim;i++){
            int nwF=(tight==0)?0:(i<lim?0:1);
            ans+=solve(idx+1,sum+i,nwF);
            if(ans>=mod) ans-=mod;
        }
        return dp[idx][sum][tight]=ans;
    }
    // solving for queries of (L-1) and R
    int solveR(String s){
        int len=s.length();
        num=new int[len];
        for(int i=0;i<len;i++){
            num[i]=s.charAt(i)-'0';
        }
        // states are [len(string) maxSumPossible tight_state]
        dp=new int[len+1][407][2];
        for(int [][] arr2D:dp){
            for(int [] arr1D:arr2D){
                Arrays.fill(arr1D,-1);
            }
        }
        return solve(0,0,1);
    }
    // function to subtract 1 from strings or we can use BigInteger
    String rest(String s){
        char c[]=s.toCharArray();
        int id=s.length()-1;
        while(id>=0 && c[id]=='0'){
            c[id]='9';
            id--;
        }
        // if curr num is 0 then reducing it will give negative hence returned ""
        if(id<0) return "";
        // else c[id]--;
        c[id]--;
        if(c[0]=='0') return new String(c,1,c.length-1);
        return new String(c);
    }
    public int count(String num1, String num2, int min_sum, int max_sum) {
        mx=max_sum;
        mn=min_sum;
        int R=solveR(num2);
        String decre=rest(num1);
        if(decre.isEmpty()) return R;
        int L=solveR(decre);
        R-=L;
        if(R<0) R+=mod;
        return R;        
    }
}
