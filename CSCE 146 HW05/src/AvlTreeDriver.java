import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

public class AvlTreeDriver {

  public static void main(String[] args) {
    if (!testLookup()) {
      System.out.println("Lookup failed.");
      System.exit(1);
    } else {
      System.out.println("Lookup test succeeded.");
    }

    System.out.println("----------------");
    System.out.println("Rotation test:");
    testRotation();
    System.out.println("----------------");
    System.out.println("Randomized rotation test:");
    testRandomRotation();
    System.out.println("----------------");
  }

  /**
   * Randomized method to test the lookup method of the AvlTree.
   * Tests both contains and not contains
   */
  private static boolean testLookup() {
    ArrayList<Integer> list = new ArrayList<Integer>();
    AvlTree<Integer> tree = new AvlTree<Integer>();
    Random rand = new Random();
    int maxNum = 10000 + rand.nextInt(900000);
    int minNum = rand.nextInt(10000);
    for (int i = 0; i < 100000; i++) {
      int num = minNum + rand.nextInt(maxNum - minNum);
      list.add(num);
      tree.insert(num);
    }
    for (int i = 0; i < 500; i++) {
      boolean shouldBeFalse = rand.nextBoolean();
      Integer lookupNum = rand.nextInt();
      if (shouldBeFalse) {
        while (minNum <= lookupNum && lookupNum <= maxNum) {
          lookupNum = rand.nextInt();
        }
      } else {
        lookupNum = list.get(rand.nextInt(list.size()));
      }
      boolean isFalse = tree.lookup(lookupNum) == null;
      if (shouldBeFalse != isFalse) {
        return false;
      }
    }
    return true;
  }

  private static void testRandomRotation() {
    AvlTree<Integer> tree = new AvlTree<Integer>();
    ArrayList<Integer> list = new ArrayList<Integer>();

    int maxNum = 5;
    for (int i = 0; i < maxNum; i++) {
      list.add(i);
    }
    Random rand = new Random();
    for (int i = 0; i < maxNum; i++) {
      tree.insert(list.remove(rand.nextInt(list.size())));
    }
    Class<?> avlTreeClass = getAvlTreeClass();
    Class<?> avlNodeClass = getNodeClass(avlTreeClass);

    System.out.println("Random tree:");
    tree.printAscii(1);
    for (int i = 0; i < 3; i++) {
      boolean left = rand.nextBoolean();
      if (left) {
        System.out.print("Left ");
      } else {
        System.out.print("Right ");
      }
      int num = rand.nextInt(maxNum);
      System.out.println("rotation around " + num);
      performRotation(tree, num, left);
      tree.printAscii(1);
    }
  }

  private static void testRotation() {
    AvlTree<String> tree = buildNewStringTree();
    Class<?> avlTreeClass = getAvlTreeClass();
    Class<?> avlNodeClass = getNodeClass(avlTreeClass);

    tree.printAscii(1);
    System.out.println("Left rotation around 'Jose'");
    performRotation(tree, "Jose", true);
    tree.printAscii(1);
    System.out.println("Right rotation around 'Jose'");
    performRotation(tree, "Jose", false);
    tree.printAscii(1);
    System.out.println("Right rotation around 'Louie'");
    performRotation(tree, "Louie", false);
    tree.printAscii(1);
  }

  private static void performRotation(AvlTree tree, Comparable rootElement, boolean left) {
    Class<?> avlTreeClass = getAvlTreeClass();
    Class<?> avlNodeClass = getNodeClass(avlTreeClass);
    Method rotateLeftMethod = null;

    String methodName = "rotateLeft";
    if (!left) {
      methodName = "rotateRight";
    }
    try {
      rotateLeftMethod = avlTreeClass.getDeclaredMethod(methodName, avlNodeClass);
    } catch (NoSuchMethodException e) {
      System.out.print("Your avl tree class needs a method entitled 'rotate");
      if (left) {
        System.out.println("Left'");
      } else {
        System.out.println("Right'");
      }
      System.exit(1);
    }

    rotateLeftMethod.setAccessible(true);

    try {
      rotateLeftMethod.invoke(tree, nodeLookup(tree, rootElement));
    } catch (IllegalAccessException e) {
      System.out.println("problem");
    } catch (InvocationTargetException e) {
      System.out.println("Error: your rotate left method threw an exception");
      System.exit(1);
    }
  }

  private static AvlTree<String> buildNewStringTree() {
    AvlTree<String> tree = new AvlTree<String>();
    tree.insert("Jose");
    tree.insert("Jaki");
    tree.insert("Louie");
    tree.insert("Beverly");
    tree.insert("Nathan");
    tree.insert("Kiley");
    return tree;
  }

  private static Object nodeLookup(AvlTree tree, Comparable number) {
    Class<?> avlTreeClass = getAvlTreeClass();
    Class<?> avlNodeClass = getNodeClass(avlTreeClass);

    Field rootNodeField = null;
    try {
      rootNodeField = avlTreeClass.getDeclaredField("root");
    } catch (NoSuchFieldException e) {
      System.out.println("Your avl tree class needs a field entitled 'root'");
      System.exit(1);
    }

    rootNodeField.setAccessible(true);
    Object iterNode = null;

    try {
      iterNode = rootNodeField.get(tree);
    } catch (IllegalAccessException e) {
      System.out.println("problem");
      System.exit(1);
    }

    Field leftField = null;
    Field rightField = null;
    Field valField = null;
    try {
      leftField = avlNodeClass.getDeclaredField("left");
      rightField = avlNodeClass.getDeclaredField("right");
      valField = avlNodeClass.getDeclaredField("val");
    } catch (NoSuchFieldException e) {
      System.out.println("Your avl node class need fields entitled 'left', 'right', and 'val'");
      System.exit(1);
    }

    leftField.setAccessible(true);
    rightField.setAccessible(true);
    valField.setAccessible(true);

    while (true) {
      Object left = null;
      Object right = null;
      Comparable val = null;
      try {
        left = leftField.get(iterNode);
        right = rightField.get(iterNode);
        val = (Comparable) valField.get(iterNode);
      } catch (IllegalAccessException e) {
        System.out.println("problem");
        System.exit(1);
      }
      if (iterNode == null) {
        return null;
      } else if (val.compareTo(number) == 0) {
        return iterNode;
      } else if (val.compareTo(number) > 0) {
        iterNode = left;
      } else {
        iterNode = right;
      }
    }
  }

  private static Class<?> getAvlTreeClass() {
    Class<?> avlTreeClass = null;
    try {
      avlTreeClass = Class.forName("AvlTree");
    } catch (ClassNotFoundException e) {
      System.out.println("The name of your java file MUST be 'AvlTree.java'");
      System.exit(1);
    }
    return avlTreeClass;
  }

  private static Class<?> getNodeClass(Class<?> avlTreeClass) {
    Class<?> avlNodeClass = null;
    Class<?>[] declaredClasses = avlTreeClass.getDeclaredClasses();
    for (Class<?> clazz : declaredClasses) {
      if (!clazz.getName().contains("AvlNode")) {
        System.out.println("Your class should only have one internal class, named 'AvlNode'");
        System.exit(1);
      } else {
        avlNodeClass = clazz;
      }
    }
    return avlNodeClass;
  }
}
