import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);

  public static void main(String[] args) throws Exception {
    int t=in.nextInt(); 
    StringBuilder res=new StringBuilder();
    int[][] inter=new int[t][3];
    for(int i=0; i<t; i++){
      int l=in.nextInt();
      int r=in.nextInt();
      inter[i][0] = l;
      inter[i][1] = r;
      inter[i][2] = i;
    }
    
    solve(res, inter);
    System.out.println(res);
    out.flush();
  }
  
  static void solve(StringBuilder res, int[][] inter) {
    int n=inter.length;
    if(n==1){
      res.append("0 0\n");
      return;
    }

    Arrays.sort(inter, (x, y) -> {
      if (x[0] != y[0]) return Integer.compare(x[0], y[0]);
      return Integer.compare(y[1], x[1]);
    });

    int[] contains=new int[n];
    int[] contained=new int[n];
    
    int maxR =Integer.MIN_VALUE;
    for(int i=0;i<n;i++){
      int r=inter[i][1];
      int ind=inter[i][2];
      if(r<=maxR){
        contained[ind]=1;
      }
      maxR=Math.max(maxR,r);
    }

    int minR =Integer.MAX_VALUE;
    for(int i=n-1;i>=0;i--){
      int r=inter[i][1];
      int ind=inter[i][2];
      if(r>=minR){
        contains[ind]=1;
      }
      minR=Math.min(minR,r);
    }
    
    for(int i=0;i<n;i++){
      res.append(contains[i]).append(" ");
    }
    res.append("\n");
    for(int i=0;i<n;i++){
      res.append(contained[i]).append(" ");
    }
    res.append("\n");
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