/**
Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.

 

Example 1:

Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]

Example 2:

Input: preorder = [-1], inorder = [-1]
Output: [-1]

 

Constraints:

    1 <= preorder.length <= 3000
    inorder.length == preorder.length
    -3000 <= preorder[i], inorder[i] <= 3000
    preorder and inorder consist of unique values.
    Each value of inorder also appears in preorder.
    preorder is guaranteed to be the preorder traversal of the tree.
    inorder is guaranteed to be the inorder traversal of the tree.

*/
import java.util.HashMap;
import java.util.Map;
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class ConstructBinaryTreefromPreorderandInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> idx = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            idx.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1,
                         inorder, 0, inorder.length - 1, 
                         idx);
    }
    
    private TreeNode buildTree(int[] preorder, int iniPre, int finPre,
                               int[] inorder, int iniIn, int finIn, 
                               Map<Integer,Integer> idx) {
        if (finPre < iniPre || finIn < iniIn)
            return null;
        int valRoot = preorder[iniPre];
        int idxInRoot = idx.get(valRoot);
        int numLeftNodes = idxInRoot - iniIn;
        return new TreeNode(valRoot, 
                            buildTree(preorder, iniPre + 1, iniPre + numLeftNodes,
                                      inorder, iniIn, idxInRoot - 1,
                                      idx),
                            buildTree(preorder, iniPre + numLeftNodes + 1, finPre,
                                      inorder, idxInRoot + 1, finIn,
                                      idx)
                            );
    }
}
