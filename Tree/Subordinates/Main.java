import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);

  static List<List<Integer>> list;
  static int[] ans;
  public static void main(String[] args) throws Exception {
    StringBuilder res=new StringBuilder();
    int n=in.nextInt();
    list=new ArrayList<>();
    for(int i=0;i<=n;i++){
      list.add(new ArrayList<>());
    }
    for(int i=2;i<=n;i++){
      int boss=in.nextInt();
      list.get(boss).add(i);
    }
    ans=new int[n+1];

    dfs(1);

    for(int i=1;i<=n;i++){
      res.append(ans[i]).append(' ');
    }
    res.append('\n');
    System.out.println(res);
  }

  private static void dfs(int boss){
    Stack<Integer> stack=new Stack<>();
    Stack<Integer> order=new Stack<>();
    stack.push(boss);
    while(!stack.isEmpty()){
      int node=stack.pop();
      order.push(node);
      for(int sub:list.get(node)){
        stack.push(sub);
      }
    }
    while(!order.isEmpty()){
      int node=order.pop();
      for(int sub:list.get(node)){
        ans[node]+=1+ans[sub];
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
}