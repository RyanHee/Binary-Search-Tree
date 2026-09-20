package main;

import javax.swing.SwingUtilities;

/** Launches the interactive binary search tree display. */
public class BinarySearchTreeDriver {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Frame("Binary Search Tree"));
    }
}
