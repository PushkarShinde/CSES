import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);

  private static List<Integer>[] adj;
  private static long[] up, down, ans, size;
  private static int n;

  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws Exception {
    StringBuilder res=new StringBuilder();
    n=in.nextInt();
    adj=new ArrayList[n+1];
    for(int i=0; i<=n; i++){
      adj[i]=new ArrayList<>();
    }
    for(int i=0; i<n-1; i++){
      int u=in.nextInt();
      int v=in.nextInt();
      adj[u].add(v);
      adj[v].add(u);
    }
    
    down=new long[n+1];
    size=new long[n+1];
    up=new long[n+1];
    ans=new long[n+1];

    dfs1(1, 0);//computing down and size
    dfs2(1, 0);//computing up and ans

    for(int i=1; i<=n; i++){
      res.append(ans[i]).append(" ");
    }
    res.append('\n');
    System.out.println(res);
  }

  //down[u] = sum of distances from u to all nodes in its subtree
  //size[u] = number of nodes in the subtree rooted at u
  private static void dfs1(int u, int p){
    size[u]=1;
    down[u]=0;
    for(int v: adj[u]){
      if(v==p) continue;
      dfs1(v, u);
      size[u]+=size[v];
      down[u]+=down[v]+size[v];
    }
  }

  //up[u] = sum of distances from u to all nodes outside its subtree
  //ans[u] = total distance from u to all other nodes
  private static void dfs2(int u, int p){
    ans[u]=down[u]+up[u];
    for(int v: adj[u]){
      if(v==p) continue;
      //up[v] = up[u] + (down[u] - (down[v]+size[v])) + (n-size[v])
      up[v]=up[u]+(down[u]-(down[v]+size[v]))+(n-size[v]);
      dfs2(v, u);
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