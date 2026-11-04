package com.kaushik.lab.dsa.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    final Map<Character, TrieNode> children = new HashMap<>();
    boolean isEnd;
}
