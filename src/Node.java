/**
 * <h3>Class Node</h3>
 * <p></p>
 * This class represents a node in a binary search tree. Each node stores a store object as its key,
 * and has a left and right child, which are also nodes in the tree.
 * @see Node#getValue()
 * @see Node#setValue(Store)
 * @see Node#getLeftProduct() ()
 * @see Node#getRightProduct() ()
 * @see Node#setLeftProduct(Node) (Node)
 * @see Node#setRightProduct(Node)
 */
class Node {
    Store store;
    private Node leftProduct;
    private Node rightProduct;


    public Store getValue() {
        return this.store;
    }

    public void setValue(final Store store) {
        this.store = store;
    }

    public Node getLeftProduct() {
        return this.leftProduct;
    }

    public void setLeftProduct(final Node leftProduct) {
        this.leftProduct = leftProduct;
    }

    public Node getRightProduct() {
        return this.rightProduct;
    }

    public void setRightProduct(final Node rightProduct) {
        this.rightProduct = rightProduct;
    }

    @Override
    public String toString() {
        return "Node{" +
                "store=" + store +
                ", leftProduct=" + leftProduct +
                ", rightProduct=" + rightProduct +
                '}';
    }
}