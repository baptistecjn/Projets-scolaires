package util;

public class Couple <T1,T2>{

    private T1 first;
    private T2 second;

    public Couple(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }
    public void setFirst(T1 first) {
        this.first = first;
    }
    public T2 getSecond() {
        return second;
    }
    public void setSecond(T2 second) {
        this.second = second;
    }

    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        Couple<?, ?> other = (Couple<?, ?>) obj;
        return (first == null ? other.first == null : first.equals(other.first)) &&
                (second == null ? other.second == null : second.equals(other.second));
    }
}
