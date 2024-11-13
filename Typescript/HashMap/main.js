var Pair = /** @class */ (function () {
    function Pair(key, value) {
        this.key = key;
        this.value = value;
    }
    return Pair;
}());
var HashMap = /** @class */ (function () {
    function HashMap() {
        this.EXPECTED_SIZE = 50;
        this.INITIAL_TABLE_SIZE = this.EXPECTED_SIZE * 2;
        this.storage = new Array(this.INITIAL_TABLE_SIZE);
        this.size = 0;
    }
    HashMap.prototype.insert = function (key, value) {
        var hash = this.calculateHash(key);
        this.storage[hash] = new Pair(key, value);
        this.size++;
    };
    HashMap.prototype.get = function (key) {
        var _a;
        var hash = this.calculateHash(key);
        return (_a = this.storage[hash]) === null || _a === void 0 ? void 0 : _a.value;
    };
    HashMap.prototype.remove = function (key) {
        var hash = this.calculateHash(key);
        this.storage[hash] = undefined;
        this.size--;
    };
    HashMap.prototype.calculateHash = function (key) {
        var _a;
        var hash = 0;
        for (var i = 0; i < key.length; i++) {
            var charCode = key.charCodeAt(i);
            // helps in mixing the bits and distributing the influence of each character
            hash = (hash << 5) - hash + charCode;
            // bitwise OR with 0 to convert the result to a 32-bit integer
            hash |= 0;
            // keep the hash within our set array size
            hash = hash % this.storage.length;
        }
        // Hash collision, keep incrementing hash until we find an empty slot
        while (this.storage[hash] && ((_a = this.storage[hash]) === null || _a === void 0 ? void 0 : _a.key) !== key) {
            hash++;
            if (hash >= this.storage.length - 1) {
                this.resizeArray();
            }
        }
        return hash;
    };
    HashMap.prototype.resizeArray = function () {
        this.storage.length = this.storage.length * 2;
    };
    HashMap.prototype.print = function () {
        for (var _i = 0, _a = this.storage; _i < _a.length; _i++) {
            var pair = _a[_i];
            if (pair === null || pair === void 0 ? void 0 : pair.key) {
                console.log("".concat(pair === null || pair === void 0 ? void 0 : pair.key, " -> ").concat(pair === null || pair === void 0 ? void 0 : pair.value));
            }
        }
    };
    return HashMap;
}());
// Test
var hashMap = new HashMap();
hashMap.insert('key1', 'value1');
hashMap.insert('key2', 'value2');
hashMap.insert('key3', 'value3');
hashMap.print();
console.log("getting key2 -> ".concat(hashMap.get('key2')));
hashMap.print();
console.log('removing key2');
hashMap.remove('key2');
console.log("getting key2 -> ".concat(hashMap.get('key2')));
hashMap.print();
hashMap.insert('key1', 'updateValue1');
hashMap.print();
