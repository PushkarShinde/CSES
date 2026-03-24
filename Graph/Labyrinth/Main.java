import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);

  public static void main(String[] args) throws Exception {
    int n=in.nextInt(); 
    int m=in.nextInt();
    char[][] grid=new char[n][m];
    Queue<int[]> q=new ArrayDeque<>();
    boolean[][] vis = new boolean[n][m];
    for(int i=0;i<n;i++){
      String s=in.next();
      for(int j=0;j<m;j++){
        grid[i][j]=s.charAt(j);
        if(grid[i][j]=='A'){
          q.offer(new int[]{i,j});
          vis[i][j]=true;
        }
      }
    }
    StringBuilder res=new StringBuilder();
    
    int[][] dir={{0,1},{0,-1},{1,0},{-1,0}};
    char[] dc={'R','L','D','U'};
    char[][] parent=new char[n][m];

    while(!q.isEmpty()){
      int[] cur=q.poll();
      int x=cur[0], y=cur[1];
      
      for(int i=0;i<4;i++){
        int nx=x+dir[i][0];
        int ny=y+dir[i][1];
        if(nx<0 || ny<0 || nx>=n || ny>=m || grid[nx][ny]=='#' || vis[nx][ny]) continue;
        vis[nx][ny]=true;
        parent[nx][ny]=dc[i];
        if(grid[nx][ny]=='B') {
          x=nx;
          y=ny;
          StringBuilder path=new StringBuilder();
          while(grid[x][y]!='A'){
            char c=parent[x][y];
            path.append(c);
            if(c=='R') y--;
            else if(c=='L') y++;
            else if(c=='D') x--;
            else if(c=='U') x++;
          }
          path.reverse();
          res.append("YES\n");
          res.append(path.length()).append('\n');
          res.append(path).append('\n');
          System.out.println(res);
          return;
        }
        q.offer(new int[]{nx,ny});
      }
    }

    res.append("NO\n");
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