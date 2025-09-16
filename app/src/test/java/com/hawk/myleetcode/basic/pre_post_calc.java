package com.hawk.myleetcode.basic;

import org.junit.Test;

public class pre_post_calc {

    @Test
    public void main(){
        post_calc();
        pre_calc();
    }


    static public void post_calc(){
        System.out.println("=============== post =========");

        int a=1;
        System.out.println("0. a="+a);

        if(a-- > 0)
            System.out.println("1. a="+a); // 後置運算 DANGEROUS: 因後置運算, 故先 >比較算符, 通過if後, 再做--算符, 所以印出

        if(a++ > 0)
            System.out.println("2. a="+a);

        a--;
        if(a > 0)
            System.out.println("3. a="+a);

        a++;
        if(a > 0)
            System.out.println("4. a="+a);

        System.out.println("5. a="+a);
    }

    static public void pre_calc(){
        System.out.println("=============== pre =========");

        int a=1;
        System.out.println("0. a="+a);

        if(--a > 0)
            System.out.println("1. a="+a);

        if(++a > 0)
            System.out.println("2. a="+a);

        --a;
        if(a > 0)
            System.out.println("3. a="+a);

        ++a;
        if(a > 0)
            System.out.println("4. a="+a);

        int shift=1;
        if( 'a'== "abc".charAt(shift++)) // 後置運算: 因為++與函式結合, 即便後置, 運算優先權仍高於函式charAt, 並於完成後才計算函式外運算子 ==
            System.out.println("5  true. shift="+shift);
        else
            System.out.println("5 false. shift="+shift);



        System.out.println("5. a="+a);
    }
}
