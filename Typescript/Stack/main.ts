class StackNode {
    public value: number;
    public next: StackNode | null;

    constructor(value: number, next: StackNode | null) {
        this.value = value;
        this.next = next;
    }
}

class Stack {
    private head: StackNode | null;

    constructor() {
        this.head = null;
    }

    public push(value: number): void {
        this.head = new StackNode(value, this.head);
    }
    
    public peek(): number | null {
        if (this.head === null) {
            return null;
        }
        return this.head.value;
    }
    
    public pop(): number | null {
        if (this.head === null) {
            return null;
        }

        const returnValue = this.head.value;
        this.head = this.head.next;
        return returnValue;
    }

    public print() {
        let currNode: StackNode | null = this.head;
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
const stack = new Stack();
stack.push(1);
stack.push(2);
stack.push(3);
stack.print();
console.log(`peaking ${stack.peek()}`);
stack.print();
console.log(`popping ${stack.pop()}`);
stack.print();
stack.push(55);
stack.print();
console.log(`popping ${stack.pop()}`);
console.log(`popping ${stack.pop()}`);
stack.print();
console.log(`popping ${stack.pop()}`);
console.log(`popping ${stack.pop()}`);
console.log(`peaking ${stack.peek()}`);