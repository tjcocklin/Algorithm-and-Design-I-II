/**
 * @author Jeffrey Cocklin This class defines an Avltree.
 */

public class AvlTree<E extends Comparable<E>> {

  public class AvlNode<E> {
    public AvlNode<E> left = null;
    public AvlNode<E> right = null;


    public AvlNode<E> parent;
    E val = null;

    /**
     * Default Constructor for AvlNode.
     */

    public AvlNode() {
      this.parent = null;
    }

    /**
     * Parameterized Constructor.
     * 
     * @param parent AvlNode that will be this node's parent.
     * @param val the element for this node.
     */

    public AvlNode(AvlNode<E> parent, E val) {
      this.parent = parent;
      this.val = val;
    }
  }

  AvlNode<E> root;
  int size;

  /**
   * Default Constructor for AvlTree.
   */

  public AvlTree() {
    this.root = new AvlNode<E>();
    this.size = 0;
  }

  /**
   * Size mutator that returns the size of the tree.
   * 
   * @return integer value of the tree size.
   */

  public int size() {
    return this.size;
  }

  /**
   * Insert inserts a new node into the tree
   * 
   * @param val the value to be assigned to the new node.
   */

  public void insert(E val) {
    if (this.size() == 0) {
      this.root.val = val;
      this.size++;
    } else {
      AvlNode<E> iter = this.root;
      while (true) {
        if (val.compareTo(iter.val) < 0) {
          if (iter.left == null) {
            AvlNode<E> newNode = new AvlNode<E>(iter, val);
            iter.left = newNode;

            this.size++;
            return;
          }
          iter = iter.left;
        } else {
          if (iter.right == null) {
            AvlNode<E> newNode = new AvlNode<E>(iter, val);
            iter.right = newNode;

            this.size++;
            return;
          }
          iter = iter.right;
        }
      }
    }
  }

  /**
   * lookup method looks for a node with val and returns it's node or null.
   * 
   * @param val the value to search for.
   * @return the node containing val or null.
   */

  public E lookup(E val) {
    return lookup(val, this.root);
  }
  
  /**
   * lookup method that uses recursion to search the tree for val. 
   * 
   * @param val the value to be looked up.
   * @param node the current node where search is to begin.
   * @return the node containing val or null.
   */
  
  public E lookup(E val, AvlNode<E> node) {
    if (node == null) {
      return null;
    }

    if (val.equals(node.val)) {
      return node.val;
    }

    if (val.compareTo(node.val) < 0) {
      return lookup(val, node.left);
    } else {
      return lookup(val, node.right);
    }
  }

  /**
   * rotateLeft rotate the target node left.
   * 
   * @param target the node to be rotated.
   */

  public void rotateLeft(AvlNode<E> target) {
    AvlNode<E> tempP = target.right;
    // can only rotate a node that has both children.
    if (target.left != null && target.right != null) {
      target.right = tempP.left;

      if (tempP.left != null) {
        tempP.left.parent = target;
      }

      tempP.parent = target.parent;

      if (target.parent == null) {
        this.root = tempP;
      } else if (target == target.parent.right) {
        target.parent.right = tempP;
      } else {
        target.parent.left = tempP;
      }
      tempP.left = target;
      target.parent = tempP;
    }
  }

  /**
   * rotateRight rotate the target node right.
   * 
   * @param target the node to be rotated.
   */

  public void rotateRight(AvlNode<E> target) {
    AvlNode<E> tempQ = target.left;
    // can only rotate a node if it has both children.
    if (target.left != null && target.right != null) {
      target.left = tempQ.right;

      if (tempQ.right != null) {
        tempQ.right.parent = target;
      }

      tempQ.parent = target.parent;

      if (target.parent == null) {
        this.root = tempQ;
      } else if (target == target.parent.left) {
        target.parent.left = tempQ;
      } else {
        target.parent.right = tempQ;
      }
      tempQ.right = target;
      target.parent = tempQ;
    }
  }

  /* Do not change anything below here! */

  /**
   * Checks whether the nth bit within an integer is set.
   * 
   * @param number The number to check.
   * @param index The index of the bit to find.
   * @return True if the nth bit is set. For example, (5, 1) is true since 5 = b101, and the ones
   *         place is set. Similarly, (5, 2) is false.
   */

  private static boolean nthBitSet(int number, int index) {
    int constant = 1 << (index - 1);
    return (number & constant) != 0;
  }

  /**
   * Prints an ascii representation of the tree.
   * 
   * @param root The current node to print.
   * @param depth The level of the tree in which the current node lies. Levels are counted with the
   *        root starting at 0.
   * @param isLeft Whether the current node is a left child.
   * @param parBits A bitstring (integer) representing the parents in the path above this node. Used
   *        to 'fill in the gaps' in the drawing.
   * @param buffered Whether this was called to specifically include additional whitespace on the
   *        left.
   */

  public void printAscii(AvlNode<E> root, int depth, 
      boolean isLeft, int parBits, boolean buffered) {
    if (root == null) {
      return;
    }
    for (int i = 0; i < (depth - 1) * 3; i++) {
      if (i % 3 == 1 && nthBitSet(parBits, depth - (i / 3))) {
        System.out.print("|");
      } else {
        System.out.print(" ");
      }
    }
    if (isLeft && depth != 0 && !buffered) {
      System.out.print(" `- ");
    } else if (depth != 0 && !buffered) {
      System.out.print(" |- ");
    } else if (buffered) {
      for (int i = 0; i < depth * 3; i++) {
        System.out.print(" ");
      }
    }

    System.out.println(root.val);

    boolean hasLeft = root.left != null;
    int newParBits = parBits << 1;
    if (hasLeft) {
      newParBits++;
    }

    printAscii(root.right, depth + 1, !hasLeft, newParBits, false);
    printAscii(root.left, depth + 1, true, --newParBits, false);
  }

  /**
   * Prints an ascii representation of the tree.
   */

  public void printAscii(int depth) {
    printAscii(root, depth, false, 0, true);
  }

  public void printAscii() {
    printAscii(root, 0, false, 0, false);
  }
}
