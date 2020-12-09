package com.bogdanmaier;

public class Node<T> {

    private T value;
    private int productionNumber;
    private Node<T> leftChild;
    private Node<T> rightChild;

    public Node (T value) {
        this.value = value;
    }

    public void setProductionNumber (int val) {
        this.productionNumber = val;
    }

    public int getProductionNumber () {
        return this.productionNumber;
    }

    public void setLeftChild (Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild (Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public Node<T> getLeftChild () {
        return leftChild;
    }

    public Node<T> getRightChild () {
        return rightChild;
    }

}
