import java.util.HashMap;

// suppose each page stores just an integer
public class MemorySpace {

	int capacity;
	HashMap<Integer, Node> map = new HashMap<Integer, Node>();
	Node head = null;
	Node tail = null;

	/**
	 * capacity indicates the maximum number of pages in the physical memory
	 * space
	 *
	 * @param capacity
	 *            suppose it is always positive
	 */
	public MemorySpace(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Finds the content in a page given the number.
	 *
	 * Returns -1 if the page is not in the physical memory.
	 *
	 * The time complexity is O(1)
	 *
	 * @param page
	 * @return
	 */
	public int read(int page) {
		if (map.containsKey(page)) {
			Node temp = map.get(page);
			update(page, temp.content);
			return temp.content;
		}
		return -1;
	}

	/**
	 * Sets the content for a page given its page number. If the page is in the
	 * physical memory, overwrite its current content. Otherwise, add a new page
	 * in the physical memory. If the capacity was already reached, remove the
	 * one that was last referenced in the farthest past.
	 *
	 * The time complexity is O(1).
	 *
	 * @param page
	 * @param content
	 */
	public void update(int page, int content) {
		if (map.containsKey(page)) {
			Node temp = map.get(page);
			remove(temp);
			setHead(temp);
		} else {
			set(page, content);
		}
	}

	/*
	 * if called, then page needs to be set
	 */
	private void set(int page, int content) {
		Node temp = new Node(page, content);
		if (map.size() >= capacity) {
			map.remove(tail.page);
			remove(tail);
			setHead(temp);

		} else {
			setHead(temp);
		}

		map.put(page, temp);
	}

	/*
	 * sets the head and updates pointers
	 */
	private void setHead(Node temp) {
		temp.after = head;
		temp.before = null;

		if (head != null)
			head.before = temp;

		head = temp;

		if (tail == null)
			tail = head;
	}

	/*
	 * Removes nodes :D and updates pointers
	 */
	private void remove(Node temp) {
		if (temp.before != null) {
			temp.before.after = temp.after;
		} else {
			head = temp.after;
		}

		if (temp.after != null) {
			temp.after.before = temp.before;
		} else {
			tail = temp.before;
		}

	}

}
