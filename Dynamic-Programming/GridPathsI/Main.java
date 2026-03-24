import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);

  static int[][] dp;
  public static void main(String[] args) throws Exception {
    int n=in.nextInt(); 
    StringBuilder res=new StringBuilder();
    dp=new int[n][n];
    for(int[] d:dp){
      Arrays.fill(d, -1);
    }
    char[][] grid=new char[n][n];
    for(int i=0;i<n;i++){
      grid[i]=in.next().toCharArray();
    }
    if(grid[0][0]=='*'){
      res.append("0\n");
      System.out.println(res);
      return;
    }

    res.append(dfs(grid, 0,0)).append('\n');
    System.out.println(res);
    out.flush();
  }
  static int mod=(int)1e9+7;
  static int[][] dir={{1,0}, {0,1}};
  private static int dfs(char[][] grid, int i, int j){
    int n=grid.length;
    if(i==n-1 && j==n-1 && grid[i][j]!='*') return 1;
    if(dp[i][j]!=-1) return dp[i][j];
    long res=0;
    for(int[] d:dir){
      int nx=i+d[0];
      int ny=j+d[1];
      if(nx>=n || ny>=n || grid[nx][ny]=='*') continue;
      res=(res+(long)dfs(grid, nx, ny))%mod;
    }
    return dp[i][j]=(int)res;
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