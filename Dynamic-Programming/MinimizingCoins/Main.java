import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);

  static int[] dp;
  public static void main(String[] args) throws Exception {
    StringBuilder res=new StringBuilder();
    int n=in.nextInt();
    int x=in.nextInt();
    int[] a= new int[n];
    for(int i = 0; i < n; i++) a[i]=in.nextInt();
    dp=new int[x+1];//dp[i]=minimum number of coins required to make sum i
    dp[0]=0;//0 sum can be obtained using 0 coins
    for(int s=1;s<=x;s++){
      dp[s]=(int)1e9;
      for(int i=0;i<n;i++){
        if(a[i]<=s){
          dp[s]=Math.min(dp[s], 1+dp[s-a[i]]);
        }
      }
    }
    res.append((dp[x]==(int)1e9)?-1:dp[x]).append('\n');
    System.out.println(res);
  }
  // private static int solve(int[] a, int x){
  //   if(x==0) return 0;
  //   if(x<0) return Integer.MAX_VALUE;
  //   if(dp[x]!=-1) return dp[x];
  //   int ans=Integer.MAX_VALUE;
  //   for(int i: a){
  //     if(i<=x) ans=Math.min(ans, 1+solve(a, x-i));
  //   }
  //   return dp[x]=ans;
  // }

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