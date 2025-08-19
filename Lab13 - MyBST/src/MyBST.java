public class MyBST {
	private class BSTNode {
		Integer val;
		BSTNode left, right;

		public BSTNode(Integer val) {
			this.val = val;
			left = right = null;
		}

		public String toString () {
			return "" + this.val; 
		}

	}
	
	BSTNode root;

	public int size() {
		return sizeHelper(root);
	}

	private int sizeHelper(BSTNode node) {
		if(node == null) {
			return 0;
		}
		return 1 + sizeHelper(node.left) + sizeHelper(node.right);
	}

	public void insert(Integer n) {
		if(root == null) {
			root = new BSTNode(n);
		}
		else {
			insertHelper(root, n);
		}
	}

	private void insertHelper(BSTNode node, Integer n) {
		if(node != null) {
			if(n > node.val && node.right != null) {
				insertHelper(node.right, n);
			}
			else if(n < node.val && node.left != null) {
				insertHelper(node.left, n);
			}
			else {
				boolean lessThan = (node.val > n) ? true : false;
				if(lessThan) {
					node.left = new BSTNode(n);
				}
				else {
					node.right = new BSTNode(n);
				}
			}
		}
	}

	public boolean contains(Integer n) {
		return containsHelper(root, n);
	}

	private boolean containsHelper(BSTNode node, Integer n) {
		if(node == null) {
			return false;
		}
		
		if(node.val == n) {
			return true;
		}
		
		return containsHelper(node.left, n) || containsHelper(node.right, n);
	}
	
	public Integer getMax(BSTNode node) {
		while (node.right != null) {
	        node = node.right;
	    }
	    return node.val;
	}
	
	public Integer getMin(BSTNode node) {
		while (node.left != null) {
	        node = node.left;
	    }
	    return node.val;
	}
	
	public void delete(Integer n) {
		if(this.contains(n))
			deleteHelper(null, root, n);
	}
	
	private void deleteHelper(BSTNode parent, BSTNode node, Integer n) {
	    if (node == null) {
	        return;
	    }

	    if (node.val > n) {
	        deleteHelper(node, node.left, n);
	    } 
	    else if (node.val < n) {
	        deleteHelper(node, node.right, n);
	    } 
	    else {
	        // Node with no children
	        if (node.left == null && node.right == null) {
	            if (parent.left == node) {
	                parent.left = null;
	            }
	            else {
	                parent.right = null;
	            }
	        }
	        // Node with one child
	        else if (node.left == null) {
	            // only right 
	            if (parent.left == node) {
	                parent.left = node.right;
	            } else {
	                parent.right = node.right;
	            }
	        } else if (node.right == null) {
	            // only left 
	            if (parent.left == node) {
	                parent.left = node.left;
	            } else {
	                parent.right = node.left;
	            }
	        }
	        // Node with two children
	        else {
	            int max = getMax(node.left);
	            deleteHelper(node, node.left, max);
	            node.val = max;
	        }
	    }
	}
	
	public void inOrder() {
		inOrderHelper(root);
	}
	
	private void inOrderHelper(BSTNode node) {
		if(node != null) {
			inOrderHelper(node.left);
			System.out.print(node.val + " ");
			inOrderHelper(node.right);
		}
	}
	
	public void print() {
		printHelper(root, 0);
	}
	
	private void printHelper(BSTNode node, int depth) {
		if(node != null) {
			printHelper(node.right, depth + 1);
			String out = "" + node.val;
			for(int i = 0; i < depth; i++) {
				out = "    " + out;
			}		
			System.out.println(out);
			printHelper(node.left, depth + 1);
		}
	}
}