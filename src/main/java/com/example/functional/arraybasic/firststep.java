package com.example.functional.arraybasic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

/**
 * Array
 * 配列に関する基礎講座
 */
public class firststep {
    @Test
    public void first_step(){
        // 配列を使う変数の宣言
        int[] intArray;

        // 変数の初期化、
        // ただし配列の中身を初期化していない
        // でもプリミティブ型なので中身の初期化は不要でデフォルト値が入る
        intArray = new int[3];

        /** 配列の各値の確認 **/
        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }

        /** 配列自体の値はObject.toString()  メモリ上の値 */
        System.out.println(intArray);
    }

    @Test
    public void second_step() {
        // 配列を使う変数の宣言
        Integer[] integerArray;

        // 変数の初期化、
        // ただし配列の中身を初期化していない: null
        integerArray = new Integer[3];

        /** 確認 **/
        for (int i = 0; i < integerArray.length; i++) {
            System.out.println(integerArray[i]);
        }
    }

    @Test
    public void third_step() {
        PanCakeRecipeClass[] recipeArray;

        recipeArray = new PanCakeRecipeClass[3];

        // これはまだできない
        // NullPointerExceptionが発生する　assertThrows(NullPointerException.class, () -> recipeArray[0].LetsCooking());
//        recipeArray[0].LetsCooking();

        // 理由は、recipeArray[0]が初期化されていないから: recipeArray[0]はnull
        recipeArray[0] = new PanCakeRecipeClass(100, 100, 100);
        recipeArray[0].LetsCooking();
    }


    /**
     * クラスとは設計図
     * パンケーキレシピ
     */
    private class PanCakeRecipeClass{

        /**
         * ホットケーキミックス
         */
        private int hotCakeMix;

        /**
         * たまご
         */
        private int egg;

        /**
         * 牛乳
         */
        private int milk;

        public PanCakeRecipeClass(int hotCakeMix, int egg, int milk){
            this.hotCakeMix = hotCakeMix;
            this.egg = egg;
            this.milk = milk;
        }

        public int LetsCooking(){
            // ホットケーキ1枚を作るために必要な材料
            // ホットケーキミックス: 50g
            // たまご:1個
            // 牛乳:40cc

            // 現在の材料で作ることのできるホットケーキの枚数を返します。
            int maxByCakeMix = hotCakeMix / 50;
            int maxByEgg = egg;
            int maxByMilk = milk/40;

            int[] maxArray = new int[3];
            maxArray[0] = maxByCakeMix;
            maxArray[1] = maxByEgg;
            maxArray[2] = maxByMilk;

            var maxHotCakeCount = Arrays.stream(maxArray).min().getAsInt();

            System.out.println("ホットケーキは最大で" + maxHotCakeCount + "枚できますよ");
            return maxHotCakeCount;
        }

    }

}
