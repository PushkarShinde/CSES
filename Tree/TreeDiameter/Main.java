import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);

  public static void main(String[] args) throws Exception {
    int n=in.nextInt(); 
    StringBuilder res=new StringBuilder();
    Map<Integer, List<Integer>> adj=new HashMap<>();
    for(int i=0;i<n-1;i++){
      int u=in.nextInt();
      int v=in.nextInt();
      adj.computeIfAbsent(u, k-> new ArrayList<>()).add(v);
      adj.computeIfAbsent(v, k-> new ArrayList<>()).add(u);
    }

    boolean[] vis=new boolean[n+1];
    Queue<Integer> q=new LinkedList<>();
    int farthest=0;
    q.offer(1);
    vis[1]=true;
    while(!q.isEmpty()){
      int size=q.size();
      while(size-->0){
        int cur=q.poll();
        // dist++;
        farthest=cur;
        for(int v:adj.getOrDefault(cur, new ArrayList<>())){
          if(!vis[v]){
            q.offer(v);
            vis[v]=true;
          }
        }
      }
    }
    
    int dist=-1;
    Arrays.fill(vis, false);
    q.offer(farthest);
    vis[farthest]=true;
    while(!q.isEmpty()){
      int size=q.size();
      dist++;
      while(size-->0){
        int cur=q.poll();
        // farthest=cur;
        for(int v:adj.getOrDefault(cur, new ArrayList<>())){
          if(!vis[v]){
            q.offer(v);
            vis[v]=true;
          }
        }
      }
    }

    res.append(dist).append('\n');
    
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