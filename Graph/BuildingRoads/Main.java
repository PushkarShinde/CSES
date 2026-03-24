import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);

  private static List<List<Integer>> adj;
  private static boolean[] vis;
  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws Exception {
    int n=in.nextInt(); 
    int m=in.nextInt(); 
    StringBuilder res=new StringBuilder();
    adj=new ArrayList<>();
    for(int i=0;i<=n;i++){
      adj.add(new ArrayList<>());
    }
    for(int i=0;i<m;i++){
      int u=in.nextInt();
      int v=in.nextInt();
      adj.get(u).add(v);
      adj.get(v).add(u);
    }

    List<Integer> components=new ArrayList<>();
    vis=new boolean[n+1];
    for(int i=1;i<=n;i++){
      if(!vis[i]){
        components.add(i);
        dfs(i);
      }
    }

    int comps=components.size();
    res.append(comps-1).append('\n');
    for(int i=1;i<comps;i++){
      res.append(components.get(i-1)).append(" ")
      .append(components.get(i)).append('\n');
    }
    System.out.println(res);
    out.flush();
  }

  private static void dfs(int node){
    vis[node]=true;
    for(int child: adj.get(node)){
      if(vis[child]) continue;
      dfs(child);
    }
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