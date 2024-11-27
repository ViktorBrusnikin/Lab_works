package ru.ssau.tk._viktor_._lab2_.functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction{
    private Node head;

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues){
        for(int i = 0; i < xValues.length; i++){
            addNode(xValues[i], yValues[i]);
        }
    }
    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xTo < xFrom) {
            double temp = xTo;
            xTo = xFrom;
            xFrom = temp;
        }
        if (xTo == xFrom) {
            for (int i = 0; i < count; i++) {
                addNode(xFrom, source.apply(xFrom));
            }
        } else {
            double step = (xTo - xFrom) / (count - 1);
            for (int i = 0; i < count; i++) {
                double x = xFrom + step * i;
                double y = source.apply(x);
                addNode(x, y);
            }
        }
    }
    private void addNode(double x, double y){
        Node newNode = new Node(x, y);
        if(head == null){
            newNode.next = newNode;
            newNode.prev = newNode;
            head = newNode;
        }
        else {
            Node last = head.prev;
            newNode.next = head;
            newNode.prev = head.prev;
            head.prev = newNode;
            last.next = newNode;
        }
        count++;
    }

    private Node getNode(int index){
        Node cur = head;
        if(index > count/2){
            int newIndex = count - index;
            while (newIndex != 0){
                cur = cur.prev;
                newIndex--;
            }
        }
        else {
            while (index != 0){
                cur = cur.next;
                index--;
            }
        }
        return cur;
    }
    @Override
    protected int floorIndexOfX(double x) {
        int temp = indexOfX(x);
        if (temp != -1) return temp;
        else {
            Node cur = head;
            if (x < head.x) {
                return 0;
            }
            if(x > head.prev.x){
                return count;
            }
            int index = 0;
            while (x > cur.x) {
                cur = cur.next;
                index++;
            }
            return index - 1;
        }
    }

    @Override
    protected double extrapolateLeft(double x) {
        if(count == 1) return getY(0);
        return interpolate(x, 0);
    }

    @Override
    protected double extrapolateRight(double x) {
        if(count == 1) return getY(0);
        return interpolate(x, count-2);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if(count == 1) return getY(0);
        Node leftNode = getNode(floorIndex);
        Node rightNode = leftNode.next;
        return interpolate(x, leftNode.x, rightNode.x, leftNode.y, rightNode.y);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        if (count == 0) {
            return -1;
        }
        Node cur = head;
        for(int i = 0; i < count; i++){
            if(cur.x == x) return i;
            cur = cur.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        if (count == 0) {
            return -1;
        }
        Node cur = head;
        for(int i = 0; i < count; i++){
            if(cur.y == y) return i;
            cur = cur.next;
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }
}