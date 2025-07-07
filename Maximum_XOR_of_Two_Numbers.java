import java.util.*;
class Solution {
    public class Node{
        Node links[]=new Node[2];
        boolean containsKey(int x){
            return links[x]!=null;
        }
        void put(int x,Node node){
            links[x]=node;
        }
        Node get(int x){
            return links[x];
        }
    }
    public class Trie{
        public Node root;
        Trie(){
            root=new Node();
        }
        void insert(int num){
            Node node=root;
            for(int i=31;i>=0;i--){
                int x=((num>>i)&1);
                if(!node.containsKey(x)){
                    node.put(x,new Node());
                }
                node=node.get(x);
            }
        }
        int getMax(int num){
            Node node=root;
            int max=0;
            for(int i=31;i>=0;i--){
                int x=((num>>i)&1);
                if(node.containsKey(1-x)){
                    max=max|(1<<i);
                    node=node.get(1-x);
                }
                else node=node.get(x);
            }
            return max;
        }
    }
    public int findMaximumXOR(int[] nums) {
        int n=nums.length;
        Trie t=new Trie();
        for(int i=0;i<n;i++){
            t.insert(nums[i]);
        }
        int max=0;
        for(int i=0;i<n;i++){
            max=Math.max(max,t.getMax(nums[i]));
        }
        return max;
    }
  public static void main(String []args){
      Scanner sc=new Scanner(System.in);
      int n=sc.nextInt();
      int nums[]=new int[n];
      for(int i=0;i<n;i++){
        nums[i]=sc.nextInt();
      }
      System.out.println(findMaximumXOR(nums));
  }
}
