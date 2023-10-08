package com.example.functional;

import com.example.functional.mockdata.MockData;
import com.example.functional.model.Person;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 命令型プログラミングと宣言的プログラミングについて
 */
public class GettingStarted {
    /**
     * コーヒーを飲むための手順を例にしてみましょう
     * 命令型プログラミング
     *  1. カップを用意する
     *  2. お湯を沸かす
     *  3. コーヒー豆を挽く
     *  4. フィルターを用意する
     *  5. 挽いたコーヒーをフィルターの中に入れる
     *  6. お湯を注ぐ
     *  7. 抽出したコーヒーをカップに注ぐ
     *
     * 宣言的プログラミング
     *  1. 「コーヒー頂戴」と伝える。以上。
     */


    /**
     * Imperative Approach
     * 命令型プログラミング
     * ひとつひとつの処理をすべて書いて実行させるコーディング
     * 手続きをすべて書いてるのでコードの行数が当然多くなります
     * @throws IOException
     */
    @Test
    public void imperativeApproach() throws IOException {
        // 18歳以下の人を見つける
        List<Person> people = MockData.getPeople();
        List<Person> youngPeople = new ArrayList<>();
        int limit = 10;
        int counter = 0;
        for (Person person : people) {
            if(person.getAge() <= 18) {
                youngPeople.add(person);
                counter++;
                if(counter == limit){
                    break;
                }
            }
        }

        // foreach文での出力
        for (Person person : youngPeople) {
            System.out.println(person);
        }
    }

    /**
     * Declarative Approach
     * 宣言的プログラミング
     * 何をするかを一文で書いて、処理の内容はお任せするコーディング
     * 　streamを使用してます
     *   if文がないことに注目
     *   中間処理等を呼んで匿名型メソッドなどをラムダ構文で使用しています
     * @throws IOException
     */
    @Test
    public void declarativeApproach() throws IOException {
        List<Person> people = MockData.getPeople();
        List<Person> youngPeople = people.stream()
                .filter(p -> p.getAge() <= 18)  // 実際はこのように定義されています filter(Predicate<? super T> predicate)
                .limit(10)
                .collect(Collectors.toList());  // <- Java17はここを .toList() だけに省略可能です

        // ラムダで出力する場合
        System.out.println("ラムダでの出力");
        youngPeople.forEach(x -> System.out.println(x));

        // メソッド参照で出力する場合
        System.out.println("メソッド参照での出力");
        youngPeople.forEach(System.out::println);
    }


    /**
     * Streamとは？
     * 　・命令型プログラミングではなく関数型プログラミングを使用する
     * 　　　関数型プログラミングとは実装の詳細を定義するのではなくメソッドを指定する書き方
     * 　・コレクションで使用される(Array, List, Map, Set)
     *
     *  1. リストなどからスタートし、streamメソッドを呼びだしてstreamを生成します
     *      list.stream()
     *  2. 中間処理(Intermediate Operators)のメソッドを呼びます
     *      ex) filter, map, limit, skip, sorted, ちょっと特殊:peek
     *  3. 終端処理(Terminal Operator)のメソッドを呼びます
     *      ex) foreach, anyMatch, findFirst, max, min, count, average, sum, reduce, collect, toList
     */

    // filter(Predicate<? super T> predicate)
    // map  Function<? super T, ? extends R> mapper

}
