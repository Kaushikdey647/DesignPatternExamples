## C++ to Java Mapping

| C++ structure | Java structure | Comments |
|---|---|---|
| `vector<T>` | `ArrayList<T>` | vectors/arrays |
| `unordered_map<K,V>` | `HashMap<K,V>` | hash map |
| `map<K,V>` | `TreeMap<K,V>` | balanced BST (red-black) map |
| `unordered_set<T>` | `HashSet<T>` | hash set |
| `stack<T>` / `queue<T>` | `ArrayDeque<T>` | avoid legacy `Stack`/`Queue`; use `ArrayDeque` for both |
| `priority_queue<T>` | `PriorityQueue<T>` | **min** heap by default in Java (C++ is max) |
| `string` (mutable ops) | `StringBuilder` | Java `String` is immutable; use `StringBuilder` for building/editing |

---

## ArrayList\<T\>

Resizable array. O(1) random access, amortized O(1) append, O(n) insert/remove in the middle.

| Method | Purpose | Time |
|---|---|---|
| `add(e)` / `add(i, e)` | append / insert at index | O(1) amortized / O(n) |
| `get(i)` / `set(i, e)` | read / overwrite | O(1) |
| `remove(i)` / `remove(Object)` | remove by index / by value | O(n) |
| `size()`, `isEmpty()`, `clear()` | basics | O(1) |
| `contains(e)`, `indexOf(e)` | linear search | O(n) |
| `subList(from, to)` | view of range | O(1) |
| `Collections.sort(list)` / `list.sort(cmp)` | sort | O(n log n) |

**Use when:** index access, ordered results, building answer lists, backtracking paths.
**Gotcha:** `list.remove(Integer.valueOf(x))` removes by value; `list.remove(x)` with `int` removes by index.

**LeetCode**
- [78. Subsets](https://leetcode.com/problems/subsets/) – backtracking: `List<Integer>` path, copy with `new ArrayList<>(path)` into `List<List<Integer>>`.
```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    backtrack(nums, 0, new ArrayList<>(), res);
    return res;
}
private void backtrack(int[] nums, int start, List<Integer> path, List<List<Integer>> res) {
    res.add(new ArrayList<>(path));
    for (int i = start; i < nums.length; i++) {
        path.add(nums[i]);
        backtrack(nums, i + 1, path, res);
        path.remove(path.size() - 1);
    }
}
```
- [283. Move Zeroes](https://leetcode.com/problems/move-zeroes/) – in-place array/list two-pointer.
```java
public void moveZeroes(int[] nums) {
    int w = 0;
    for (int x : nums) if (x != 0) nums[w++] = x;
    while (w < nums.length) nums[w++] = 0;
}
```

---

## HashMap\<K,V\>

Hash table. O(1) average get/put/remove. No ordering.

| Method | Purpose |
|---|---|
| `put(k, v)` / `get(k)` | insert / read (`null` if absent) |
| `getOrDefault(k, d)` | read with default |
| `containsKey(k)` / `containsValue(v)` | membership (O(1) / O(n)) |
| `putIfAbsent(k, v)` | insert only if missing |
| `computeIfAbsent(k, fn)` | lazy create, e.g. `map.computeIfAbsent(k, x -> new ArrayList<>()).add(v)` |
| `merge(k, 1, Integer::sum)` | counting / accumulation |
| `remove(k)`, `size()` | basics |
| `keySet()`, `values()`, `entrySet()` | iteration |

**Use when:** frequency count, complement lookup, grouping, prefix-sum → index, memoization.

**LeetCode**
- [1. Two Sum](https://leetcode.com/problems/two-sum/) – store `value → index`, look up `target - x`.
```java
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> seen = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        Integer j = seen.get(target - nums[i]);
        if (j != null) return new int[]{j, i};
        seen.put(nums[i], i);
    }
    return new int[0];
}
```
- [49. Group Anagrams](https://leetcode.com/problems/group-anagrams/) – sorted-string key → `computeIfAbsent` list.
```java
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> groups = new HashMap<>();
    for (String s : strs) {
        char[] c = s.toCharArray();
        Arrays.sort(c);
        groups.computeIfAbsent(new String(c), k -> new ArrayList<>()).add(s);
    }
    return new ArrayList<>(groups.values());
}
```
- [560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) – prefix-sum frequency map.
```java
public int subarraySum(int[] nums, int k) {
    Map<Integer, Integer> freq = new HashMap<>();
    freq.put(0, 1);
    int sum = 0, count = 0;
    for (int x : nums) {
        sum += x;
        count += freq.getOrDefault(sum - k, 0);
        freq.merge(sum, 1, Integer::sum);
    }
    return count;
}
```

---

## TreeMap\<K,V\>

Red-black tree, keys sorted. O(log n) for all ops.

| Method | Purpose |
|---|---|
| `put`, `get`, `remove`, `containsKey` | as `HashMap`, O(log n) |
| `firstKey()` / `lastKey()` | min / max key |
| `firstEntry()` / `lastEntry()` | min / max entry |
| `pollFirstEntry()` / `pollLastEntry()` | remove and return min / max |
| `floorKey(k)` / `ceilingKey(k)` | greatest ≤ k / smallest ≥ k |
| `lowerKey(k)` / `higherKey(k)` | greatest < k / smallest > k |
| `headMap(k)` / `tailMap(k)` / `subMap(a, b)` | range views |
| `descendingMap()` | reverse order view |

**Use when:** need sorted keys, nearest-key queries, interval/calendar problems, sliding-window with order statistics.

**LeetCode**
- [729. My Calendar I](https://leetcode.com/problems/my-calendar-i/) – `floorKey` / `ceilingKey` to check overlap.
```java
class MyCalendar {
    private final TreeMap<Integer, Integer> cal = new TreeMap<>(); // start -> end
    public boolean book(int start, int end) {
        Integer prev = cal.floorKey(start);
        Integer next = cal.ceilingKey(start);
        if (prev != null && cal.get(prev) > start) return false;
        if (next != null && next < end) return false;
        cal.put(start, end);
        return true;
    }
}
```
- [220. Contains Duplicate III](https://leetcode.com/problems/contains-duplicate-iii/) – `TreeSet`/`TreeMap` ceiling/floor in window.
```java
public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
    TreeSet<Long> window = new TreeSet<>();
    for (int i = 0; i < nums.length; i++) {
        long x = nums[i];
        Long c = window.ceiling(x - valueDiff);
        if (c != null && c <= x + valueDiff) return true;
        window.add(x);
        if (i >= indexDiff) window.remove((long) nums[i - indexDiff]);
    }
    return false;
}
```
- [981. Time Based Key-Value Store](https://leetcode.com/problems/time-based-key-value-store/) – `floorEntry(timestamp)`.
```java
class TimeMap {
    private final Map<String, TreeMap<Integer, String>> store = new HashMap<>();
    public void set(String key, String value, int timestamp) {
        store.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
    }
    public String get(String key, int timestamp) {
        TreeMap<Integer, String> m = store.get(key);
        if (m == null) return "";
        Map.Entry<Integer, String> e = m.floorEntry(timestamp);
        return e == null ? "" : e.getValue();
    }
}
```

---

## HashSet\<T\>

Hash-based unique set. O(1) average add/contains/remove.

| Method | Purpose |
|---|---|
| `add(e)` | returns `false` if already present (handy for dup detection) |
| `contains(e)` | membership |
| `remove(e)`, `size()`, `isEmpty()`, `clear()` | basics |
| `addAll`, `retainAll`, `removeAll` | union / intersection / difference |

**Use when:** dedupe, visited tracking, O(1) existence checks, sequence/streak detection.
**Variants:** `LinkedHashSet` (insertion order), `TreeSet` (sorted, O(log n), has `floor/ceiling/first/last`).

**LeetCode**
- [217. Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) – `if (!set.add(x)) return true`.
```java
public boolean containsDuplicate(int[] nums) {
    Set<Integer> seen = new HashSet<>();
    for (int x : nums) if (!seen.add(x)) return true;
    return false;
}
```
- [128. Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) – start counting only from `x` where `x-1 ∉ set`.
```java
public int longestConsecutive(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int x : nums) set.add(x);
    int best = 0;
    for (int x : set) {
        if (set.contains(x - 1)) continue;
        int len = 1;
        while (set.contains(x + len)) len++;
        best = Math.max(best, len);
    }
    return best;
}
```
- [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) – sliding window + set.
```java
public int lengthOfLongestSubstring(String s) {
    Set<Character> window = new HashSet<>();
    int l = 0, best = 0;
    for (int r = 0; r < s.length(); r++) {
        while (!window.add(s.charAt(r))) window.remove(s.charAt(l++));
        best = Math.max(best, r - l + 1);
    }
    return best;
}
```

---

## ArrayDeque\<T\> (stack, queue, deque)

Resizable circular array. O(1) at both ends. Faster than `Stack`/`LinkedList`. **No `null` elements.**

| Role | Methods |
|---|---|
| Stack (LIFO) | `push(e)`, `pop()`, `peek()` |
| Queue (FIFO) | `offer(e)` / `add(e)`, `poll()`, `peek()` |
| Deque | `offerFirst/Last`, `pollFirst/Last`, `peekFirst/Last` |
| Common | `isEmpty()`, `size()`, `clear()` |

Don't mix: `push/pop/peek` act on the **head**; `offer/poll` add at **tail**, remove from **head**.
`pop()`/`remove()` throw on empty; `poll()`/`peek()` return `null`.

**Use when:**
- Stack: matching brackets, monotonic stack (next greater), DFS iterative, expression evaluation.
- Queue: BFS, level-order, multi-source BFS, topological sort (Kahn).
- Deque: monotonic deque for sliding-window max/min.

**LeetCode**
- [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) – stack.
```java
public boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char c : s.toCharArray()) {
        if (c == '(') stack.push(')');
        else if (c == '[') stack.push(']');
        else if (c == '{') stack.push('}');
        else if (stack.isEmpty() || stack.pop() != c) return false;
    }
    return stack.isEmpty();
}
```
- [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) – monotonic stack.
```java
public int[] dailyTemperatures(int[] temps) {
    int[] res = new int[temps.length];
    Deque<Integer> stack = new ArrayDeque<>(); // indices, temps decreasing
    for (int i = 0; i < temps.length; i++) {
        while (!stack.isEmpty() && temps[stack.peek()] < temps[i]) {
            int j = stack.pop();
            res[j] = i - j;
        }
        stack.push(i);
    }
    return res;
}
```
- [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/) – queue BFS.
```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) return res;
    Queue<TreeNode> q = new ArrayDeque<>();
    q.offer(root);
    while (!q.isEmpty()) {
        int n = q.size();
        List<Integer> level = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            TreeNode node = q.poll();
            level.add(node.val);
            if (node.left != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }
        res.add(level);
    }
    return res;
}
```
- [239. Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) – monotonic deque.
```java
public int[] maxSlidingWindow(int[] nums, int k) {
    int[] res = new int[nums.length - k + 1];
    Deque<Integer> dq = new ArrayDeque<>(); // indices, values decreasing
    for (int i = 0; i < nums.length; i++) {
        if (!dq.isEmpty() && dq.peekFirst() <= i - k) dq.pollFirst();
        while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) dq.pollLast();
        dq.offerLast(i);
        if (i >= k - 1) res[i - k + 1] = nums[dq.peekFirst()];
    }
    return res;
}
```

---

## PriorityQueue\<T\>

Binary heap. **Min-heap by default.** O(log n) `offer`/`poll`, O(1) `peek`, O(n) `contains`/`remove(Object)`.

| Method | Purpose |
|---|---|
| `offer(e)` / `add(e)` | insert |
| `poll()` | remove & return top (`null` if empty) |
| `peek()` | view top |
| `size()`, `isEmpty()` | basics |

Max-heap / custom order:
```java
new PriorityQueue<>(Collections.reverseOrder());
new PriorityQueue<>((a, b) -> b[0] - a[0]);          // int[]; prefer Integer.compare(b[0], a[0]) to avoid overflow
new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
```
Iteration order is **not** sorted; only `poll()` yields order.

**Use when:** top-K, k-way merge, running median (two heaps), scheduling, Dijkstra, greedy "pick best next".
**Top-K trick:** keep a min-heap of size K for K largest (and vice versa).

**LeetCode**
- [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) – min-heap of size k.
```java
public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> heap = new PriorityQueue<>();
    for (int x : nums) {
        heap.offer(x);
        if (heap.size() > k) heap.poll();
    }
    return heap.peek();
}
```
- [23. Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) – heap of list heads.
```java
public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
    for (ListNode n : lists) if (n != null) heap.offer(n);
    ListNode dummy = new ListNode(), tail = dummy;
    while (!heap.isEmpty()) {
        ListNode n = heap.poll();
        tail.next = n;
        tail = n;
        if (n.next != null) heap.offer(n.next);
    }
    return dummy.next;
}
```
- [295. Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/) – max-heap + min-heap.
```java
class MedianFinder {
    private final PriorityQueue<Integer> lo = new PriorityQueue<>(Collections.reverseOrder()); // max-heap
    private final PriorityQueue<Integer> hi = new PriorityQueue<>();                            // min-heap
    public void addNum(int num) {
        lo.offer(num);
        hi.offer(lo.poll());
        if (hi.size() > lo.size()) lo.offer(hi.poll());
    }
    public double findMedian() {
        return lo.size() > hi.size() ? lo.peek() : (lo.peek() + (double) hi.peek()) / 2;
    }
}
```
- [743. Network Delay Time](https://leetcode.com/problems/network-delay-time/) – Dijkstra.
```java
public int networkDelayTime(int[][] times, int n, int k) {
    List<int[]>[] graph = new List[n + 1];
    for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
    for (int[] t : times) graph[t[0]].add(new int[]{t[1], t[2]});
    int[] dist = new int[n + 1];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[k] = 0;
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // {node, dist}
    pq.offer(new int[]{k, 0});
    while (!pq.isEmpty()) {
        int[] cur = pq.poll();
        if (cur[1] > dist[cur[0]]) continue;
        for (int[] e : graph[cur[0]]) {
            int nd = cur[1] + e[1];
            if (nd < dist[e[0]]) {
                dist[e[0]] = nd;
                pq.offer(new int[]{e[0], nd});
            }
        }
    }
    int max = 0;
    for (int i = 1; i <= n; i++) max = Math.max(max, dist[i]);
    return max == Integer.MAX_VALUE ? -1 : max;
}
```

---

## StringBuilder

Mutable character sequence. Use instead of `String +=` in loops (which is O(n²)). Not thread-safe (use `StringBuffer` if needed).

| Method | Purpose |
|---|---|
| `append(x)` | add char/String/int/… |
| `insert(i, x)` | insert at index (O(n)) |
| `deleteCharAt(i)` / `delete(a, b)` | remove char / range |
| `setCharAt(i, c)` | overwrite |
| `charAt(i)`, `length()` | read |
| `setLength(n)` | truncate (e.g. `sb.setLength(sb.length() - 1)` to backtrack) |
| `reverse()` | reverse in place |
| `indexOf(s)`, `substring(a, b)` | search / slice |
| `toString()` | convert to `String` |

Related `String` methods: `toCharArray()`, `charAt`, `substring`, `split`, `equals` (never `==`), `String.valueOf(char[])`.

**Use when:** building output strings, reversing, in-place edit simulation, backtracking on strings.

**LeetCode**
- [151. Reverse Words in a String](https://leetcode.com/problems/reverse-words-in-a-string/) – build result with `append`.
```java
public String reverseWords(String s) {
    String[] words = s.trim().split("\\s+");
    StringBuilder sb = new StringBuilder();
    for (int i = words.length - 1; i >= 0; i--) {
        sb.append(words[i]);
        if (i > 0) sb.append(' ');
    }
    return sb.toString();
}
```
- [394. Decode String](https://leetcode.com/problems/decode-string/) – stack of `StringBuilder` + counts.
```java
public String decodeString(String s) {
    Deque<StringBuilder> strs = new ArrayDeque<>();
    Deque<Integer> counts = new ArrayDeque<>();
    StringBuilder cur = new StringBuilder();
    int k = 0;
    for (char c : s.toCharArray()) {
        if (Character.isDigit(c)) k = k * 10 + (c - '0');
        else if (c == '[') {
            strs.push(cur);
            counts.push(k);
            cur = new StringBuilder();
            k = 0;
        } else if (c == ']') {
            StringBuilder prev = strs.pop();
            prev.append(cur.toString().repeat(counts.pop()));
            cur = prev;
        } else cur.append(c);
    }
    return cur.toString();
}
```
- [22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) – backtracking with `append` / `setLength`.
```java
public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    backtrack(n, 0, 0, new StringBuilder(), res);
    return res;
}
private void backtrack(int n, int open, int close, StringBuilder sb, List<String> res) {
    if (sb.length() == 2 * n) { res.add(sb.toString()); return; }
    if (open < n) {
        sb.append('(');
        backtrack(n, open + 1, close, sb, res);
        sb.setLength(sb.length() - 1);
    }
    if (close < open) {
        sb.append(')');
        backtrack(n, open, close + 1, sb, res);
        sb.setLength(sb.length() - 1);
    }
}
```
