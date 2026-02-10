import java.util.*;
import java.io.*;

public class Main {

    static FastReader in = new FastReader();
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws Exception {
        StringBuilder res=new StringBuilder();
        String str=in.next();
        int[] fre=new int[26];
        for(int i=0;i<str.length();i++){
            fre[str.charAt(i)-'a']++;
        }
        long total=factorial(str.length());
        for(int i=0;i<str.length();i++){
            if(fre[i]<2) continue;
            total/=factorial(fre[i]);
        }
        char[] c=str.toCharArray();
        StringBuilder cur=new StringBuilder();
        Arrays.sort(c);
        boolean[] vis=new boolean[c.length];
        res.append(total).append('\n');
        solve(res,cur, str,vis, c);
        System.out.println(res);
        out.flush(); // Don't forget to flush output!
    }

    private static void solve(StringBuilder res, StringBuilder cur, String s, boolean[] vis, char[] c){
        if(cur.length()==c.length) {
            res.append(cur).append('\n');
            return;
        }
        for(int i=0;i<c.length;i++){
            if(vis[i]) continue;
            // Skip duplicates: if the same character was available before
            // and we didn't use it, don't use this one either
            if(i>0 && c[i]==c[i-1] && !vis[i-1]) continue;
            vis[i]=true;
            cur.append(c[i]);
            solve(res,cur,s,vis,c);
            cur.deleteCharAt(cur.length()-1);
            vis[i]=false;
        }
    }

    private static long factorial(int n){
        long num=1L;
        while(n>1){
            num*=n;
            n--;
        }
        return num;
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