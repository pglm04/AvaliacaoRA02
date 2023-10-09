class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public int getData() {
        return data;
    }
}

public class BinaryTree {
    
    TreeNode root;
    
    public BinaryTree() {
        this.root = null;
    }
    
    public void insert(int data) {
        root = insertRec(root, data);
    }
    
    private TreeNode insertRec(TreeNode root, int data) {
        if (root == null) {
            root = new TreeNode(data);
            return root;
        }
        
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }
        
        return root;
    }
    
    public boolean remove(int data) {
        if (root == null) {
            return false;
        }
    
        TreeNode focusNode = root;
        TreeNode parent = root;
        boolean isItALeftChild = true;
    
        while (focusNode.data != data) {
            parent = focusNode;
    
            if (data < focusNode.data) {
                isItALeftChild = true;
                focusNode = focusNode.left;
            } else {
                isItALeftChild = false;
                focusNode = focusNode.right;
            }
    
            if (focusNode == null) {
                return false; 
            }
        }
    
        
        if (focusNode.left != null && focusNode.right != null) {
            TreeNode replacement = getReplacementNode(focusNode);
            if (focusNode == root) {
                root = replacement;
            } else if (isItALeftChild) {
                parent.left = replacement;
            } else {
                parent.right = replacement;
            }
            replacement.left = focusNode.left;
        } else if (focusNode.left != null) {
            if (focusNode == root) {
                root = focusNode.left;
            } else if (isItALeftChild) {
                parent.left = focusNode.left;
            } else {
                parent.right = focusNode.left;
            }
        } else if (focusNode.right != null) {
            if (focusNode == root) {
                root = focusNode.right;
            } else if (isItALeftChild) {
                parent.left = focusNode.right;
            } else {
                parent.right = focusNode.right;
            }
        } else {
            if (focusNode == root) {
                root = null;
            } else if (isItALeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
    
        return true;
    }
    
    public TreeNode getReplacementNode(TreeNode replacedNode) {
        TreeNode replacementParent = replacedNode;
        TreeNode replacement = replacedNode;
        TreeNode focusNode = replacedNode.right;
    
        while (focusNode != null) {
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.left;
        }
    
        if (replacement != replacedNode.right) {
            replacementParent.left = replacement.right;
            replacement.right = replacedNode.right;
        }
    
        return replacement;
    }
    
    
    public boolean search(int data) {
        return searchRec(root, data);
    }
    
    private boolean searchRec(TreeNode root, int data) {
        if (root == null) {
            return false;
        }
        
        if (data == root.data) {
            return true;
        }
        
        if (data < root.data) {
            return searchRec(root.left, data);
        }
        
        return searchRec(root.right, data);
    }

    public TreeNode getRoot() {
        return root;
    }
}
