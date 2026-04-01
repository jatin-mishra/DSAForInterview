package org.example.bit_manipulation;

public class ithBit {
    private int getIthBit(int n, int i){
        return (n >> (i-1)) & 1;
    }

    private int clearIthBit(int n, int i){
        int mask = ~(1 << (i-1));
        return n & mask;
    }

    private int setIthBit(int n, int i){
        int mask = (1 << (i-1));
        return n | mask;
    }

    private int updateIthBit(int n, int i, int v){
        n = clearIthBit(n, i);
        if(v == 1){
            n = setIthBit(n, i);
        }
        return n;
    }

    private int clearLastIBits(int n, int i){
        int mask = (-1 << i);
        return n & mask;
    }

    private int clearBitsInRange(int n, int i, int j){
        /*
        1 1 1 1 0 0 0 0 1 1 1

        mask1:
        1 1 1 1 0 0 0 0 0 0 0

        mask2:
        1 1 1 1 1 1 1 1 0 0 0
        0 0 0 0 0 0 0 0 1 1 1

        ... 0 0 1 1 1 1 0 ...

        Another approach:
        mask1 = (-1 << j+1)
        mask2 = (1 << i) - 1
         */
        int mask1 = (-1 << j);
        int mask2 = ~(-1 << (i-1));
        int mask = mask1 | mask2;
        return n & mask;
    }

}
