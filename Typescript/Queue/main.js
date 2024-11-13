var QueueNode = /** @class */ (function () {
    function QueueNode(value, next, prev) {
        if (next === void 0) { next = null; }
        if (prev === void 0) { prev = null; }
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
    return QueueNode;
}());
var Queue = /** @class */ (function () {
    function Queue() {
        this.head = null;
        this.tail = null;
    }
    Queue.prototype.enqueue = function (value) {
        var node = new QueueNode(value, this.head);
        if (this.head === null) {
            this.tail = node;
        }
        else {
            this.head.prev = node;
            if (this.head.next === null) {
                this.tail = this.head;
            }
        }
        this.head = node;
    };
    Queue.prototype.dequeue = function () {
        if (this.tail === null) {
            return null;
        }
        var returnValue = this.tail.value;
        // one element queue, so queue is now empty
        if (this.tail.prev === null) {
            this.tail = null;
            this.head = null;
        }
        else {
            this.tail = this.tail.prev;
            this.tail.next = null;
        }
        return returnValue;
    };
    Queue.prototype.peek = function () {
        if (this.tail === null) {
            return null;
        }
        return this.tail.value;
    };
    Queue.prototype.print = function () {
        var currNode = this.head;
        var log = "";
        while (currNode !== null) {
            log += currNode.value;
            if (currNode.next !== null) {
                log += ",";
            }
            currNode = currNode.next;
        }
        console.log(log);
    };
    return Queue;
}());
// Tests
var queue = new Queue();
queue.enqueue(1);
queue.enqueue(2);
queue.enqueue(3);
queue.print();
console.log("dequeueing ".concat(queue.dequeue()));
queue.print();
queue.enqueue(4);
console.log("peeking ".concat(queue.peek()));
console.log("dequeueing ".concat(queue.dequeue()));
queue.print();
