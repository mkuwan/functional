package com.example.functional.generics;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 匿名クラスを利用することで、新しくクラスを作成せずに
 * 抽象クラスやインタフェースをそのままインスタンス化させるような動作を行うことができます。
 * これを利用したものが関数型インタフェース Consumer<T>, Supplier<T>, Predicate<T>, Function<T, R> です
 */
public class GenericSampleInterface {

    /**
     * メソッドをひとつだけ宣言しているのが特徴です
     */
    @FunctionalInterface   //　なくても問題ないが、これをつけておくと複数のメソッドを記入したときにコンパイルエラーとすることができる
    interface SampleInterface {
        void method();
    }


    /**
     * 一般的な継承を使った使い方
     */
    private class ImplementedSample implements SampleInterface {
        /**
         * インターフェイスで宣言されているメソッドの実装
         */
        @Override
        public void method() {
            System.out.println("implementsしてmethod()を呼び出しました");
        }
    }

    /**
     * 継承したextendedSampleのテスト
     */
    @Test
    public void extendedSampleTest() {
        var implementedSample= new ImplementedSample();
        implementedSample.method();
    }

    //　******************************************************************* //

    /**
     * ジェネリック型 (匿名クラス)
     */
    public class GenericExercise {
        public void exercise() {
            // ジェネリック型の実装
            //   本来はclass SampleImpl implements Sampleというインタフェースを継承したクラスを作る必要があるが
            //   それを省略してインターフェイスをnewしている
            //   インターフェイスのメソッドが一つだけという関数型インターフェイスのみ可能な書き方
            SampleInterface sample = new SampleInterface() {
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


    //　******************************************************************* //

    /**
     * ラムダにしたもの
     * このほうが直感的な感じになります
     */
    public class GenericLambdaExercise {
        public void exercise() {
            // ジェネリック型のラムダでの実装
            // Overrideしたmethod()が消えているが、実は呼んでいる
            SampleInterface sample = () -> System.out.println("ラムダでOverrideしたメソッドだよ");
            sample.method();
        }
    }

    @Test
    void SampleLambdaTest(){
        GenericLambdaExercise exercise = new GenericLambdaExercise();
        exercise.exercise();
    }

    //　******************************************************************* //

    @FunctionalInterface
    public interface PredicateInterface<T>{
        boolean test(T value);
    }


    //　******************************************************************* //

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

//        ラムダではない書き方だと以下のようになります
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
