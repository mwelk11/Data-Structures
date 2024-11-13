class LinkedListNode {

    public next: LinkedListNode | null;
    public value: number

    constructor(value: number, next: LinkedListNode | null) {
        this.value = value;
        this.next = next;
    }
}

class LinkedList {

    private _head: LinkedListNode | null = null;
    private _tail: LinkedListNode | null = null;
    private _size: number;

    constructor() {
        this._size = 0;
    }

    public insertAtBeginning(value: number): void {
        const node = new LinkedListNode(value, this._head);
        if (this._size === 0) {
            this._tail = node;
        }
        this._head = node;
        this._size++;
    }

    public insertAtEnd(value: number): void {
        const node = new LinkedListNode(value, null);
        if (this._size === 0) {
            this._head = node;
        } else {
            this._tail!.next = node;
        }
        this._tail = node;
        this._size++;
    }

    public delete(value: number): void {
        let currNode: LinkedListNode | null = this._head;
        let prevNode: LinkedListNode | null = null;
        while (currNode !== null) {
            if (currNode.value === value) {
                if (currNode === this._head) {
                    this._head = currNode.next;
                } else if (currNode === this._tail) {
                    this._tail = prevNode;
                    prevNode!.next = currNode.next;
                } else {
                    prevNode!.next = currNode.next;
                }
                currNode = null;
                this._size--;
            } else {
                prevNode = currNode;
                currNode = currNode.next;
            }
        }
    }

    public get(value: number): LinkedListNode | null {
        let currNode = this._head;
        while (currNode !== null) {
            if (currNode.value === value) {
                return currNode;
            } else {
                currNode = currNode.next;
            }
        }
        return null;
    }

    public get size(): number {
        return this._size;
    }

    public print() {
        let currNode = this._head;
        let log = "";
        while (currNode !== null) {
            log += currNode.value;
            if (currNode.next !== null) {
                log += '->';
            }
            currNode = currNode.next
        }
        console.log(log);
        console.log(this._head);
        console.log(this._tail);
    }
}

// Tests
const linkedList = new LinkedList();
linkedList.insertAtEnd(1);
linkedList.insertAtEnd(2);
linkedList.insertAtBeginning(3);
linkedList.print();
linkedList.delete(3);
linkedList.print();