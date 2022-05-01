package it.fmt.games.connect4;

public class Pair<T, K> {
    private final T value1;

    public T getValue1() {
        return value1;
    }

    public K getValue2() {
        return value2;
    }

    private final K value2;

    public Pair(T value1, K value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public static <A, B> Pair<A, B> of(A value1, B value2) {
        return new Pair<>(value1, value2);
    }
}
