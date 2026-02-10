import java.util.*;
import java.io.*;

public class Main {

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);

  public static void main(String[] args) throws Exception {
    StringBuilder res=new StringBuilder();
    int x=in.nextInt();
    int n=in.nextInt();
    TreeSet<Integer> pos=new TreeSet<>();
    pos.add(x);
    pos.add(0);
    TreeMap<Integer, Integer> gaps=new TreeMap<>();
    gaps.put(x, 1);
    for(int i=0;i<n;i++){
      int num=in.nextInt();
      int l=pos.lower(num);
      int r=pos.higher(num);
      removeGaps(gaps, r-l);
      addGaps(gaps, num-l);
      addGaps(gaps, r-num);
      pos.add(num);
      res.append(gaps.lastKey()).append(" ");
    }
    System.out.println(res);
  }
  private static void removeGaps(TreeMap<Integer, Integer> gaps, int gap){
    int count=gaps.get(gap);
    if(count==1){
      gaps.remove(gap);
    }else{
      gaps.put(gap, gaps.get(gap)-1);
    }
  }
  private static void addGaps(TreeMap<Integer, Integer> gaps, int gap){
    gaps.merge(gap, 1, Integer::sum);
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