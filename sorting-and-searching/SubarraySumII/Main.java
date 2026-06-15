import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);
  private static long mod=(long)1e9+7;
  private static long neg=Long.MIN_VALUE/2;

  public static void main(String[] args) throws Exception {
    StringBuilder res=new StringBuilder();
    int n=in.nextInt();
    int x=in.nextInt(); 
    long[] a=new long[n];
    for(int i=0; i<n; i++) a[i]=in.nextLong();
    
    // long[] pre=new long[n+1];
    // for(int i=1;i<=n;i++){
    //   pre[i]=pre[i-1]+a[i-1];
    // }

    Map<Long, Long> map=new HashMap<>();
    map.put(0L,1L);
    long pre=0;
    long count=0;
    for(int i=0;i<n;i++){
      pre+=a[i];
      if(map.containsKey(pre-x)){
        count+=map.get(pre-x);
      }
      map.put(pre, map.getOrDefault(pre, 0L)+1);
    }
    
    res.append(count);
    System.out.println(res);
  }

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

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static long lcm(long a, long b) {
        return a/gcd(a,b)*b;
    }

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