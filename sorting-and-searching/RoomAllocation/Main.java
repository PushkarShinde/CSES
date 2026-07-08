import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);
  private static long mod=(long)1e9+7;
  private static long neg=Long.MIN_VALUE/2;

  public static void main(String[] args) throws Exception {
    int n=in.nextInt(); 
    StringBuilder res=new StringBuilder();
    
    int[][] a=new int[n][3];
    PriorityQueue<int[]> pq=new PriorityQueue<>((x,y)->x[0]-y[0]);
    for(int i=0;i<n;i++){
      a[i][0]=in.nextInt();
      a[i][1]=in.nextInt();
      a[i][2]=i;
    }
    Arrays.sort(a,(x,y)->x[0]-y[0]);

    int[] result=new int[n];
    int room=0;
    for(int[] i:a){
      if(pq.isEmpty() || i[0]<=pq.peek()[0]){
        room++;
        pq.offer(new int[]{i[1], room});
        result[i[2]]=room;
      }else{
        int[] empty=pq.poll();
        result[i[2]]=empty[1];
        pq.offer(new int[]{i[1], empty[1]});
      }
    }
    out.println(room);
    for(int i:result){
      res.append(i).append(' ');
    }
    out.println(res);
    out.flush();
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