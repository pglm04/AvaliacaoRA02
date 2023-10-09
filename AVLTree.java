class AVLNode {
    int data;
    AVLNode left;
    AVLNode right;
    int height;
    
    public AVLNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}

public class AVLTree {
    AVLNode root;
    
    public AVLTree() {
        this.root = null;
    }
    
    private int height(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }
    
    private int balanceFactor(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }
    
    public void insert(int data) {
        root = insertRec(root, data);
    }
    
    private AVLNode insertRec(AVLNode root, int data) {
        if (root == null) {
            return new AVLNode(data);
        }
        
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        } else {
            return root; 
        }
        
        root.height = 1 + Math.max(height(root.left), height(root.right));
        
        int balance = balanceFactor(root);
        
        if (balance > 1 && data < root.left.data) {
            return rotateRight(root);
        }
        
        if (balance < -1 && data > root.right.data) {
            return rotateLeft(root);
        }
        
        if (balance > 1 && data > root.left.data) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }
        
        if (balance < -1 && data < root.right.data) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }
        
        return root;
    }
    
    private AVLNode rotateLeft(AVLNode node) {
        AVLNode rightChild = node.right;
        AVLNode leftOfRight = rightChild.left;
        
        rightChild.left = node;
        node.right = leftOfRight;
        
        node.height = 1 + Math.max(height(node.left), height(node.right));
        rightChild.height = 1 + Math.max(height(rightChild.left), height(rightChild.right));
        
        return rightChild;
    }
    
    private AVLNode rotateRight(AVLNode node) {
        AVLNode leftChild = node.left;
        AVLNode rightOfLeft = leftChild.right;
        
        leftChild.right = node;
        node.left = rightOfLeft;
        
        node.height = 1 + Math.max(height(node.left), height(node.right));
        leftChild.height = 1 + Math.max(height(leftChild.left), height(leftChild.right));
        
        return leftChild;
    }
    
    public void remove(int data) {
        root = removeRec(root, data);
    }
    
    private AVLNode removeRec(AVLNode root, int data) {
        if (root == null) {
            return root;
        }
        
        if (data < root.data) {
            root.left = removeRec(root.left, data);
        } else if (data > root.data) {
            root.right = removeRec(root.right, data);
        } else {
            if (root.left == null || root.right == null) {
                AVLNode temp = (root.left != null) ? root.left : root.right;
                
                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                AVLNode temp = minValueNode(root.right);
                
                root.data = temp.data;
                
                root.right = removeRec(root.right, temp.data);
            }
        }
        
        if (root == null) {
            return root;
        }
        
        root.height = 1 + Math.max(height(root.left), height(root.right));
        
        int balance = balanceFactor(root);
        
        if (balance > 1 && balanceFactor(root.left) >= 0) {
            return rotateRight(root);
        }

        if (balance > 1 && balanceFactor(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }
        
        if (balance < -1 && balanceFactor(root.right) <= 0) {
            return rotateLeft(root);
        }
        
        if (balance < -1 && balanceFactor(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }
        
        return root;
    }
    
    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
    
    public boolean search(int data) {
        return searchRec(root, data);
    }
    
    private boolean searchRec(AVLNode root, int data) {
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
}
