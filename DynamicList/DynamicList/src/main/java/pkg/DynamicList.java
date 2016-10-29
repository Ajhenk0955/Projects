public class DynamicList {
	private DynamicNode head;

	public DynamicList() {
		head = null;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void insertFirst(Object x) {
		System.out.println("Insert " + x);
		DynamicNode q = new DynamicNode(x, null);
		if (!isEmpty())
			q.setNext(head);
		head = q;

	}

	public void printList() {
		System.out.println();
		if (!isEmpty()) {
			DynamicNode p = null;
			System.out.println("List: ");
			for (p = head; p != null; p = p.getNext()) {
				System.out.print(p.getInfo() + (" "));
			}
		} else {
			System.out.println("The list is empty");
		}
	}

	public DynamicNode search(Object x) {
		// returns node where first found x, null otherwise
		DynamicNode p = null;
		DynamicNode q = null;
		if (!isEmpty())
			for (p = head; p != null; p = p.getNext()) {
				if (x.equals(p.getInfo())) {
					return q = p;
				}
			}
		return q;
	}

	public void removeX(Object x) {
		// removes all matching nodes
		DynamicNode p = null;
		DynamicNode q = null;
		if (!isEmpty()) {
			for (p = head; p != null; p = p.getNext()) {
				if (x.equals(p.getInfo())) {
					// match was found, if head just change head to next value
					if (p == head) {
						head = p.getNext();
						return;
					}
					q.setNext(p.getNext());
					return;
				}
				// save previous node
				q = p;
			}
		} else {
			System.out.println("The list is empty");
		}
	}

	/**
	 * Appends all elements in the parameter list othrList to the end of this
	 * list. Returns true if the list was changed, false otherwise. Please note
	 * that NO new list is created. Also, it is wrong to (repeatedly) insert new
	 * nodes to the list. *****for the 1 absurd case: we loop to the end and
	 * compare ends DynamicNode end = null; for (end = otherList.head;
	 * end.getNext() != null; end = end .getNext()) { } if (p.equals(end))
	 * return false;
	 */
	public boolean addAllElements(DynamicList otherList) {
		DynamicNode p = null;
		System.out.println("Add all elements");

		// if list.addAllElements(list) it breaks (loops)
		// the end restarts
		// while on last element of otherList if same change to null
		if (!otherList.isEmpty()) {
			if (isEmpty()) {
				head = otherList.head;
				return true;
			}
			for (p = head; p.getNext() != null; p = p.getNext()) {
			}
			p.setNext(otherList.head);
			return true;

		}
		return false;

	}

	/**
	 * Reverses the order of the nodes in the linked-list Do not use
	 * insertFirst, insertAfter, deleteFirst, or deleteAfter make recursive??
	 * swap swap swap n-1factorial? --> --> --> --> to <-- <-- <--<--
	 */
	public void reverse() {
		if (isEmpty()) {
			System.out.println("List is empty");
			return;
		}

		System.out.println("reverse");
		DynamicNode p = head;
		DynamicNode q = null;
		DynamicNode temp;
		while (p != null) {
			temp = p.getNext();
			p.setNext(q);
			q = p;
			p = temp;
		}
		head = q;
	}

	/**
	 * Searches the list for a node equal to parameter x, and returns the list
	 * node containing x, if x is found. If x is not found in the list, it
	 * inserts x in the list and returns the node containing x.
	 * 
	 */
	public DynamicNode searchInsert(Object x) {
		System.out.println("search insert");
		DynamicNode p = null;
		DynamicNode newNode = new DynamicNode(x, null);

		if (isEmpty()) { // new list, no head yet.
			head = newNode;
			return newNode;
		}
		for (p = head; p.getNext() != null; p = p.getNext()) {
			if (x.equals(p.getInfo())) {
				return p;
			}
		}
		// insert last if null
		p.setNext(newNode);
		return newNode;
	}

	/**
	 * Deletes the middle node in the list IF it exists. If the list has an EVEN
	 * number of nodes, there is NO middle node. Constraint: you cannot use a
	 * COUNTER. Given a list a->b->c->d->e, c is deleted Given a list
	 * a->b->c->d, nothing is deleted.
	 * 
	 * Returns: info in the middle node if it exists; null otherwise
	 * 
	 */
	public Object deleteMid() {
		// uses a fast pointer and a slow, half as fast
		DynamicNode slow = head;
		DynamicNode fast = head;
		// fast will equal null if it skips the last one
		// meaning no middle

		if (!isEmpty()) {
			while (fast != null && fast.getNext() != null) {
				fast = fast.getNext().getNext();
				slow = slow.getNext();
			}
			if (fast != null) {
				removeX(slow.getInfo());
				return slow.getInfo();
			}
		}
		System.out.println("No middle or empty");
		return fast;

	}


	/*
	This is only for testing :)
	*/
	public static void main(String args[]) {
		DynamicList listy = new DynamicList();

		String a = "1";
		String b = "2";
		String c = "7";
		String d = "4";
		String e = "5";

		listy.insertFirst(b);
		listy.insertFirst(a);

		listy.printList();
		listy.searchInsert(c);
		listy.printList();

		DynamicList tater = new DynamicList();
		tater.searchInsert(a);
		tater.printList();

		DynamicList potato = new DynamicList();
		potato.insertFirst(d);
		potato.insertFirst(e);

		listy.printList();
		potato.printList();
		listy.addAllElements(potato);
		listy.printList();

		listy.printList();
		listy.reverse();
		listy.printList();

		DynamicList empty = new DynamicList();
		empty.reverse();
		empty.printList();

		listy.printList();
		listy.deleteMid();
		listy.printList();

		listy.printList();
		listy.deleteMid();
		listy.printList();

		empty.deleteMid();
		empty.printList();

	}
}