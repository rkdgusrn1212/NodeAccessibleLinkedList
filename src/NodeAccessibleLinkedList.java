/**  
 * @author khgkjg12
 * LinkedList와의 차이점
 * 1. 입력 인덱스의 노드 레퍼런스를 반환.
 * 2. 입력 노드의 이전노드를 반환.
 * 3. 입력 노드의 다음노드를 반환.
 * 4. 기타 필요없는 기능 뺌.
 */
public class NodeAccessibleLinkedList<E>{
	
	private Node<E> first = null;
	private Node<E> last = null;
	
	@SuppressWarnings("hiding")
	public static class Node<E>{//static으로 외부참조없에고 외부클래스가 먼제 unreachable될 경우의 메모리 누수 방지.
		private E elem;
		private Node<E> prev = null;
		private Node<E> next = null;
	}
	
	public void addFirst(E elem) {
		if(first==null) {
			first = new Node<E>();
			first.elem = elem;	
			last = first;
		}else {
			Node<E> temp = first;
			first = new Node<E>();
			first.elem = elem;
			first.next = temp;
			temp.prev = first;
		}
	}
	
	public void addLast(E elem){
		if(last==null) {
			last = new Node<E>();
			last.elem = elem;
			first = last;
		}else {
			Node<E> temp = last;
			last = new Node<E>();
			last.elem = elem;
			last.prev = temp;
			temp.next = last;
		}
	}
	
	public void addNext(Node<E> prev, E elem) {
		Node<E> node = new Node<E>();
		node.elem = elem;
		Node<E> nNode = prev.next;
		if(nNode!=null) {
			nNode.prev = node;
			node.next = nNode;
		}else {
			last = node;
		}
		prev.next = node;
		node.prev = prev;
	}
	
	public void addPrev(Node<E> next, E elem) {
		Node<E> node = new Node<E>();
		node.elem = elem;
		Node<E> pNode = next.prev;
		if(pNode !=null) {
			pNode.next = node;
			node.prev = pNode;	
		}else {
			first = node;
		}
		next.prev = node;
		node.next = next;
	}
	
	
	//Node를 리스트에서 제거
	public void removeNode(Node<E> node) {
		Node<E> pNode = node.prev;
		Node<E> nNode = node.next;
		if(pNode!=null) {
			pNode.next = nNode;
		}else {
			first = nNode;
		}
		if(nNode!=null) {
			nNode.prev = pNode;
		}else {
			last = pNode;
		}
		node.next = null;
		node.prev = null;
	}
	
	/**
	 * 
	 * @param node
	 * @return 없으면 null
	 */
	public Node<E> getNext(Node<E> node){
		return node.next;
	}
	public Node<E> getPrev(Node<E> node){
		return node.prev;
	}
	public E getElem(Node<E> node) {
		return node.elem;
	}
	
	public Node<E> getFirst() {
		return first;
	}
	public Node<E> getLast() {
		return last;
	}
	
	public NodeAccessibleLinkedList<E> detachList(Node<E> strNode){

		NodeAccessibleLinkedList<E> nList = new NodeAccessibleLinkedList<E>();
		nList.first = strNode;
		nList.last = last;
		
		Node<E> pNode = strNode.prev;
		if(pNode!=null) {
			pNode.next = null;
			strNode.prev = null;
			last = pNode;
		}else {//텅비어버림.
			first = null;
			last = null;
		}
		return nList;
	}
	
	public void attachList(NodeAccessibleLinkedList<E> list) {
		if(first==null){
			first = list.first;
		}else {
			last.next = list.first;
			list.first.prev = last;
		}
		last = list.last;
		list.first = null;
		list.last = null;
	}
}