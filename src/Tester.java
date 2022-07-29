
public class Tester {

	public static void main(String[] args) {
		NodeAccessibleLinkedList<Integer> list = new NodeAccessibleLinkedList<Integer>();
		list.addFirst(1);
		NodeAccessibleLinkedList.Node<Integer> node = list.getFirst();
		NodeAccessibleLinkedList.Node<Integer> cNode = null;
		for(int i=2; i<10; i++) {
			list.addNext(node, i);
			node = list.getNext(node);
			if(i==5) {
				cNode = node;
			}
			print(list);
		}
		NodeAccessibleLinkedList<Integer> list2 = list.detachList(cNode);
		print(list2);
		print(list);
		list2.attachList(list);
		print(list2);
		list2.removeNode(list2.getFirst());
		print(list2);
		list2.removeNode(list2.getLast());
		print(list2);
	}
	
	private static void print(NodeAccessibleLinkedList<Integer> list) {
		NodeAccessibleLinkedList.Node<Integer> node = list.getFirst();
		while(node!=null) {
			System.out.print(list.getElem(node)+" ");
			node = list.getNext(node);
		}
		System.out.println();
	}
}
