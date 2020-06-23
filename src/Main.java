import static java.lang.Integer.max;
import static java.lang.Integer.min;
public class Main {

    public static void main(String[] args) {
	// write your code here
        Integer [ ] num = {-3, -2, 7, 4, 5, 6, 8 ,9 , -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(num, (a , b) -> a*b );
//        SegmentTree<Integer> segmentTree = new SegmentTree<>(num, (a , b) -> min(a,b) );
//        SegmentTree<Integer> segmentTree = new SegmentTree<>(num, (a , b) -> max(a,b) );
//        SegmentTree<Integer> segmentTree = new SegmentTree<>(num, (a , b) -> a+b );
//        SegmentTree<Integer> segmentTree = new SegmentTree<>(num, new Merger<Integer>() {
//            @Override
//            public Integer merge(Integer a, Integer b) {
//                return a + b;
//            }
//        });
        System.out.println(segmentTree);
        System.out.println(segmentTree.query(2,5));
        System.out.println(segmentTree.query(0,8));
        System.out.println(segmentTree.query(8,8));
    }
}
