import java.util.Stack;
/**
 * <h3>Class Tree</h3>
 * <p></p>
 * The Tree class represents a binary search tree data structure that stores Store objects.
 * Each node of the tree contains a reference to a Store object and pointers to its left and right child nodes.
 * The class provides methods for inserting, deleting and searching nodes in the tree.
 * @see Tree#findNodeByValue(Store)
 * @see Tree#insertNode(Store)
 * @see Tree#deleteNode(Store)
 * @see Tree#receiveHeir(Node)
 * @see Tree#printTree()
 * @see Tree#preOrderTraversal(Node)
 * @see Tree#traverseInOrder(Node)
 * @see Tree#traversePostOrder(Node)
 * @author EgorBusuioc
 * @version 1.0
 */
class Tree {
    private Node rootNode; // the root node of the tree

    public Tree() { // Empty Tree
        rootNode = null;
    }
    /**
     * <h3>Find Node By Value</h3>
     * <p></p>
     * Searches for a node in the tree with the specified Store value.
     *
     * @param value the Store object to search for
     * @return the node containing the value, or null if no such node exists in the tree
     */
    public Node findNodeByValue(Store value) {
        Node currentNode = rootNode;
        while (!currentNode.getValue().equals(value)) {
            if (value.getAnidentificationnumber() < currentNode.getValue().getAnidentificationnumber()) {
                currentNode = currentNode.getLeftProduct();
            } else {
                currentNode = currentNode.getRightProduct();
            }
            if (currentNode == null) {
                return null;
            }
        }
        return currentNode;
    }

    public void insertNode(Store value) {
        Node newNode = new Node();
        newNode.setValue(value);
        if (rootNode == null) {
            rootNode = newNode;
        }
        else {
            Node currentNode = rootNode;
            Node parentNode;
            while (true)
            {
                parentNode = currentNode;
                if(value.equals(currentNode.getValue())) {
                    return;
                }
                else  if (value.compareTo(currentNode.getValue()) < 0) {
                    currentNode = currentNode.getLeftProduct();
                    if (currentNode == null){
                        parentNode.setLeftProduct(newNode);
                        return;
                    }
                }
                else {
                    currentNode = currentNode.getRightProduct();
                    if (currentNode == null) {
                        parentNode.setRightProduct(newNode);
                        return;
                    }
                }
            }
        }
    }


    public boolean deleteNode(Store value) {
        Node currentNode = rootNode;
        Node parentNode = rootNode;
        boolean isLeftProduct = true;

        while (currentNode.getValue().compareTo(value) != 0) {
            parentNode = currentNode;
            if (value.compareTo(currentNode.getValue()) < 0) {
                isLeftProduct = true;
                currentNode = currentNode.getLeftProduct();
            } else {
                isLeftProduct = false;
                currentNode = currentNode.getRightProduct();
            }
            if (currentNode == null) {
                return false;
            }
        }

        if (currentNode.getLeftProduct() == null && currentNode.getRightProduct() == null) {
            if (currentNode == rootNode) { // node is root
                rootNode = null;
            } else if (isLeftProduct) {
                parentNode.setLeftProduct(null);
            } else {
                parentNode.setRightProduct(null);
            }
        } else if (currentNode.getRightProduct() == null) {
            if (currentNode == rootNode) {
                rootNode = currentNode.getLeftProduct();
            } else if (isLeftProduct) {
                parentNode.setLeftProduct(currentNode.getLeftProduct());
                parentNode.setRightProduct(currentNode.getLeftProduct());
            }
        } else if (currentNode.getLeftProduct() == null) {
            if (currentNode == rootNode) {
                rootNode = currentNode.getRightProduct();
            } else if (isLeftProduct) {
                parentNode.setLeftProduct(currentNode.getRightProduct());
            } else {
                parentNode.setRightProduct(currentNode.getRightProduct());
            }
        } else {
            Node heir = receiveHeir(currentNode);
            if (currentNode == rootNode) {
                rootNode = heir;
            } else if (isLeftProduct) {
                parentNode.setLeftProduct(heir);
            } else {
                parentNode.setRightProduct(heir);
            }
        }

        return true;
    }


    private Node receiveHeir(Node node) {
        Node parentNode = node;
        Node heirNode = node;
        Node currentNode = node.getRightProduct();
        while (currentNode != null)
        {
            parentNode = heirNode;
            heirNode = currentNode;
            currentNode = currentNode.getLeftProduct();
        }
        if (heirNode != node.getRightProduct())
        {
            parentNode.setLeftProduct(heirNode.getRightProduct());
            heirNode.setRightProduct(node.getRightProduct());
        }
        return heirNode;
    }


    public void printTree() {
        Stack globalStack = new Stack();
        globalStack.push(rootNode);
        int gaps = 32;
        boolean isRowEmpty = false;
        String separator = "-----";
        System.out.println(separator);
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.getValue());
                    localStack.push(temp.getLeftProduct());
                    localStack.push(temp.getRightProduct());
                    if (temp.getLeftProduct() != null ||
                            temp.getRightProduct() != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("__");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
        }
        System.out.println(separator);
    }
    public void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.getValue() + " ");
            preOrderTraversal(node.getLeftProduct());
            preOrderTraversal(node.getRightProduct());
        }
    }
    /**
     * <h3>Traverse Post Order</h3>
     * <p></p>
     * Traverses the binary tree in post-order, starting from the given node.
     * @param node The node to start the traversal from.
     */
    public void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.getLeftProduct());
            traversePostOrder(node.getRightProduct());
            System.out.print(node.getValue() + " ");
        }
    }
    /**
     * <h3>Traverse In Order</h3>
     * <p></p>
     * Traverses the binary tree in in-order, starting from the given node.
     * @param node The node to start the traversal from.
     */
    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.getLeftProduct());
            System.out.print(node.getValue() + " ");
            traverseInOrder(node.getRightProduct());
        }
    }
}