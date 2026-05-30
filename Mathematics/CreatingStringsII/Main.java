import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);
  static long mod=(long)1e9+7;
  static long power(long a, long b){
    long ans=1;
    while(b>0){
      if((b&1)==1){
        ans=(ans*a)%mod;
      }
      a=(a*a)%mod;
      b>>=1;
    }
    return ans;
  }
  public static void main(String[] args) throws Exception {
    StringBuilder res=new StringBuilder();
    String s=in.next();
    int n=s.length();

    int[] fre=new int[26];
    for(int i=0;i<s.length();i++){
      fre[s.charAt(i)-'a']++;
    }

    long[] fact=new long[n+1];
    fact[0]=1;
    for(int i=1;i<=n;i++){
      fact[i]=(i*fact[i-1])%mod;
    }
    long[] inverse=new long[n+1];
    inverse[n]=power(fact[n],mod-2);
    for(int i=n-1;i>=0;i--){
      inverse[i]=((i+1)*inverse[i+1])%mod;
    }

    long ans=fact[n];
    for(int i=0;i<26;i++){
      int val=fre[i];
      if(val<=1) continue;
      ans=(ans*inverse[val])%mod;
    }

    res.append(ans+"\n");
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