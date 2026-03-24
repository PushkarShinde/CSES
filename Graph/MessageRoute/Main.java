import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);

  public static void main(String[] args) throws Exception {
    int n=in.nextInt(); 
    int m=in.nextInt(); 
    StringBuilder res=new StringBuilder();
    List<List<Integer>> adj=new ArrayList<>();
    for(int i=0;i<=n;i++){
      adj.add(new ArrayList<>());
    }
    for(int i=0;i<m;i++){
      int u=in.nextInt();
      int v=in.nextInt();
      adj.get(u).add(v);
      adj.get(v).add(u);
    }
    int[] parent=new int[n+1];
    Queue<Integer> q=new ArrayDeque<>();
    boolean[] vis=new boolean[n+1];
    q.offer(1);
    vis[1]=true;
    int minNodes=0;
    while(!q.isEmpty()){
      int size=q.size();
      minNodes++;
      while(size-->0){
        int u=q.poll();
        if(u==n) {
          res.append(minNodes).append('\n');
          List<Integer> path=new ArrayList<>();
          int node=n;
          while(node>1){
            path.add(node);
            node=parent[node];
          }
          path.add(1);
          Collections.reverse(path);
          for(int i:path){
            res.append(i).append(" ");
          }
          res.append('\n');
          System.out.println(res);
          return;
        }
        for(int v:adj.get(u)){
          if(vis[v]) continue;
          vis[v]=true;
          parent[v]=u;
          q.offer(v);
        }
      }
    }
    res.append("IMPOSSIBLE\n");
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