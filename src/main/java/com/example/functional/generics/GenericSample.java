package com.example.functional.generics;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 匿名クラスを利用することで、新しくクラスを作成せずに
 * 抽象クラスやインタフェースをそのままインスタンス化させるような動作を行うことができます。
 * これを利用したものが関数型インタフェース Consumer<T>, Supplier<T>, Predicate<T>, Function<T, R> です
 */
public class GenericSample {

    /**
     * メソッドをひとつだけ宣言しているのが特徴です
     */
    @FunctionalInterface   //　なくても問題ないが、これをつけておくと複数のメソッドを記入したときにコンパイルエラーとすることができる
    interface Sample {
        void method();
    }

    /**
     * ジェネリック型 (匿名クラス)
     */
    public class GenericExercise {
        public void exercise() {
            // ジェネリック型の実装
            //   本来はclass SampleImpl implements Sampleというインタフェースを継承したクラスが必要だがそれを短縮してる
            Sample sample = new Sample() {
                @Override
                public void method() {
                    System.out.println("Overrideしたメソッドだよ");
                }
            };

            sample.method();
        }
    }

    @Test
     void SampleTest(){
        GenericExercise exercise = new GenericExercise();
        exercise.exercise();
    }


    /**
     * ラムダにしたもの
     * このほうが直感的な感じになります
     */
    public class GenericLambdaExercise {
        public void exercise() {
            // ジェネリック型のラムダでの実装
            Sample sample = () -> System.out.println("ラムダでOverrideしたメソッドだよ");
            sample.method();
        }
    }

    @Test
    void SampleLambdaTest(){
        GenericLambdaExercise exercise = new GenericLambdaExercise();
        exercise.exercise();
    }


    /**
     * この関数型インタフェースについてJavaでは定義済みのものがあります
     *  Consumer<T>, Predicate<T>, Supplier<T>, Function<T, R>などです
     */


    /**
     * Consumer<T>
     *     T型の引数を受けてメソッドを実行する
     */
    @Test
    void consumerTest() {
        Consumer<String> consumer = x -> System.out.println(x + "です");

//        ラムダではない書き方だと以下のようになります(ラムダが使いやすいのがよく分かりますね)
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(x + "です");
//            }
//        };

        consumer.accept("コンシューマ");
    }

    /**
     * Predicate<T>
     *     T型の引数を受けてbooleanを返します
     */
    @Test
    void PredicateTest() {
        Predicate<String> predicate = x -> x.equals("あ");

//        ラムダではない書き方だと以下のようになります(ラムダが使いやすいのがよく分かりますね)
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return s.equals("あ");
//            }
//        };

        System.out.println(predicate.test("あ"));    // true
        System.out.println(predicate.test("い"));    // false
    }

}
