import java.util.Comparator;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses Mergesort algorithm.
 *
 * @author CS221
 */
public class Sort
{	
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface. 
	 * As configured, uses WrappedDLL. Must be changed if using 
	 * your own IUDoubleLinkedList class. 
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() 
	{
		return new IUDoubleLinkedList<T>(); //TODO: replace with your IUDoubleLinkedList for extra-credit
	}
	
	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @see IndexedUnsortedList 
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) 
	{
		mergesort(list);
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 * @see IndexedUnsortedList 
	 */
	public static <T> void sort(IndexedUnsortedList <T> list, Comparator<T> c) 
	{
		mergesort(list, c);
	}
	
	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface, 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 */
	private static <T extends Comparable<T>> void mergesort(IndexedUnsortedList<T> list)
	{
		// TODO: Implement recursive mergesort algorithm
		int length = list.size();
		if (length < 2) {
			return;
		}
		int mid = length/2;
		IndexedUnsortedList<Integer> leftList = newList();
		IndexedUnsortedList<Integer> rightList = newList();
		
		for (int i=0; i<mid; i++) {
			leftList.add((int)list.removeFirst());
		}
		for (int i= 0; i<length-mid; i++) {
			rightList.add((int)list.removeFirst());
		}
		
		mergesort(leftList);
		mergesort(rightList);
		
		merge(leftList, rightList, list);
	}
	
	/**
	 * Algorithm to merge and sort 2 halves of a list, used by the
	 * mergesort function.
	 * @param <T>
	 * 
	 * @param leftList the left half of the list to be merged
	 * @param rightList the right half of the list to be merged
	 * @param list the original list for values to be placed
	 */
	@SuppressWarnings("unchecked")
	private static <T> void merge(IndexedUnsortedList<Integer>leftList, IndexedUnsortedList<Integer>rightList, IndexedUnsortedList<T>list) {
		int leftLength = leftList.size();
		int rightLength = rightList.size();
		
		int i=0, j=0, k=0;
		while (i < leftLength && j < rightLength) {
			if (leftList.get(0) <= rightList.get(0)) {
				list.add((T)leftList.removeFirst());
				i++;
			}
			else {
				list.add((T)rightList.removeFirst());
				j++;
			}
		}
		
		while (i < leftLength) {
			list.add((T)leftList.removeFirst());
			k++;
			i++;
		}
		while (j < rightLength) {
			list.add((T)rightList.removeFirst());
			k++;
			j++;
		}
	}
		
	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface,
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void mergesort(IndexedUnsortedList<T> list, Comparator<T> c)
	{
		// TODO: Implement recursive mergesort algorithm using Comparator
		int length = list.size();
		if (length < 2) {
			return;
		}
		int mid = length/2;
		IndexedUnsortedList<T> leftList = newList();
		IndexedUnsortedList<T> rightList = newList();
		
		for (int i=0; i<mid; i++) {
			leftList.add(list.removeFirst());
		}
		for (int i= 0; i<length-mid; i++) {
			rightList.add(list.removeFirst());
		}
		
		mergesort(leftList, c);
		mergesort(rightList, c);
		
		compMerge(leftList, rightList, list, c);
	}
	
	/**
	 * Algorithm to merge and sort 2 halves of a list, used by the
	 * mergesort function.
	 * @param <T>
	 * 
	 * @param leftList the left half of the list to be merged
	 * @param rightList the right half of the list to be merged
	 * @param list the original list for values to be placed
	 */
	private static <T> void compMerge(IndexedUnsortedList<T>leftList, IndexedUnsortedList<T>rightList, IndexedUnsortedList<T>list, Comparator<T> c) {
		int leftLength = leftList.size();
		int rightLength = rightList.size();
		
		int i=0, j=0;
		while (i < leftLength && j < rightLength) {
			if (c.compare(leftList.get(0), rightList.get(0)) <= 0) {
				list.add((T)leftList.removeFirst());
				i++;
			}
			else {
				list.add((T)rightList.removeFirst());
				j++;
			}
		}
		
		while (i < leftLength) {
			list.add((T)leftList.removeFirst());

			i++;
		}
		while (j < rightLength) {
			list.add((T)rightList.removeFirst());
			j++;
		}
	}
	
}
