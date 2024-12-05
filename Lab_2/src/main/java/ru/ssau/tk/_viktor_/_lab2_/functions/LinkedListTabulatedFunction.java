package ru.ssau.tk._viktor_._lab2_.functions;

import ru.ssau.tk._viktor_._lab2_.exceptions.InterpolationException;

import java.util.Iterator;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Cloneable{
    private Node head;

    static class Node implements Cloneable{
        public Node next;
        public Node prev;
        public double x;
        public double y;
        public Node(double x, double y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString(){
            return "(" + this.x + " ; " + this.y + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;
            Node other = (Node) o;
            return Double.compare(this.x, other.x) == 0 && Double.compare(this.y, other.y) == 0;
        }

        @Override
        public int hashCode() {
            return (int)this.x ^ (int)this.y;
        }

        @Override
        public Node clone() {
            return new Node(this.x, this.y);
        }
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues){
        if (xValues == null || yValues == null) {
            throw new IllegalArgumentException("Массивы не должны быть null.");
        }

        if (xValues.length < 2 || yValues.length < 2) {
            throw new IllegalArgumentException("Нужно хотя бы 2 точки");
        }

        checkLengthIsTheSame(xValues, yValues);

        checkSorted(xValues);

        for(int i = 0; i < xValues.length; i++){
            addNode(xValues[i], yValues[i]);
        }
    }
    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Нужно хотя бы 2 точки");
        }
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
        if (index < 0) throw new IllegalArgumentException("Индекс не может быть отрицательным!");
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
                throw new IllegalArgumentException();
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
        Node leftNode = getNode(0);
        Node rightNode = leftNode.next;
        return interpolate(x, leftNode.x, rightNode.x, leftNode.y, rightNode.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        Node leftNode = getNode(count-2);
        Node rightNode = leftNode.next;
        return interpolate(x, leftNode.x, rightNode.x, leftNode.y, rightNode.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node leftNode = getNode(floorIndex);
        Node rightNode = leftNode.next;
        if (x < leftNode.x || x > rightNode.x) {
            throw new InterpolationException("Значение x находится вне интервала интерполяции.");
        }
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

    @Override
    public String toString(){
        String res = "";
        Node cur = head;
        if (cur == null) {
            return "[]";
        }
        res += "[ ";
        do {
            res += cur.toString();
            cur = cur.next;
            if (cur != head) {
                res += ", ";
            }
        } while (cur != head);
        res += " ]";
        return res;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkedListTabulatedFunction that = (LinkedListTabulatedFunction)o;
        if(this.count != that.count) return false;

        Node cur1 = this.head;
        Node cur2 = that.head;
        for (int i = 0; i < count; i++){
            if (!cur1.equals(cur2)) return false;
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return true;
    }

    @Override
    public int hashCode(){
        Node cur = head;
        int res = 0;
        if (cur == null) return 0;
        do{
            res += cur.hashCode();
            cur = cur.next;
        }while (cur != head);
        return res;
    }

    @Override
    public LinkedListTabulatedFunction clone(){
        if (head == null) {
            return new LinkedListTabulatedFunction(new double[0], new double[0]);
        }

        Node cur = head;
        double[] xVal = new double[count];
        double[] yVal = new double[count];

        for (int i = 0; i < count; i++) {
            xVal[i] = cur.x;
            yVal[i] = cur.y;
            cur = cur.next;
        }

        return new LinkedListTabulatedFunction(xVal, yVal);
    }

    @Override
    public Iterator<Point> iterator() {
        throw new UnsupportedOperationException();
    }
}