package com.kaushik.lab.dsa.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Prefix tree over arbitrary chars. L = word length.
 */
public class Trie {

    private final TrieNode root = new TrieNode();

    /** O(L) time. Duplicate inserts are no-ops. */
    public void insert(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            cur = cur.children.computeIfAbsent(c, k -> new TrieNode());
        }
        cur.isEnd = true;
    }

    /** O(L) time. True only if the exact word was inserted. */
    public boolean search(String word) {
        TrieNode node = find(word);
        return node != null && node.isEnd;
    }

    /** O(L) time. True if any inserted word starts with prefix. */
    public boolean startsWith(String prefix) {
        return find(prefix) != null;
    }

    /** O(L) time. Returns false if the word was not present. Prunes branches left empty. */
    public boolean delete(String word) {
        TrieNode node = find(word);
        if (node == null || !node.isEnd) {
            return false;
        }
        node.isEnd = false;
        delete(root, word, 0);
        return true;
    }

    // Returns true if the caller should remove this node from its parent.
    private boolean delete(TrieNode node, String word, int depth) {
        if (depth == word.length()) {
            return !node.isEnd && node.children.isEmpty();
        }
        char c = word.charAt(depth);
        TrieNode child = node.children.get(c);
        if (delete(child, word, depth + 1)) {
            node.children.remove(c);
        }
        return !node.isEnd && node.children.isEmpty() && node != root;
    }

    private TrieNode find(String s) {
        TrieNode cur = root;
        for (char c : s.toCharArray()) {
            cur = cur.children.get(c);
            if (cur == null) {
                return null;
            }
        }
        return cur;
    }
}
