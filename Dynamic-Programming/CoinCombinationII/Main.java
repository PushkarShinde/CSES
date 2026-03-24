import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);

  private static int mod=(int)1e9+7;
  public static void main(String[] args) throws Exception {
    int n=in.nextInt(); 
    int x=in.nextInt(); 
    int[] a= new int[n];
    for(int i = 0; i < n; i++) a[i]=in.nextInt();
    int[][] dp=new int[n+1][x+1];
    //dp[i][j]=no. of ways to constuct sum k such that:
    // all coins before coin[i] are unusable and all coins 
    // from i to n-1 are usable

    for(int i=0;i<=n;i++){
      dp[i][0]=1;
    }
    for(int i=n-1;i>=0;i--){
      for(int sum=1;sum<=x;sum++){
        int skip=dp[i+1][sum];
        int pick=0;
        if(a[i]<=sum){
          pick=dp[i][sum-a[i]];
        }
        dp[i][sum]=(skip+pick)%mod;
      }
    }
    System.out.println(dp[0][x]);
    out.flush();
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