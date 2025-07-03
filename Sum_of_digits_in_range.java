import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[][][] dp;  // [l, tight, sum]
    static char[] digits;
    static int digitSum(int pos, int tight, int sum) {
        if (pos == digits.length) return sum;
        if (dp[pos][tight][sum] != -1) return dp[pos][tight][sum];
        int limit = (tight == 1) ? digits[pos] - '0' : 9; // limit depend on the tight
        int res = 0;
        for (int d = 0; d <= limit; d++) {
            int newTight = (tight == 1 && d == limit) ? 1 : 0;   // to be considered
            res += digitSum(pos + 1, newTight, sum + d);
        }
        return dp[pos][tight][sum] = res;
    }
    static int solve(String numStr) {
        digits = numStr.toCharArray();
        // upper bound of possible sum should be defined 
        int maxDigitSum = 9 * digits.length;
        dp = new int[digits.length][2][maxDigitSum + 1];
        for (int[][] row : dp)
            for (int[] col : row)
                Arrays.fill(col, -1);
        return digitSum(0, 1, 0);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //to find the sum of range define lower bound l and find solve(r)-solve(l-1)
        String r = sc.next();
        System.out.println("Sum of digits from 1 to " + r + " is: " + solve(r));
    }
}
