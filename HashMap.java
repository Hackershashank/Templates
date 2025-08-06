import java.io.*;
import java.util.*;

public class Main {
    static class MyHashMap {
        static class Node {
            String key, value;
            Node next;

            Node(String key, String value) {
                this.key = key;
                this.value = value;
            }
        }

        private Node[] table;
        private int capacity;
        private int size;
        private final double LOAD_FACTOR = 0.75;

        public MyHashMap() {
            this.capacity = 16;
            this.table = new Node[capacity];
            this.size = 0;
        }

        private long hash(String key) {
            long hash = 0;
            long p = 127; // better base for less collisions
            long pow = 1;
            for (int i = 0; i < key.length(); i++) {
                hash += (key.charAt(i) - 'a' + 1) * pow;
                pow *= p;
            }
            return hash & Long.MAX_VALUE; // prevent negative values
        }

        private int getIndex(String key) {
            return (int)(hash(key) & (capacity - 1));
        }

        private void rehash() {
            Node[] oldTable = table;
            capacity *= 2;
            table = new Node[capacity];
            size = 0;

            for (Node head : oldTable) {
                while (head != null) {
                    put(head.key, head.value, false);
                    head = head.next;
                }
            }
        }

        private void put(String key, String value, boolean countSize) {
            int idx = getIndex(key);
            Node node = table[idx];
            while (node != null) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
                node = node.next;
            }

            Node newNode = new Node(key, value);
            newNode.next = table[idx];
            table[idx] = newNode;

            if (countSize) {
                size++;
                if ((double) size / capacity > LOAD_FACTOR) {
                    rehash();
                }
            }
        }

        public void put(String key, String value) {
            put(key, value, true);
        }

        public String get(String key) {
            int idx = getIndex(key);
            Node node = table[idx];
            while (node != null) {
                if (node.key.equals(key)) {
                    return node.value;
                }
                node = node.next;
            }
            return "NULL";
        }

        public String remove(String key) {
            int idx = getIndex(key);
            Node node = table[idx];
            Node prev = null;
            while (node != null) {
                if (node.key.equals(key)) {
                    String val = node.value;
                    if (prev == null) {
                        table[idx] = node.next;
                    } else {
                        prev.next = node.next;
                    }
                    size--;
                    return val;
                }
                prev = node;
                node = node.next;
            }
            return "NULL";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        int Q = Integer.parseInt(br.readLine());

        MyHashMap map = new MyHashMap();

        while (Q-- > 0) {
            String line = br.readLine();
            String[] parts = line.split(" ");

            String op = parts[0];
            String key = parts[1];

            if (op.equals("PUT")) {
                String value = parts[2];
                map.put(key, value);
            } else if (op.equals("GET")) {
                out.println(map.get(key));
            } else if (op.equals("REMOVE")) {
                out.println(map.remove(key));
            }
        }

        out.flush();
    }
}
