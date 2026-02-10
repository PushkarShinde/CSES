import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);

  public static void main(String[] args) throws Exception {
    int n=in.nextInt(); 
    int m=in.nextInt(); 
    int[] a=new int[n+1];
    int[] pos=new int[n+1];
    for(int i=1;i<=n;i++){
        a[i]=in.nextInt();
        pos[a[i]]=i;
    }
    int round=1;
    for(int i=2;i<=n;i++){
        if(pos[i]<pos[i-1]) round++;
    }
    StringBuilder res=new StringBuilder();
    for(int i=0;i<m;i++){
        int x=in.nextInt();
        int y=in.nextInt();
        if(x==y) {
            res.append(round).append('\n');
            continue;
        }
        if(x>y){
            int temp=x;
            x=y;
            y=temp;
        }
        int n1=a[x];
        int n2=a[y];

        Set<Integer> affected=new HashSet<>();
        affected.add(n1);
        affected.add(n1+1);
        affected.add(n2);
        affected.add(n2+1);

        for(int v:affected){
            if(v>=2 && v<=n){
                if(pos[v]<pos[v-1]) round--;
            }
        }

        pos[n1]=y;
        pos[n2]=x;
        a[x]=n2;
        a[y]=n1;

        for(int v:affected){
            if(v>=2 && v<=n){
                if(pos[v]<pos[v-1]) round++;
            }
        }
        res.append(round).append('\n');
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