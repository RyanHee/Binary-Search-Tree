# Binary Search Tree

A Java binary search tree for naturally ordered values, using
`BinarySearchTree<T extends Comparable<? super T>>`.

## Build and test

Requires JDK 11 or newer; no external dependencies.

```sh
mkdir -p build
javac -Xlint:all -Werror -d build src/main/*.java src/tests/*.java
java -cp build tests.TestBST
java -cp build main.BinarySearchTreeDriver
```

Tests generate fresh random values on each run and print their seed. To reproduce
a run, pass that seed: `java -cp build tests.TestBST <seed>`.

The driver reads a count of insertion lines, those lines of whitespace-separated
strings, a count of search values followed by those values, and a count of removal
values followed by those values. For example:

```text
1
D B F A C E G
2
D Z
1
D
```

## Behavior

- Duplicate values are ignored. Equality is defined by `compareTo(...) == 0`.
- Null values are rejected by insertion, search, and removal.
- Empty trees have height -1, zero levels, nodes, leaves, width, and diameter,
  and are considered full. Both minimum and maximum return null.
- Height counts edges; diameter counts nodes on the longest path anywhere in
  the tree. Width levels start at zero; nonexistent levels have width zero.
- Removal accepts the tree's value type and returns a detached node containing
  the removed value, or null if no matching value exists.
- Preorder, inorder, postorder, reverse order, and level order each return a fresh
  `ArrayList<T>`, or an empty list for an empty tree. `toString()` displays the
  inorder list. The full-tree display methods continue to return formatted strings.
- `fullLevelOrder(maxLevels)` limits the display depth. The driver displays up to
  six levels. The unlimited version can produce exponential output on sparse trees.

This is an unbalanced tree. Operations can take linear time on sorted input,
and recursive traversals and statistics are limited by the Java call stack.
