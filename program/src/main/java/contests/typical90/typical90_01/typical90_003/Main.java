/*
 * 競プロ典型 90 問
 * 003 - Longest Circular Road（★4）
 * https://atcoder.jp/contests/typical90/tasks/typical90_c
 *
 * verified:
 * - https://atcoder.jp/contests/typical90/submissions/43700477
 *
 * note:
 * 木の直径を測る
 * BFSを２回実行する
 *
 */

package contests.typical90.typical90_01.typical90_003;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Main {

  public static void main(String[] args) throws Exception {
    final FastScanner sc = new FastScanner(System.in);
    final int n = sc.nextInt();
    final List<List<Integer>> list_adj = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      list_adj.add(new ArrayList<>());
    }
    for (int i = 0; i < n - 1; i++) {
      int a = sc.nextInt() - 1;
      int b = sc.nextInt() - 1;
      list_adj.get(a).add(b);
      list_adj.get(b).add(a);
    }
    sc.close();
    final int[] dists = new int[n];
    int p1 = bfs(list_adj, 0, dists);
    int p2 = bfs(list_adj, p1, dists);
    System.out.println(dists[p2] + 1);
  }

  //BFSして、startからの最遠点を返す
  private static int bfs(List<List<Integer>> list_adj, int start, int[] dists) {
    Arrays.fill(dists, Integer.MAX_VALUE / 2);
    Queue<Integer> queue = new ArrayDeque<>();
    queue.add(start);
    dists[start] = 0;
    int ret = 0;
    while (!queue.isEmpty()) {
      int v = queue.poll();
      if (dists[v] > dists[ret]) {
        ret = v;
      }
      for (int next : list_adj.get(v)) {
        if (dists[next] > dists[v] + 1) {
          queue.add(next);
          dists[next] = dists[v] + 1;
        }
      }
    }
    return ret;
  }

  // FastScannerライブラリ
  static class FastScanner implements AutoCloseable {

    private final java.io.InputStream in;
    private final byte[] buf = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;

    FastScanner(java.io.InputStream source) {
      this.in = source;
    }

    private boolean hasNextByte() {
      if (ptr < buflen) {
        return true;
      } else {
        ptr = 0;
        try {
          buflen = in.read(buf);
        } catch (java.io.IOException e) {
          e.printStackTrace();
        }
        if (buflen <= 0) {
          return false;
        }
      }
      return true;
    }

    private int readByte() {
      if (hasNextByte()) {
        return buf[ptr++];
      } else {
        return -1;
      }
    }

    private boolean isPrintableChar(final int c) {
      return 33 <= c && c <= 126;
    }

    private boolean isNumeric(final int c) {
      return '0' <= c && c <= '9';
    }

    private void skipToNextPrintableChar() {
      while (hasNextByte() && !isPrintableChar(buf[ptr])) {
        ptr++;
      }
    }

    public boolean hasNext() {
      skipToNextPrintableChar();
      return hasNextByte();
    }

    public String next() {
      if (!hasNext()) {
        throw new java.util.NoSuchElementException();
      }
      StringBuilder ret = new StringBuilder();
      int b = readByte();
      while (isPrintableChar(b)) {
        ret.appendCodePoint(b);
        b = readByte();
      }
      return ret.toString();
    }

    public long nextLong() {
      if (!hasNext()) {
        throw new java.util.NoSuchElementException();
      }
      long ret = 0;
      int b = readByte();
      boolean negative = false;
      if (b == '-') {
        negative = true;
        if (hasNextByte()) {
          b = readByte();
        }
      }
      if (!isNumeric(b)) {
        throw new NumberFormatException();
      }
      while (true) {
        if (isNumeric(b)) {
          ret = ret * 10 + b - '0';
        } else if (b == -1 || !isPrintableChar(b)) {
          return negative ? -ret : ret;
        } else {
          throw new NumberFormatException();
        }
        b = readByte();
      }
    }

    public int nextInt() {
      return (int) nextLong();
    }

    public double nextDouble() {
      return Double.parseDouble(next());
    }

    @Override
    public void close() throws Exception {
      in.close();
    }
  }
}
