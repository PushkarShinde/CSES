import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);

  public static void main(String[] args) throws Exception {
    int n=in.nextInt(); 
    int m=in.nextInt(); 
    StringBuilder res=new StringBuilder();
    char[][] grid = new char[n][m];
    for(int i=0;i<n;i++){
      grid[i] = in.next().toCharArray();
    }

    int count=0;
    for(int i=0;i<n;i++){
      for(int j=0;j<m;j++){
        if(grid[i][j]=='.'){
          dfs(i,j,grid);
          count++;
        }
      }
    }
    res.append(count);
    System.out.println(res);
    out.flush();
  }

  static int[][] dir={{-1,0},{1,0},{0,1},{0,-1}};
  private static void dfs(int i, int j, char[][] a){
    int n=a.length;
    int m=a[0].length;
    Deque<int[]> st=new ArrayDeque<>();
    st.push(new int[]{i,j});
    a[i][j]='#';
    while(!st.isEmpty()){
      int[] cur=st.pop();
      int x=cur[0];
      int y=cur[1];
      for(int[] d:dir){
        int nx=x+d[0];
        int ny=y+d[1];
        if(nx<0 || ny<0 || nx>=n || ny>=m || a[nx][ny]=='#') continue;
        a[nx][ny]='#';
        st.push(new int[]{nx,ny});
        // dfs(nx,ny,a);
      }
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