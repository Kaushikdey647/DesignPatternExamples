package com.kaushik.lab.dsa.trie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrieTest {
    private Trie trie;

    @BeforeEach
    void setUp() {
        trie = new Trie();
    }

    @Test
    void emptyTrieFindsNothing() {
        assertFalse(trie.search("a"));
        assertFalse(trie.startsWith("a"));
    }

    @Test
    void searchFindsInsertedWord() {
        trie.insert("apple");
        assertTrue(trie.search("apple"));
    }

    @Test
    void searchMissesNeverInsertedWord() {
        trie.insert("apple");
        assertFalse(trie.search("apply"));
        assertFalse(trie.search("applesauce"));
    }

    @Test
    void prefixIsNotAWordUnlessInserted() {
        trie.insert("apple");
        assertFalse(trie.search("app"));
        assertTrue(trie.startsWith("app"));
        assertTrue(trie.startsWith("apple"));
        assertFalse(trie.startsWith("b"));
    }

    @Test
    void duplicateInsertIsHarmless() {
        trie.insert("apple");
        trie.insert("apple");
        assertTrue(trie.search("apple"));
        assertTrue(trie.delete("apple"));
        assertFalse(trie.search("apple"));
    }

    @Test
    void emptyStringIsAWordOnlyAfterInsert() {
        assertFalse(trie.search(""));
        assertTrue(trie.startsWith(""));
        trie.insert("");
        assertTrue(trie.search(""));
    }

    @Test
    void deleteMissingWordReturnsFalse() {
        trie.insert("apple");
        assertFalse(trie.delete("app"));
        assertFalse(trie.delete("banana"));
        assertTrue(trie.search("apple"));
    }

    @Test
    void deletePrefixWordKeepsLongerWord() {
        trie.insert("app");
        trie.insert("apple");
        assertTrue(trie.delete("app"));
        assertFalse(trie.search("app"));
        assertTrue(trie.search("apple"));
        assertTrue(trie.startsWith("app"));
    }

    @Test
    void deleteLongerWordKeepsPrefixWord() {
        trie.insert("app");
        trie.insert("apple");
        assertTrue(trie.delete("apple"));
        assertFalse(trie.search("apple"));
        assertTrue(trie.search("app"));
    }

    @Test
    void deletePrunesBranch() {
        trie.insert("apple");
        assertTrue(trie.delete("apple"));
        assertFalse(trie.startsWith("a"));
    }
}
