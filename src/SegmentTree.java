import java.util.Objects;
// SegmentTree does not have add, remove operations
public class SegmentTree <E> {
    private E [] tree;
    private E [] data;
    private Merger<E> merger;

    public SegmentTree(E [] arr, Merger<E> merger) {
        data = (E[]) new Object[arr.length];
        tree = (E[]) new Object[arr.length * 4];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        this.merger = merger;
        buildSegmentTree(0,0,data.length-1);
    }
    public int getSize(){
        return data.length;
    }
    public E get(int index) {
        if(index <0 || index >= data.length)
            throw new IllegalArgumentException("Index is out of range, get failed");
        return data[index];
    }
    private int leftTree(int index){
        return (2*index + 1);
    }
    private int rightTree(int index){
        return (2*index + 2);
    }
    private int parent(int index){
        return (index-1)/2 ;
    }
    // build segmentTree @ treeIndex, with range left to right
    private void buildSegmentTree (int treeIndex, int l, int r){
        if(l==r){
            tree[treeIndex]= data [l];
            return;
        }

        int left = leftTree((treeIndex));
        int right = rightTree((treeIndex));
        int mid = l/2 + r/2;
        buildSegmentTree(left, l, mid);
        buildSegmentTree(right,mid+1, r);

        tree[treeIndex] = merger.merge(tree[left], tree[right]);
    }
    public E query(int queryL, int queryR){
        if(queryL <0 || queryL >=data.length || queryR <0 || queryR >=data.length || queryL > queryR)
            throw new IllegalArgumentException("invalid range, query failed");
        return query(0, 0,data.length-1, queryL, queryR);
    }
    private E query(int treeIndex, int l, int r, int queryL, int queryR){
        if(l==queryL && r==queryR){
            return tree[treeIndex];
        }

        int left = leftTree((treeIndex));
        int right = rightTree((treeIndex));
        int mid = l/2 + r/2;
        if(queryL>mid){
            return query(right, mid+1, r,queryL,queryR );
        }else if(queryR<=mid)
            return query(left, l, mid,queryL,queryR );
        else{
            E leftResult = query(left, l,mid,queryL,mid);
            E rightResult = query(right,mid+1,r,mid+1,queryR);
            return merger.merge(leftResult,rightResult);
        }


    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i< tree.length; i++) {
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");
            if (i != tree.length - 1)
                res.append(",");
        }
        res.append("]");
        return res.toString();
    }

}
