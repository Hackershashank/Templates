import java.util.*;
public class LazyPropagation {
    private static long[] segTree;
    private static long[] lazy;
    private static void build(int idx,int l,int r){
        if(l==r){
            segTree[idx]=0;
            return;
        }
        int mid=l+(r-l)/2;
        build(2*idx+1,l,mid);
        build(2*idx+2,mid+1,r);
        segTree[idx]=segTree[2*idx+1]+segTree[2*idx+2];
    }
    private static void update(int left,int right,long val,int idx,int l,int r){
        if(lazy[idx]!=0){
            segTree[idx]+=(r-l+1)*lazy[idx];
            if(l!=r){
                lazy[2*idx+1]+=lazy[idx];
                lazy[2*idx+2]+=lazy[idx];
            }
            lazy[idx]=0;
        }
        if(r<left || l>right) return ;
        if(left<=l && r<=right){
            segTree[idx]+=(r-l+1)*val;
            if(l!=r){
                lazy[2*idx+1]+=val;
                lazy[2*idx+2]+=val;
            }
            return ;
        }
        int mid=l+(r-l)/2;
        update(left,right,val,2*idx+1,l,mid);
        update(left,right,val,2*idx+2,mid+1,r);
        segTree[idx]=segTree[2*idx+1]+segTree[2*idx+2];
    }
    private static long query(int left,int right,int idx,int l,int r){
        if(lazy[idx]!=0){
            segTree[idx]+=(r-l+1)*lazy[idx];
            if(l!=r){
                lazy[2*idx+1]+=lazy[idx];
                lazy[2*idx+2]+=lazy[idx];
            }
            lazy[idx]=0;
        }
        if(r<left || l>right) return 0;
        if(left<=l && r<=right) return segTree[idx];
        int mid=l+(r-l)/2;
        return query(left,right,2*idx+1,l,mid)+query(left,right,2*idx+2,mid+1,r);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int q=sc.nextInt();
        segTree=new long[4*n];
        lazy=new long[4*n];
        build(0,0,n-1);
        while(q-->0){
            int type=sc.nextInt();
            if(type==1){
                int l=sc.nextInt();
                int r=sc.nextInt();
                long v=sc.nextLong();
                update(l,r,v,0,0,n-1);
            }
            else{
                int l=sc.nextInt();
                int r=sc.nextInt();
                System.out.println(query(l,r,0,0,n-1));
            }
        }
    }
}
