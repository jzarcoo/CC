/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    /**
    * Given the root of a binary tree, invert the tree, and return its root.
    */
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode treeNode = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = treeNode;
        return root;
    }
}
