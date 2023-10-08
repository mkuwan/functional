package com.example.functional.strings;

import org.junit.jupiter.api.Test;

/**
 * intはプリミティブ型ですが
 * Stringはオブジェクト型です
 * ここをしっかりと分かっていれば大丈夫！！
 */
public class StringSamples {

    /**
     * プリミティブ型について
     */
    @Test
    public void primitiveTypeSample(){
        // valueAには10、valueBには20という値が保持されます
        int valueA = 10;
        int valueB = 20;

        System.out.println("valueA:=" + valueA + ", valueB:=" + valueB);    // valueA:=10, valueB:=20
        System.out.println(valueA==valueB);                                 // false

        // valueAにvalueBを代入すると
        valueA = valueB;
        System.out.println("valueA:=" + valueA + ", valueB:=" + valueB);    // valueA:=20, valueB:=20
        System.out.println(valueA==valueB);                                 // true

        // valueBを30にしたらどうなる？
        valueB = 30;
        System.out.println("valueA:=" + valueA + ", valueB:=" + valueB);    // valueA:=20, valueB:=30
        System.out.println(valueA==valueB);                                 // false

        // valueAを30にしたらどうなる？
        valueA = 30;
        System.out.println("valueA:=" + valueA + ", valueB:=" + valueB);    // valueA:=30, valueB:=30
        System.out.println(valueA==valueB);                                 // true
    }


    /**
     * プリミティブ型と同じことをString型で行ったどうなる？
     */
    @Test
    public void stringTypeSample(){
        // valueAには10、valueBには20という値が保持されます
        String valueA = "10";
        String valueB = "20";

        System.out.println("valueA:=" + valueA + ", valueB:=" + valueB);    // valueA:=10, valueB:=20
        System.out.println(valueA==valueB);                                 // false

        // valueAにvalueBを代入すると
        valueA = valueB;
        System.out.println("valueA:=" + valueA + ", valueB:=" + valueB);    // valueA:=20, valueB:=20
        System.out.println(valueA==valueB);                                 // true

        // valueBを30にしたらどうなる？
        valueB = "30";
        System.out.println("valueA:=" + valueA + ", valueB:=" + valueB);    // valueA:=20, valueB:=30
        System.out.println(valueA==valueB);                                 // false

        // valueAを30にしたらどうなる？
        valueA = "30";
        System.out.println("valueA:=" + valueA + ", valueB:=" + valueB);    // valueA:=30, valueB:=30
        System.out.println(valueA==valueB);                                 // true

        // valueBをnew String("30")とした場合は
        valueB = new String("30");
        System.out.println("valueA:=" + valueA + ", valueB:=" + valueB);    // valueA:=30, valueB:=30
        System.out.println(valueA==valueB);                                 // false  <------おなじ30なのにfalseになる
        // 参照値ではなく中身の値を比較する場合はequalsを使用します
        System.out.println("equals:" + valueA.equals(valueB));
    }


    /**
     * 参照渡し(ほとんどこの使い方は実際はしません)
     * 　こちらの参照値と呼び出しメソッドの参照値が同じ
     */
    @Test
    public void arrayByRefSample() {
        String[] array = {"おはようございます", "こんにちは", "こんばんは"};
        System.out.println("1. " + array[0]);       // おはようございます
        changeArrayByRef(array);
        System.out.println("3. " + array[0]);       // Hello

        // 参照値は以下の通り
        System.out.println("arrayの参照値:=" + array);
    }

    private void changeArrayByRef(String[] arr){
        arr[0] = "Hello";
        System.out.println("2. " + arr[0]);       // Hello

        // 参照値は以下の通り
        System.out.println("arrの参照値:=" + arr);
    }


    /**
     * 参照の値渡し(これがオブジェクト型の基本的な使い方)
     */
    @Test
    public void arrayByRefWithByValSample(){
        String[] array = {"おはようございます", "こんにちは", "こんばんは"};
        System.out.println("1. " + array[0]);       // おはようございます
        changeArrayByRefWithByVal(array);
        System.out.println("3. " + array[0]);       // おはようございます
        // 参照値は以下の通り
        System.out.println("arrayの参照値:=" + array);
    }

    private void changeArrayByRefWithByVal(String[] arr){
        arr = new String[3];  // ここでarrayを新しく初期化したインスタンスに入れ替えた！
        arr[0] = "Good Morning";
        System.out.println("2. " + arr[0]);       // Good Morning

        // 参照値は以下の通り
        System.out.println("arrの参照値:=" + arr);
    }

    /**
     * Stringも参照の値渡しです
     * 参照値を渡しているのではなく、参照値が持っている値だけを渡している
     */
    @Test
    public void stringByRefWithByValSample(){
        String message = "おはよう";
        System.out.println("1. " + message);        // おはよう
        changeStringByRefWithByVal(message);        // 参照渡ししているようだが実際は String.valueOf(message) を渡している感じ
        System.out.println("3. " + message);        // おはよう
    }

    private void changeStringByRefWithByVal(String value){
        value = "Good Morning";                   // ここでStringは作り直しされたことになる
        System.out.println("2. " + value);        // Good Morning
    }

}
