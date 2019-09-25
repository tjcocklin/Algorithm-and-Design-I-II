
public class AvlTester {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

    AvlTree<Integer> hat= new AvlTree<Integer>();
    
    
    hat.insert(4);
   
    System.out.println("root value "+hat.root.val);
    
    hat.insert(5);
    hat.insert(3);
    
    hat.printAscii();
   
    System.out.println("look up 3 "+hat.lookup(3));
    System.out.println("look up 6 "+hat.lookup(6));
    System.out.println("\n\n");
    
    AvlTree<String> tree = new AvlTree<String>();
    
    tree.insert("Jose");
    tree.insert("Jaki");
    tree.insert("Louie");
    tree.insert("Beverly");
    tree.insert("Nathan");
    tree.insert("Kiley");
    
    tree.printAscii();
    System.out.println("\n\n");
    
    System.out.println("root right "+tree.root.right.val);
    System.out.println("right child of above: "+ tree.root.right.right.val);
    System.out.println("leftt child of above: "+ tree.root.right.left.val);
    
    System.out.println("right child of above parent: "+ tree.root.right.right.parent.val);
    System.out.println("leftt child of above parent: "+ tree.root.right.left.parent.val);
    
    tree.rotateLeft(tree.root);
    System.out.println("\n\n");
    tree.printAscii();
  
    System.out.println("\n\n");
    //tree.root.left.right.parent=tree.root.left;
      tree.rotateRight(tree.root.left);
    //System.out.println("tree root left "+tree.root.left.val);
    //tree.rotateRight(tree.root.left);
     tree.printAscii();
    //System.out.println("tree root "+tree.root.val);
    //System.out.println("tree root left parent"+tree.root.left.parent.val);
  
     AvlTree<Double> tree2= new AvlTree<Double>();
     
     tree2.insert((double)3);
     tree2.insert((double)4);
     tree2.insert((double)2);
     tree2.insert((double)1);
     tree2.insert((double)2.5);
     tree2.insert((double)2.6);
     
     tree2.printAscii();
     tree2.rotateRight(tree2.root);
     System.out.println("\n\n");
     tree2.printAscii();
  
  
     AvlTree<Integer> tree3 = new AvlTree<Integer>();
     
     tree3.insert(3);
     tree3.insert(5);
     tree3.insert(4);
     tree3.insert(6);
     tree3.insert(10);
     tree3.insert(9);
     tree3.insert(1);
     
     System.out.println("\n");
     tree3.printAscii();
  
     tree3.rotateLeft(tree3.root.left);
     System.out.println("\n");
     tree3.printAscii();
     
     tree3.rotateRight(tree3.root.right);
     System.out.println("\n");
     tree3.printAscii();
     
     tree3.rotateLeft(tree3.root.right.right.right.right.left);
     System.out.println("\n");
     tree3.printAscii();
     
     
  }

}
