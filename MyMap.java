import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Amanda on 10/8/2017.
 */
public class MyMap<K, V> {

    int size = 0;
    int capacity;
    double loadFactor = 0.75;
    Entry[] table;

    public MyMap() {
        capacity = 16;
        table = new Entry[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(K key) {
        V value = get(key);
        return value != null;
    }

    public V get(K key) {
        int idx = key.hashCode() & (capacity - 1);
        Object result = null;
        if (table[idx] == null) return (V) result;
        Entry<K, V> list = table[idx];
        while (list != null && !key.equals(list.key)) list = list.next;
        if (list != null) result = list.value;
        return (V) result;
    }

    public V put(K key, V value) {
        int code = key.hashCode();
        int idx = code & (capacity - 1);
        Object result = null;
        if (table[idx] == null) {
            Entry<K, V> list = new Entry<>(key, value, code);
            table[idx] = list;
            size++;

        } else {
            Entry<K, V> list = table[idx];
            Entry<K, V> pre = null;
            while (list != null) {
                if (list.key.equals(key)) {
                    result = list.value;
                    list.value = value;
                    break;
                }
                pre = list;
                list = list.next;
            }
            if (list == null) {
                pre.next = new Entry<>(key, value, code);
                size++;
            }
        }

        // re size
        if (size >= loadFactor * capacity) {
            reSize();
        }

        return (V) result;
    }

    void reSize() {
        int newCap = capacity * 2 < 0 ? Integer.MAX_VALUE : capacity * 2;
        Entry[] newTable = new Entry[newCap];
        for (int i = 0; i < capacity; i++) {

            Entry<K, V> AHead = null;
            Entry<K, V> ATail = null;
            Entry<K, V> BHead = null;
            Entry<K, V> BTail = null;
            for (Entry<K, V> list = table[i]; list != null; list = list.next) {
                int newIdx = list.hashCode & (newCap - 1);
                if (newIdx == i) {
                    if (AHead == null) {
                        AHead = list;
                        ATail = AHead;
                        continue;
                    }
                    ATail.next = list;
                    ATail = ATail.next;
                } else {
                    if (BHead == null) {
                        BHead = list;
                        BTail = BHead;
                        continue;
                    }
                    BTail.next = list;
                    BTail = BTail.next;
                }
            }
            if (ATail != null) ATail.next = null;
            if (BTail != null) BTail.next = null;
            newTable[i] = AHead;
            if (BHead != null) newTable[BHead.hashCode & (newCap - 1)] = BHead;

        }
        capacity = newCap;
        table = newTable;
    }

    public V remove(K key) {
        Object result = null;
        int idx = key.hashCode() & (capacity - 1);
        if (table[idx] != null) {
            Entry<K, V> list = table[idx];
            Entry<K, V> pre = null;
            while (list != null) {

                if (list.key.equals(key)) {
                    result = list.value;
                    if (pre == null) table[idx] = list.next;
                    else {
                        pre.next = list.next;
                    }
                    size --;
                    break;
                }
                pre = list;
                list = list.next;

            }
        }
        return (V) result;
    }

    public Set<Entry> entrySet() {
        Set<Entry> set=new HashSet<>();
        for(int i=0;i<capacity;i++){
            Entry<K,V> list=table[i];
            while(list!=null){
                set.add(list);
                list=list.next;
            }
        }
        return set;
    }

    static class Entry<K, V> {
        K key;
        V value;
        int hashCode;
        Entry next;

        public Entry(K kk, V vv, int hc) {
            key = kk;
            value = vv;
            hashCode = hc;
        }
    }

}
