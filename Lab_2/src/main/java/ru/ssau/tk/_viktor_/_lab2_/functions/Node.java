package ru.ssau.tk._viktor_._lab2_.functions;

class Node implements Cloneable{
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