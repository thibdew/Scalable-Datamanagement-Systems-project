package tries;

public interface AbstractIterator<V> {

    public static String DEFAULT_VAL = "NO_VALUE";

    // Standard iterator operations for Unary predicates (linear iterator interface)
    public int key();
    public void next();
    public void seek(int seekKey);
    public boolean atEnd();

    // Extra operations for data access
    public V getValue();
}
