class Pair {
    public key: string;
    public value: string;

    constructor(key: string, value: string) {
        this.key = key;
        this.value = value;
    }
}

class HashMap {

    private readonly EXPECTED_SIZE = 50;
    private readonly INITIAL_TABLE_SIZE = this.EXPECTED_SIZE * 2;
    private storage: Array<Pair | undefined>;
    private size: number;

    constructor() {
        this.storage = new Array(this.INITIAL_TABLE_SIZE);
        this.size = 0;
    }

    public insert(key: string, value: string): void {
        const hash: number = this.calculateHash(key);
        this.storage[hash] = new Pair(key, value);
        this.size++;
    }
    
    public get(key: string): string | undefined {
        const hash: number = this.calculateHash(key);
        return this.storage[hash]?.value;
    }

    public remove(key: string): void {
        const hash: number = this.calculateHash(key);
        this.storage[hash] = undefined;
        this.size--;
    }

    private calculateHash(key: string): number {
        let hash = 0;
        for (let i = 0; i < key.length; i++) {
            const charCode = key.charCodeAt(i);
            // helps in mixing the bits and distributing the influence of each character
            hash = (hash << 5) - hash + charCode;
            // bitwise OR with 0 to convert the result to a 32-bit integer
            hash |= 0;
            // keep the hash within our set array size
            hash = hash % this.storage.length;
        }

        // Hash collision, keep incrementing hash until we find an empty slot
        while(this.storage[hash] && this.storage[hash]?.key !== key) {
            hash++;
            if (hash >= this.storage.length-1) {
                this.resizeArray();
            }
        }

        return hash;
    }

    private resizeArray() {
        this.storage.length = this.storage.length * 2;
    }

    public print() {
        for (const pair of this.storage) {
            if (pair?.key) {
                console.log(`${pair?.key} -> ${pair?.value}`);
            }
        }
    }
}

// Test
const hashMap = new HashMap();
hashMap.insert('key1', 'value1');
hashMap.insert('key2', 'value2');
hashMap.insert('key3', 'value3');
hashMap.print();
console.log(`getting key2 -> ${hashMap.get('key2')}`)
hashMap.print();
console.log('removing key2')
hashMap.remove('key2');
console.log(`getting key2 -> ${hashMap.get('key2')}`)
hashMap.print();
hashMap.insert('key1','updateValue1')
hashMap.print();