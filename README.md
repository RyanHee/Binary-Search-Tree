# Binary Search Tree

A generic Java binary search tree that stores distinct values in natural order.
`BinarySearchTree<T extends Comparable<? super T>>` supports integers, strings,
and other types implementing `Comparable`.

Smaller values go into the left subtree and larger values into the right.
Values that compare as equal are treated as duplicates and are ignored on
insertion, even if their `equals` methods disagree. Null values are not accepted.

## Functionality

| Method | Behavior |
| --- | --- |
| `add(value)` | Inserts a value unless an equivalent value already exists. |
| `contains(value)` | Checks for a comparison-equivalent value. |
| `remove(value)` | Removes a value and returns a detached `BinaryNode<T>`, or null if absent. |
| `getSmallest()` / `getLargest()` | Returns the minimum / maximum value, or null if empty. |
| `getNumNodes()` | Counts all nodes. |
| `getNumLeaves()` | Counts nodes without children. |
| `getHeight()` | Counts edges on the longest downward path from the root. |
| `getNumLevels()` | Returns the height plus one. |
| `getWidth()` | Returns the largest node count on any level. |
| `getWidthAtLevel(level)` | Counts nodes at a depth; the root is level zero. Invalid levels return zero. |
| `getDiameter()` | Counts nodes on the longest path between any two nodes, even if it does not pass through the root. |
| `isFull()` | Checks whether every node has zero or two children. This does not imply balance. |

Removal handles leaves, nodes with one child, and nodes with two children. For
two children, it uses the inorder successor from the right subtree. The returned
node contains the removed value and has no children.

An empty tree has height -1 and zero nodes, leaves, levels, width, and diameter.
It is considered full. Insertion, search, and removal throw
`NullPointerException` for null arguments.

## Traversals

Every traversal returns a fresh `ArrayList<T>`, or an empty list for an empty
tree. Changing the returned list does not change the tree; its elements are
references to the stored values.

| Method | Visit order |
| --- | --- |
| `preOrder()` | Root, left subtree, right subtree |
| `inOrder()` | Left subtree, root, right subtree; ascending values |
| `postOrder()` | Left subtree, right subtree, root |
| `reverseOrder()` | Right subtree, root, left subtree; descending values |
| `levelOrder()` | Level by level, left to right |

`toString()` formats the inorder list, for example `[1, 2, 3]`.

## Example

```java
import main.BinarySearchTree;

BinarySearchTree<Integer> tree = new BinarySearchTree<>();
for (int value : new int[]{4, 2, 6, 1, 3, 5, 7}) {
    tree.add(value);
}

System.out.println(tree.inOrder());    // [1, 2, 3, 4, 5, 6, 7]
System.out.println(tree.preOrder());   // [4, 2, 1, 3, 6, 5, 7]
System.out.println(tree.postOrder());  // [1, 3, 2, 5, 7, 6, 4]
System.out.println(tree.levelOrder()); // [4, 2, 6, 1, 3, 5, 7]
System.out.println(tree.getHeight());  // 2
System.out.println(tree.getDiameter());// 5
System.out.println(tree.contains(3)); // true

tree.remove(4);
System.out.println(tree.inOrder());    // [1, 2, 3, 5, 6, 7]
```

## Build and test

Requires JDK 11 or newer. No external dependencies are needed.

```sh
mkdir -p build
javac -Xlint:all -Werror -d build src/main/*.java src/tests/*.java
java -cp build tests.TestBST
```

The regression suite checks empty trees, traversals, statistics, duplicate
handling, removal, and null rejection. It also compares 10,000 randomized
insertion/removal operations against Java's `TreeSet`. Each run generates fresh
random values and prints its seed. Reproduce a run by passing that seed:

```sh
java -cp build tests.TestBST 12345
```

Generate the tree's API documentation with:

```sh
javadoc -d docs -sourcepath src src/main/BinarySearchTree.java
```

## Console driver

Run `java -cp build main.BinarySearchTreeDriver`. It reads:

1. A count of insertion lines, followed by those lines of whitespace-separated strings.
2. A count of search values, followed by those values.
3. A count of removal values, followed by those values.

Example input:

```text
1
D B F A C E G
2
D Z
1
D
```

The driver prints traversals and statistics, reports search results, and performs
removals. It stores strings, so values use lexicographic ordering.

## Performance and limitations

Insertion, search, removal, and minimum/maximum lookup take O(h) time, where h
is the tree height. The tree does not balance itself, so sorted insertion can
produce a chain and make these operations O(n). Traversals, diameter, and full
tree statistics take O(n) time; width at a level visits only the levels needed.

Traversal results use O(n) space. Recursive methods use O(h) stack space and
can overflow the stack on sufficiently deep trees. Breadth-first methods use
O(w) working space, where w is the maximum width. Stored values must retain
their ordering while in the tree. The implementation is not thread-safe.
