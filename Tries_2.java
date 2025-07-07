import java.util.* ;
public class Trie {
    public class Node{
        Node links[];
        int cntEndWith;
        int cntPrefix;
        Node(){
            links=new Node[26];
            cntEndWith=0;
            cntPrefix=0;
        }
        boolean containsKey(char c){
            return links[c-'a']!=null;
        }
        void put(char c,Node node){
            links[c-'a']=node;
        }
        Node get(char c){
            return links[c-'a'];
        }
        void increasePrefix(){
            cntPrefix++;
        }
        void increaseEnd(){
            cntEndWith++;
        }
        void reducePrefix(){
            cntPrefix--;
        }
        void reduceEnd(){
            cntEndWith--;
        }
    }
    public Node root;
    public Trie() {
        root=new Node();
    }

    public void insert(String word) {
        Node node=root;
        int n=word.length();
        for(int i=0;i<n;i++){
            char c=word.charAt(i);
            if(!node.containsKey(c)){
                node.put(c,new Node());
            }
            node.increasePrefix();
            node=node.get(c);
        }
        node.increaseEnd();        
    }

    public int countWordsEqualTo(String word) {
        Node node=root;
        int n=word.length();
        for(int i=0;i<n;i++){
            char c=word.charAt(i);
            if(!node.containsKey(c)){
                return 0;
            }
            node=node.get(c);
        }
        return node.cntEndWith;
    }

    public int countWordsStartingWith(String word) {
        Node node=root;
        int n=word.length();
        for(int i=0;i<n;i++){
            char c=word.charAt(i);
            if(!node.containsKey(c)){
                return 0;
            }
            node=node.get(c);
        }
        return node.cntPrefix;
    }

    public void erase(String word) {
        Node node=root;
        int n=word.length();
        for(int i=0;i<n;i++){
            char c=word.charAt(i);
            if(node.containsKey(c)){
                node.reducePrefix();
                node=node.get(c);
            }           
        }
        node.reducePrefix();        
    }
}
