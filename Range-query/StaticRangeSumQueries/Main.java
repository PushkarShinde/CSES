import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);

  static class SegmentTree{
    long[] tree;
    int n;

    SegmentTree(long[] a){
      this.n=a.length;
      this.tree=new long[4*n];
      build(a, 0, n-1, 1);//1-based indexing for the tree
    }

    public void build(long[] a, int l, int r, int node){
      if(l==r){//reached a leaf node
        tree[node]=a[l];
        return;
      }

      int mid=l+(r-l)/2;
      build(a, l, mid, 2*node);
      build(a, mid+1, r, 2*node+1);
      tree[node]=tree[2*node]+tree[2*node+1];
    }

    public long query(int l, int r){
      return query(l, r, 0,n-1,1);
    }

    private long query(int ql, int qr, int l, int r, int node){
      if(qr<l || ql>r) return 0;
      if(l>=ql && r<=qr) return tree[node];

      int mid=l+(r-l)/2;
      long left=query(ql, qr, l,mid,2*node);
      long right=query(ql, qr, mid+1, r,2*node+1);

      return left+right;
    }
    
    // public void update(int ind, int val){
    //   update(ind, val, 0,n-1,1);
    // }

    // private void update(int ind, int val, int l, int r, int node){
    //   if(l==r){
    //     tree[node]=val;
    //     return;
    //   }

    //   int mid=l+(r-l)/2;
    //   if(mid>ind){
    //     update(ind, val, l,mid,2*node);
    //   }else{
    //     update(ind, val, mid+1, r,2*node+1);
    //   }
    //   tree[node]=tree[2*node]+tree[2*node+1];
    // }
  }
  public static void main(String[] args) throws Exception {
    int n=in.nextInt(); 
    int q=in.nextInt(); 
    StringBuilder res=new StringBuilder();
    long[] a=new long[n];
    for(int i=0; i<n; i++) a[i]=in.nextLong();
    SegmentTree st=new SegmentTree(a);
    while(q-->0){
      int l=in.nextInt()-1;
      int r=in.nextInt()-1;
      res.append(st.query(l, r)).append('\n');
    }
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