package com.fengliguantou.miaoshaproject.mq;

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return ToBST(nums,0,nums.length-1);
    }
    public static TreeNode ToBST(int nums[],int left,int right){
        if(left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(mid);
        root.left = ToBST(nums, left, mid - 1);
        root.right = ToBST(nums, mid + 1, right);
        return root;
    }

}