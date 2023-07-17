/*
 * 競プロ典型 90 問
 * 001 - Yokan Party（★4）
 * https://atcoder.jp/contests/typical90/tasks/typical90_a
 *
 * verified:
 * - https://atcoder.jp/contests/typical90/submissions/43698160
 *
 * note:
 * 解で二分探索する
 */

package contests.typical90.typical90_01.typical90_001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final StringTokenizer st = new StringTokenizer(br.readLine());
    final int n = Integer.parseInt(st.nextToken());
    final int l = Integer.parseInt(st.nextToken());
    final int k = Integer.parseInt(br.readLine());
    final StringTokenizer st_a = new StringTokenizer(br.readLine());
    final int[] arr_a = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      arr_a[i] = Integer.parseInt(st_a.nextToken());
    }
    arr_a[0] = 0;
    br.close();
    int ok = 1, ng = (l / (k + 1)) + 1;
    //二分探索
    while (ng - ok > 1) {
      int mid = (ok + ng) / 2;
      int cut = 0, len = 0;
      for (int i = 1; i < n + 1; i++) {
        len += arr_a[i] - arr_a[i - 1];
        if (len >= mid && l - arr_a[i] >= mid) {
          cut++;
          len = 0;
        }
      }
      if (cut >= k) {
        ok = mid;
      } else {
        ng = mid;
      }
    }
    System.out.println(ok);
  }
}
