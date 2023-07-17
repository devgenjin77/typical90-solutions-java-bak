/*
 * 競プロ典型 90 問
 * 002 - Encyclopedia of Parentheses（★3）
 * https://atcoder.jp/contests/typical90/tasks/typical90_b
 *
 * verified:
 * - https://atcoder.jp/contests/typical90/submissions/43698279
 *
 */

package contests.typical90.typical90_01.typical90_002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

  public static void main(String[] args) throws IOException {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final PrintWriter pw = new PrintWriter(System.out);
    final int n = Integer.parseInt(br.readLine());
    br.close();
    if (n % 2 == 0) {
      for (int bit = 1; bit < (1 << n); bit++) {
        if (Integer.bitCount(bit) != n / 2) {
          continue;
        }
        boolean isOK = true;
        int s = 0;
        for (int i = 0; i < n; i++) {
          if ((bit >> i & 1) == 1) {
            s++;
          } else {
            s--;
          }
          if (s < 0) {
            isOK = false;
            break;
          }
        }
        if (isOK) {
          pw.println(toParentheses(n, bit));
        }
      }
    }
    pw.close();
  }

  private static String toParentheses(int n, int b) {
    StringBuilder sb = new StringBuilder();
    for (int i = n - 1; i >= 0; i--) {
      if ((b >> i & 1) == 0) {
        sb.append('(');
      } else {
        sb.append(')');
      }
    }
    return sb.toString();
  }
}
