package main;

import java.util.Scanner;
import static java.lang.System.*;

public class BinarySearchTreeDriver {
    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);

        BinarySearchTree<String> tree = new BinarySearchTree<>();
        int numLines = input.nextInt();
        input.nextLine();
        for (int i = 0; i<numLines; i++)
        {
            String[] list = input.nextLine().trim().split("\\s+");
            for (String k:list)
            {
                if (!k.isEmpty()) {
                    tree.add(k);
                }
            }
        }
        out.println("Proper Tree Display");
        out.println("Tree-->"+tree);
        out.println("PRE ORDER\n"+tree.preOrder());
        out.println("POST ORDER\n"+tree.postOrder());
        out.println("IN ORDER\n"+tree.inOrder());
        out.println("REVERSE ORDER\n"+tree.reverseOrder());
        out.println("LEVEL ORDER\n"+tree.levelOrder());
        out.println("Number of leaves: "+tree.getNumLeaves());
        out.println("Number of levels: "+tree.getNumLevels());
        out.println("The Tree width: "+tree.getWidth());
        out.println("The Tree height: "+tree.getHeight());
        out.println("The Tree diameter: "+tree.getDiameter());
        out.println("Number of nodes: "+tree.getNumNodes());
        for (int i = 0; i<tree.getNumLevels(); i++) {
            out.println("width at level "+i+" "+tree.getWidthAtLevel(i));
        }
        if (tree.isFull())
            out.println("Tree is full");
        else
            out.println("Tree is not full.");

        numLines = input.nextInt();
        for (int i = 0; i<numLines; i++)
        {
            String x = input.next();
            if (tree.contains(x))
                out.println("Tree contains "+x);
            else
                out.println("Tree does not contain "+x);
        }

        out.println("Largest value: "+tree.getLargest());
        out.println("Smallest value: "+tree.getSmallest());

        numLines = input.nextInt();
        for (int i = 0; i<numLines; i++)
        {
            String x = input.next();
            tree.remove(x);
        }

        out.println("Proper Tree Display");
    }
}
