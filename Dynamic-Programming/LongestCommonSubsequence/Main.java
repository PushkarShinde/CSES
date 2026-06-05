import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);
  private static long mod=(long)1e9+7;

  public static void main(String[] args) throws Exception {
    StringBuilder res=new StringBuilder();
    int n=in.nextInt();
    int m=in.nextInt();
    long[] a=new long[n];
    for(int i=0; i<n; i++) a[i]=in.nextLong();
    long[] b=new long[m];
    for(int i=0; i<m; i++) b[i]=in.nextLong();

    int[][] dp=new int[n+1][m+1];
    for(int i=1;i<=n;i++){
      for(int j=1;j<=m;j++){
        if(a[i-1]==b[j-1]){
          dp[i][j]=1+dp[i-1][j-1];
        }else{
          dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
        }
      }
    } 

    res.append(dp[n][m]+"\n");
    int i=n, j=m;
    while(i>0 && j>0){
      if(dp[i-1][j-1]+1==dp[i][j]){
        res.append(a[i-1]+" ");
        i--;j--;
        continue;
      }else if(){

      }
    }
    
    System.out.println(res);
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