package com.kaushik.lab.dsa.problems;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 139. Word Break (Medium)
 * https://leetcode.com/problems/word-break/
 * Topics: DP, Hash Table, Trie
 *
 * Bottom-up DP: canBreak[i] = s[0..i) is segmentable.
 * O(n^2) substring checks (each O(n) to hash), O(n + total dict size) space.
 */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        boolean[] canBreak = new boolean[s.length() + 1];
        canBreak[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (canBreak[j] && words.contains(s.substring(j, i))) {
                    canBreak[i] = true;
                    break;
                }
            }
        }
        return canBreak[s.length()];
    }
}
