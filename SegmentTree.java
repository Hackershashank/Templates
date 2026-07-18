import java.util.Scanner;

public class SegmentTree {
    private static long[] segTree;
    private static long[] arr;
    private static void build(int idx,int l,int r){
        if(l==r){
            segTree[idx]=arr[l];
            return ;
        }
        int mid=l+(r-l)/2;
        build(2*idx+1,l,mid);
        build(2*idx+2,mid+1,r);
        segTree[idx]=segTree[2*idx+1]+segTree[2*idx+2];
    }
    private static void update(int index,long val,int idx,int l,int r){
        if(l==r){
            segTree[idx]=val;
            arr[index]=val;
            return ;
        }
        int mid=l+(r-l)/2;
        if(index<=mid) update(index,val,2*idx+1,l,mid);
        else update(index,val,2*idx+2,mid+1,r);
        segTree[idx]=segTree[2*idx+1]+segTree[2*idx+2];
    }
    private static long query(int left,int right,int idx,int l,int r){
        if(r<left || l>right) return 0;
        if(left<=l && r<=right) return segTree[idx];
        int mid=l+(r-l)/2;
        return query(left,right,2*idx+1,l,mid)+query(left,right,2*idx+2,mid+1,r);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int q=sc.nextInt();
        long[] nums=new long[n];
        for(int i=0;i<n;i++) nums[i]=sc.nextLong();
        arr=nums.clone();
        segTree=new long[4*n];
        build(0,0,n-1);
        while(q-->0){
            int type=sc.nextInt();
            if(type==1){
                int idx=sc.nextInt();
                long val=sc.nextLong();
                update(idx,val,0,0,n-1);
            }
            else{
                int l=sc.nextInt();
                int r=sc.nextInt();
                System.out.println(query(l,r,0,0,n-1));
            }
        }
    }
}
