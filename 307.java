import java.util.HashMap;
import java.util.Map;
// https://leetcode.com/problems/range-sum-query-mutable/
public class NumArray {

	class Node {
		int start;
		int end;
		int sum;
		Node left;
		Node right;
		Node parent;

		public Node(int s, int e, int su) {
			start = s;
			end = e;
			sum = su;
		}
	}

	Map<Integer, Node> nodeTable = new HashMap<>();
	Node root;

	public NumArray(int[] nums) {
		root = constructTree(nums, 0, nums.length);
	}

	Node constructTree(int[] nums, int start, int end) {
		if (end <= start)
			return null;
		if (start == end - 1) {
			Node leaf = new Node(start, start, nums[start]);
			nodeTable.put(start, leaf);
			return leaf;
		}
		int mid = (start + end) / 2;
		Node left = constructTree(nums, start, mid);
		Node right = constructTree(nums, mid, end);
		Node cur = new Node(start, end - 1, left.sum + right.sum);
		cur.left = left;
		cur.right = right;
		left.parent = cur;
		right.parent = cur;
		return cur;
	}

	void update(int i, int val) {
		Node cur = nodeTable.get(i);
		int diff = val - cur.sum;
		while(cur != null) {
			cur.sum += diff;
			cur = cur.parent;
		}
	}

	public int sumRange(int i, int j) {
		if(i > j) {
			int temp = j;
			j = i;
			i = temp;
		}
		return sum(root, i, j);
	}
	
	int sum(Node r, int i, int j) {
		if(r == null)	return 0;
		if(i == r.start && j == r.end)	return r.sum;
		if(r.left == null || r.left.end < i) {
			return sum(r.right, i, j);
		}
		if(r.right == null || r.right.start > j) {
			return sum(r.left, i, j);
		}
		return sum(r.left, i, r.left.end) + sum(r.right, r.right.start, j);
	}
}

// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);
