import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);
  private static long mod=(long)1e9+7;
  private static long neg=Long.MIN_VALUE/2;

  public static void main(String[] args) throws Exception {
    String s=in.next();
    vis=new boolean[9][9];
    path=new int[48];

    for(int i=0;i<48;i++){
      char ch=s.charAt(i);
      if(ch=='D') path[i]=0;
      else if(ch=='U') path[i]=1;
      else if(ch=='L') path[i]=2;
      else if(ch=='R') path[i]=3;
      else path[i]=4;
    }

    for (int i = 0; i < 9; i++) {
      vis[0][i] = true;
      vis[8][i] = true;
      vis[i][0] = true;
      vis[i][8] = true;
    }

    System.out.println(solve(1, 1, 0));
  }

  private static boolean[][] vis;
  private static int[] path;
  private static int solve(int x, int y, int ind){
    if(x==7 && y==1){
      return (ind==48)?1:0;
    }

    if(ind==48) return 0;

    boolean up=!vis[x-1][y];
    boolean down=!vis[x+1][y];
    boolean left=!vis[x][y-1];
    boolean right=!vis[x][y+1];

    if(!up && !down && right && left) return 0;
    if(up && down && !right && !left) return 0;

    vis[x][y]=true;
    int res=0;
    if(path[ind]==4 || path[ind]==0){
      if(down) res+=solve(x+1, y, ind+1);
    }
    if(path[ind]==4 || path[ind]==1){
      if(up) res+=solve(x-1, y, ind+1);
    }
    if(path[ind]==4 || path[ind]==2){
      if(left) res+=solve(x, y-1, ind+1);
    }
    if(path[ind]==4 || path[ind]==3){
      if(right) res+=solve(x, y+1, ind+1);
    }
    
    vis[x][y]=false;

    return res;
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

}