class QueueNode {
    public value: number;
    public next: QueueNode | null;
    public prev: QueueNode | null;

    constructor(value: number, next: QueueNode | null = null, prev: QueueNode | null = null) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
}

class Queue {
    private head: QueueNode | null;
    private tail: QueueNode | null;

    constructor() {
        this.head = null;
        this.tail = null;
    }

    public enqueue(value: number) {
        const node = new QueueNode(value, this.head);
        if (this.head === null) {
            this.tail = node;
        } else {
            this.head.prev = node;
            if (this.head.next === null) {
                this.tail = this.head;
            }
        }
        this.head = node;
    }

    public dequeue(): number | null {
        if (this.tail === null) {
            return null;
        }

        const returnValue = this.tail.value;
        // one element queue, so queue is now empty
        if (this.tail.prev === null) {
            this.tail = null;
            this.head = null;
        } else {
            this.tail = this.tail.prev;
            this.tail.next = null;
        }
        return returnValue
    }

    public peek(): number | null {
        if (this.tail === null) {
            return null;
        }

        return this.tail.value;
    }

    public print() {
        let currNode: QueueNode | null = this.head;
        let log = "";

        while (currNode !== null) {
            log += currNode.value;
            if (currNode.next !== null) {
                log += ",";
            }
            currNode = currNode.next;
        }
        console.log(log);
    }
}

// Tests
const queue: Queue = new Queue();
queue.enqueue(1);
queue.enqueue(2);
queue.enqueue(3);
queue.print();
console.log(`dequeueing ${queue.dequeue()}`)
queue.print();
queue.enqueue(4);
console.log(`peeking ${queue.peek()}`)
console.log(`dequeueing ${queue.dequeue()}`)
queue.print();