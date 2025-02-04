package tableau;

import types.Array;
import types.Tableau;

public class Block<T> implements Tableau<T> {

    private Array<T> cab_array;
    private int capacity;
    private int size;

    public Block(int capacity) {
        assert (capacity > 0);
        this.capacity = capacity;
        this.size = 0;
        this.cab_array = new Array<>(capacity);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean empty() {
        return this.size == 0;
    }

    @Override
    public boolean full() {
        return this.size == this.capacity;
    }

    @Override
    public T get(int i) {
        assert(i>=0 && i<=this.size);
        return this.cab_array.get(i);
    }

    @Override
    public void set(int i, T v) {
        assert(i>=0 && i<=this.size);
        this.cab_array.set(i, v);
    }

    @Override
    public void push_back(T x) {
        assert(!this.full());
        this.set(this.size, x);
        this.size++;
    }

    @Override
    public void pop_back() {
        assert(!this.empty());
        this.size--;
    }

    public void clear() {
        this.size = 0;
    }

}