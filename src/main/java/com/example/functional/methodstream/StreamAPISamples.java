package com.example.functional.methodstream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * for-i
 * for-each
 * ラムダ構文
 * メソッド参照
 * SteamAPI　を同じListに対して処理を行った例 (文字の長さが2より大きいものを出力する)
 */
public class StreamAPISamples {

    private final List<String> foods =
            Arrays.asList("スパゲッティ","カレーライス", "シチュー", "水",
                    "五目飯", "パン", "そば", "ラーメン",
                    "コーラ", "牛乳", "バナナ", "イチゴ");

    /**
     * for i 構文で中身の抽出
     */
    @Test
    public void for_i() {
        for(int i = 0; i < foods.size(); i++){
            if(foods.get(i).length() > 2){
                System.out.println(foods.get(i));
            }
        }
    }

    /**
     * for each 構文で中身の抽出
     */
    @Test
    public void for_each() {
        for (String food : foods) {
            if(food.length() > 2){
                System.out.println(food);
            }
        }
    }

    /**
     * forEachメソッドでの中身の抽出
     *      Consumer<? super String> actionをOverrideして作成
     */
    @Test
    public void for_each_method() {
        foods.forEach(new Consumer<String>() {
            @Override
            public void accept(String food) {
                if(food.length() > 2){
                    System.out.println(food);
                }
            }
        });
    }

    /**
     * 内部で匿名クラスとして宣言していたものをメソッドとして取り出した場合
     */
    @Test
    public void for_each_method2() {
        // Consumerの内容を取り出した
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                if(s.length() > 2)           // ついでにif文の後の実行が1行だけの場合は { } を省略できるのは覚えておこう
                    System.out.println(s);
            }
        };

        foods.forEach(consumer);
    }

    /**
     * 内部で匿名クラスとして宣言していたものをメソッドとして取り出しラムダで書いた場合
     */
    @Test
    public void for_each_method_by_lambda() {
        // Consumerの内容を定義(ラムダを使用)
        Consumer<String> consumer =  x -> {
            if(x.length() > 2)
                System.out.println(x);
        };

        foods.forEach(consumer);
    }

    /**
     * forEachメソッドでの中身をラムダ構文で作成
     *      Consumer<? super String>
     *
     * ラムダ構文：(T value) -> {}
     */
    @Test
    public void for_each_method_with_lambda() {
        // 型指定あり
        foods.forEach((String food) -> {
            if(food.length() > 2){
                System.out.println(food);
            }
        });

        // 型推論でString型の指定を省略できる
        foods.forEach(x -> {
            if(x.length() > 2){
                System.out.println(x);
            }
        });


        // 条件指定がなくSystem.out.printlnのみの場合
        foods.forEach(food -> { System.out.println(food); });

        // 条件指定がなくSystem.out.printlnのみの場合は { } を省略できる
        // { } を省略した場合は ; は記述しない
        foods.forEach(food -> System.out.println(food));

    }

    /**
     * メソッド参照
     *  クラス名::メソッド名
     *  インスタンス変数名::メソッド名　という書き方をすることができる
     */
    @Test
    public void for_each_method_with_method_reference() {
        // foods.forEach(food -> System.out.println(food));
        //                 ↓ このカッコ内の部分をメソッド参照で書き直す
        foods.forEach(System.out::println);

    }

    /**
     * ラムダ構文を使うかメソッド参照を使うかは基本的にその人の好みで決まることが多い(笑)
     */



    /**
     * StreamAPIを使用した場合
     *     peekは状態を監視するのに使われるもので、内部的には何もしません(実際はConsumer<T>です)
     */
    @Test
    public void for_each_method_with_stream() {
        foods.stream()
                .filter(food -> food.length() > 2)
                .peek(x -> System.out.println(x));
    }

    /**
     * StreamAPIの処理順がよく分かる例
     * peekはコメントアウトしてデバッグで止めてストリームチェーンをトレースすると処理がイメージしやすいかも
     */
    @Test
    public void streamApiProcess() {
        // 並び替えをした場合
        // 処理がどのような順番で行われるかが見えて面白い
        var result = foods.stream()
                // 処理するデータをひとつずつストリーム(パイプライン)に送り出す
                .peek(x -> System.out.println("peekA: " + x))

                // 文字の長さが２より大きいものを抽出
                .filter(food -> food.length() > 2)
                .peek(x -> System.out.println("peekB: " + x))

                // 文字の中にイ, コ, シ が入っているものを抽出
                .filter(c -> c.contains("イ") || c.contains("コ") || c.contains("シ"))
                .peek(x -> System.out.println("peekC: " + x))

                // 文字順にソート　全データがないとソートできないのでここでデータがまとめられてから処理が行われる
                .sorted(Comparator.naturalOrder())
                .peek(x -> System.out.println("peekS: " + x))
                .toList();

        // 結果
        System.out.println("結果です");
        result.forEach(System.out::println);
    }
}
