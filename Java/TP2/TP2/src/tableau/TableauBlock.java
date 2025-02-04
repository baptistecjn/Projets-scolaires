package tableau;

import types.Array;
import types.Tableau;

public class TableauBlock<T> implements Tableau<T> {

    private Array<Tableau<T>> cab_array;
    private int capacity;
    private int size;
    private int capinit;
    private int capabloc;

    public TableauBlock(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.capabloc = 128;
        this.cab_array = new Array<>(capacity);
    }

    public TableauBlock(int capinit, int capabloc){
        this.capinit = capinit;
        this.capabloc = capabloc;
        this.size = 0;
        this.cab_array = new Array<>(capinit);
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean empty(){
        return size == 0;
    }

    @Override
    public boolean full() {
        return size == capacity;
    }

    @Override
    public T get(int i) {
        assert(i >= 0 && i < this.size);
        return (T) this.cab_array.get(i);
    }

    @Override
    public void set(int i, T v) {
        assert(i >= 0 && i < this.size);
        cab_array.set(i, (Tableau<T>) v);
    }

    @Override
    public void push_back(T x) {
        if (this.full() || this.size == 0) {
            Tableau<T> new_array = (Tableau<T>) new Array<>(this.capabloc);
            new_array.set(0, x);
            this.cab_array.set(this.size / this.capabloc, new_array);
            this.capacity += this.capabloc;
        } else {
            Tableau<T> current_block = this.cab_array.get(this.size / this.capabloc);
            current_block.set(this.size % this.capabloc, x);
        }
        this.size++;
    }


    @Override
    public void pop_back() {
        assert(!this.empty());
        this.size--;
    }

    @Override
    public void clear() {
        size = 0;
    }


}