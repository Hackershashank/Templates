import java.util.*;
class Trie {
    class Node{
        Node links[];
        boolean flag;
        Node(){
            links=new Node[26];
            flag=false;
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
        void setEnd(){
            flag=true;
        }
        boolean getEnd(){
            return flag;
        }
    }
    public Node root;
    public Trie() {
        root=new Node();
    }
    
    public void insert(String word) {
        int n=word.length();
        Node node=root;
        for(int i=0;i<n;i++){
            char c=word.charAt(i);
            if(!node.containsKey(c)){
                node.put(c,new Node());
            }
            node=node.get(c);
        }
        node.setEnd();
    }
    
    public boolean search(String word) {
        int n=word.length();
        Node node=root;
        for(int i=0;i<n;i++){
            char c=word.charAt(i);
            if(!node.containsKey(c)){
                return false;
            }
            node=node.get(c);
        }
        return node.getEnd();
    }
    
    public boolean startsWith(String prefix) {
        int n=prefix.length();
        Node node=root;
        for(int i=0;i<n;i++){
            char c=prefix.charAt(i);
            if(!node.containsKey(c)){
                return false;
            }
            node=node.get(c);
        }
        return true;
    }
}
