package com.example.functional.extend;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class ExtendSample {

    /**
     * 直感的に分かる例
     */
    @Test
    public void StringSample(){
        // 変数の設定
        String japanese = "日本語";
        String english = "英語";
        // 出力
        System.out.println(japanese);   // 結果は日本語
        System.out.println(english);    // 結果は英語
        //
        japanese = english;
        System.out.println(japanese);   // 結果は？　英語ですね
    }

    /**
     * AnimalBaseClassについて
     * cat, dog それぞれに初期値の名前を設定している
     * catとdogは同じAnimalBaseClassなのでお互いに代入可能
     */
    @Test
    public void animalBaseClassSample(){
        // FoodBaseClass型の変数の設定
        AnimalBaseClass cat = new AnimalBaseClass("猫");
        AnimalBaseClass dog = new AnimalBaseClass("犬");

        System.out.println("cat is " + cat.getName());
        System.out.println("dog is " + dog.getName());

        cat = dog;
        System.out.println("catは" + cat.getName());
    }

    /**
     * 動物基底クラスから魚クラスに拡張します
     */
    private class FishClass extends AnimalBaseClass {

        /**
         * 基底クラスのデフォルトコンストラクタをsuperで呼び出しています
         * @param name
         */
        public FishClass(String name) {
            // superより上には何もコードは書けない
            super(name);
        }

        /**
         * 泳ぐというメソッドを独自実装する
         */
        public void swim(){
            System.out.println("泳ぎます");
        }
    }

    /**
     * 動物基底クラスから鳥クラスに拡張します
     */
    private class BirdClass extends AnimalBaseClass {

        public BirdClass(String name) {
            super(name);
        }

        /**
         * 飛ぶというメソッドを独自実装する
         */
        public void fly(){
            System.out.println("飛びます");
        }
    }


    @Test
    public void extendsSample(){

        FishClass fishClass = new FishClass("サバ");
        fishClass.swim();

        BirdClass birdClass = new BirdClass("つばめ");
        birdClass.fly();

        // 基底クラス(スーパークラス)の変数に拡張クラス(サブクラス)を代入することはできる
        AnimalBaseClass fish = new FishClass("サカナ");
        System.out.println(fish.getName());
        // fish.Swim();  // 基底クラスにはこのメソッドはないのでできない

        AnimalBaseClass bird = new BirdClass("トリ");
        System.out.println(bird.getName());

        // コンパイルエラー
        // 理由:superクラスにはサブクラスの実装(swim)がないので、実装を渡すことができない
//        FishClass errFish = new AnimalBaseClass("さかな");
        //　つまりAnimalBaseClass("さかな")では以下のコードを実行することができないということ
//        errFish.Swim();
    }
}
