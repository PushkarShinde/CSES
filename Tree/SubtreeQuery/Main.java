import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);


  static int[] entry, exit;
  static long[] ft;
  static int timer=0;
  static List<Integer>[] adj;

  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws Exception {
    StringBuilder res=new StringBuilder();
    int n=in.nextInt();
    int q=in.nextInt();
    long[] a=new long[n+1];
    for(int i=1; i<=n; i++) a[i]=in.nextLong();
  
    adj=new ArrayList[n+1];
    entry=new int[n+1];
    exit=new int[n+1];

    for(int i=0;i<=n;i++) adj[i]=new ArrayList<>();

    for(int i=1;i<n;i++){
      int u=in.nextInt();
      int v=in.nextInt();
      adj[u].add(v);
      adj[v].add(u);
    }


    dfs(1,0);

    ft=new long[n+1];

    for(int i=1;i<=n;i++){
      add(entry[i], a[i]);
    }

    while(q-->0){
      int type=in.nextInt();
      if(type==1){        
        int s=in.nextInt();
        long x=in.nextLong();
        long d=x-a[s];
        a[s]=x;
        add(entry[s], d);
      }else{
        int s=in.nextInt();
        long sum=query(exit[s])-query(entry[s]-1);
        res.append(sum).append('\n');
      }
    }
    System.out.println(res);
  }

  static void add(int i, long x){
    while(i<ft.length){
      ft[i]+=x;
      i+=(i & -i);
    }
  }
  static long query(int i){
    long sum=0;
    while(i>0){
      sum+=ft[i];
      i-=(i & -i);
    }
    return sum;
  }

  private static void dfs(int cur, int u){  
    timer++;
    entry[cur]=timer;

    for(int v: adj[cur]){
      if(v==u) continue;
      dfs(v, cur);
    }

    exit[cur]=timer;
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
}