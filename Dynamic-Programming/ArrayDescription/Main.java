import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);
  static final int MOD = 1_000_000_007; 
  public static void main(String[] args) throws Exception {
    StringBuilder res=new StringBuilder();

    int mod=(int)1e9+7;
    int n=in.nextInt();
    int m=in.nextInt();
    int[] a=new int[n];
    for(int i=0; i<n; i++) a[i]=in.nextInt();
  
    long[] pre=new long[m+2];
    long[] cur=new long[m+2];
  
    if(a[0]==0){
      for(int i=1;i<=m;i++){
        pre[i]=1;
      }
    }else{
      pre[a[0]]=1;
    }
  
    for(int i=1;i<n;i++){
      Arrays.fill(cur,0);
  
      int val=a[i];
      if(val==0){
        for(int j=1;j<=m;j++){
          cur[j]=(pre[j-1]+pre[j]+pre[j+1])%mod;
        }
      }else{
        cur[val]=(pre[val-1]+pre[val]+pre[val+1])%mod;
      }
  
      long[] temp=cur;
      cur=pre;
      pre=temp;
    }
  
    long result=0;
    for(int i=0;i<=m;i++){
      result=(result+pre[i])%mod;
    }
    res.append(result).append('\n');
    System.out.println(res);
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