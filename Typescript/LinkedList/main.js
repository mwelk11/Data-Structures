var MyNode = /** @class */ (function () {
    function MyNode(value, next) {
        this.value = value;
        this.next = next;
    }
    return MyNode;
}());
var LinkedList = /** @class */ (function () {
    function LinkedList() {
        this._head = null;
        this._tail = null;
        this._size = 0;
    }
    LinkedList.prototype.insertAtBeginning = function (elem) {
        var node = new MyNode(elem, this._head);
        if (this.size === 0) {
            this._tail = node;
        }
        this._head = node;
        this._size++;
    };
    LinkedList.prototype.insertAtEnd = function (elem) {
        var node = new MyNode(elem, null);
        if (this.size === 0) {
            this._head = node;
        }
        else {
            this._tail.next = node;
        }
        this._tail = node;
        this._size++;
    };
    LinkedList.prototype.delete = function (elem) {
        var currNode = this._head;
        var prevNode = null;
        while (currNode !== null) {
            if (currNode.value === elem) {
                if (currNode === this._head) {
                    this._head = currNode.next;
                }
                else if (currNode === this._tail) {
                    this._tail = prevNode;
                    prevNode.next = currNode.next;
                }
                else {
                    prevNode.next = currNode.next;
                }
                currNode = null;
                this._size--;
            }
            else {
                prevNode = currNode;
                currNode = currNode.next;
            }
        }
    };
    LinkedList.prototype.get = function (elem) {
        var currNode = this._head;
        while (currNode !== null) {
            if (currNode.value === elem) {
                return currNode;
            }
            else {
                currNode = currNode.next;
            }
        }
        return null;
    };
    Object.defineProperty(LinkedList.prototype, "size", {
        get: function () {
            return this._size;
        },
        enumerable: false,
        configurable: true
    });
    LinkedList.prototype.print = function () {
        var currNode = this._head;
        var log = "";
        while (currNode !== null) {
            log += currNode.value;
            if (currNode.next !== null) {
                log += '->';
            }
            currNode = currNode.next;
        }
        console.log(log);
        console.log(this._head);
        console.log(this._tail);
    };
    return LinkedList;
}());
// Tests
var linkedList = new LinkedList();
linkedList.insertAtEnd(1);
linkedList.insertAtEnd(2);
linkedList.insertAtBeginning(3);
linkedList.print();
linkedList.delete(3);
linkedList.print();
