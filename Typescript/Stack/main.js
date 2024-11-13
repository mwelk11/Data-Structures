var StackNode = /** @class */ (function () {
    function StackNode(value, next) {
        this.value = value;
        this.next = next;
    }
    return StackNode;
}());
var Stack = /** @class */ (function () {
    function Stack() {
        this.head = null;
    }
    Stack.prototype.push = function (value) {
        this.head = new StackNode(value, this.head);
    };
    Stack.prototype.peek = function () {
        if (this.head === null) {
            return null;
        }
        return this.head.value;
    };
    Stack.prototype.pop = function () {
        if (this.head === null) {
            return null;
        }
        var returnValue = this.head.value;
        this.head = this.head.next;
        return returnValue;
    };
    Stack.prototype.print = function () {
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
    return Stack;
}());
// Tests
var stack = new Stack();
stack.push(1);
stack.push(2);
stack.push(3);
stack.print();
console.log("peaking ".concat(stack.peek()));
stack.print();
console.log("popping ".concat(stack.pop()));
stack.print();
stack.push(55);
stack.print();
console.log("popping ".concat(stack.pop()));
console.log("popping ".concat(stack.pop()));
stack.print();
console.log("popping ".concat(stack.pop()));
console.log("popping ".concat(stack.pop()));
console.log("peaking ".concat(stack.peek()));
