package oop.finalexam.t1;

import java.util.*;

/**
 * <h1>Task 1 – List Management</h1>
 *
 * <h2>Algorithm</h2>
 * <ol>
 *   <li><strong>Build phase</strong> – for each value <code>x</code> in
 *       <code>list1</code> compute <code>idx = x + 1</code>. If
 *       <code>idx</code> is inside <code>list2</code> append
 *       <code>list2[idx] + x</code> to <code>list3</code>; otherwise print
 *       the warning defined below.</li>
 *   <li><strong>Removal phase (simultaneous)</strong> – remove from
 *       <code>list3</code> every element whose index equals a value in
 *       <code>list1</code>. Out-of-range indices trigger the same warning
 *       and are ignored.</li>
 * </ol>
 *
 * <h2>Error handling (big values)</h2>
 * An index that falls outside its target list prints exactly one line:
 * <pre>⚠️  Big value — index &lt;n&gt; is invalid for list&lt;m&gt; (size &lt;s&gt;)</pre>
 *
 * <h2>Usage</h2>
 * <pre>
 * javac -d out src/oop/finalexam/t1/ListManagerApp.java
 * java  -cp out oop.finalexam.t1.ListManagerApp
 * </pre>
 *
 * <h2>Author</h2>
 * Muhammad Ahmed, 03 Jul 2025
 */
public final class ListManagerApp {

    private static final List<Integer> LIST1 = List.of(7, 2, 8, 1, 3, 10, 6, 5, 4, 9);
    private static final List<String>  LIST2 = List.of(
            "qmd", "vyj", "mgw", "bli", "hgn", "dhh",
            "uml", "seh", "xcv", "tar", "uhq", "zxj");

    public static void main(String[] args) {
        new ListManagerApp(LIST1, LIST2).run();
    }

    private final List<Integer> list1;
    private final List<String>  list2;
    private final List<String>  list3 = new ArrayList<>();

    public ListManagerApp(List<Integer> list1, List<String> list2) {
        this.list1 = new ArrayList<>(list1);
        this.list2 = new ArrayList<>(list2);
    }

    public void run() {
        buildPhase();
        removePhase();
        System.out.println("Result list3 → " + list3);
    }

    private void buildPhase() {
        for (int x : list1) {
            int idx = x + 1;
            if (idx < 0 || idx >= list2.size()) {
                warn(2, idx, list2.size());
                continue;
            }
            list3.add(list2.get(idx) + x);
        }
    }

    private void removePhase() {
        List<Integer> toDelete = new ArrayList<>();
        for (int x : list1) {
            if (x >= 0 && x < list3.size()) {
                toDelete.add(x);
            } else {
                warn(3, x, list3.size());
            }
        }
        Collections.sort(toDelete);
        int shift = 0;
        for (int idx : toDelete) {
            list3.remove(idx - shift);
            shift++;
        }
    }

    private static void warn(int listNumber, int badIndex, int size) {
        System.out.printf("⚠️  Big value — index %d is invalid for list%d (size %d)%n",
                badIndex, listNumber, size);
    }
}
