import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);
  private static long mod=(long)1e9+7;
  static long[][] dp=new long[(int)1e6+1][2];
  static{
    dp[1][0]=dp[1][1]=1;
    for(int i=2;i<=(int)1e6;i++){
      dp[i][0]=(2*dp[i-1][0]+dp[i-1][1])%mod;
      dp[i][1]=(4*dp[i-1][1]+dp[i-1][0])%mod;
    }
  }

  public static void main(String[] args) throws Exception {
    int t=in.nextInt();
    StringBuilder res=new StringBuilder();
    
    while(t-->0){
      solve(res);
    }
    System.out.println(res);
    out.flush();
  }

  static void solve(StringBuilder res){
    int n=in.nextInt();
    
    // long vertical=1; // dp[n-1][1]=1;
    // // number of ways to fill the blocks from ith level to the n-1th level
    // // with 2 vertical blocks starting from i-1th row
    
    // long horizontal=1; // dp[n-1][0]=1;
    // // number of ways to fill the blocks from ith level to the n-1th level
    // // with 1 horizontal block starting from i-1th row

    // for(int i=n-2;i>=0;i--){
    //   long newVertical=(4*vertical+horizontal)%mod;
    //   // dp[i][1]=(4*dp[i+1][1]+dp[i+1][0])%mod;
    //   long newHorizontal=(2*horizontal+vertical)%mod;
    //   // dp[i][0]=(2*dp[i+1][0]+dp[i+1][1])%mod;

    //   vertical=newVertical;
    //   horizontal=newHorizontal;
    // }
    // res.append((vertical+horizontal)%mod).append('\n');
    res.append((dp[n][1]+dp[n][0])%mod).append('\n');
  }

    // Fast I/O template
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // GCD
    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // LCM
    static long lcm(long a, long b) {
        return a/gcd(a,b)*b;
    }

    // Sieve of Eratosthenes
    static boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i*i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i*i; j <= n; j += i) isPrime[j] = false;
            }
        }
        return isPrime;
    }

    // Binary Search Template
    static int binarySearch(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l+(r-l)/ 2;
            if (arr[m] == target) return m;
            else if (arr[m] < target) l = m + 1;
            else r = m - 1;
        }
        return -1;
    }

}