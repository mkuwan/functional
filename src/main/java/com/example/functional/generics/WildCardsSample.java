package com.example.functional.generics;

import com.example.functional.extend.AnimalBaseClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ワイルドカード
 * <? extends T>
 * <? super T>
 */
public class WildCardsSample {

    /* ------------------ジェネリック型-------------------------- */
    /**
     * 基底クラス(スーパークラス)
     * @param <T>
     */
    public class GenericClass<T> {
        public void viewData(T value){
            System.out.println("Type:=" + value.getClass().getSimpleName() + ", value:=" + value.toString());
        }
    }

    @Test
    public void UseSomeClassMethod() {
        GenericClass<Double> doubleGenericClass = new GenericClass<>();
        doubleGenericClass.viewData(1.2345D);

        GenericClass<String> stringGenericClass = new GenericClass<>();
        stringGenericClass.viewData("123456");
    }
    /* -------------------------------------------- */


    /* --------------------上限付きワイルドカード------------------------ */
    /**
     * Upper Bounded Wild Cards
     * 上限付きワイルドカード
     * <? extends T>
     */

    private double result;

    // 上限付きワイルドカードを使用したメソッド
    public void sum(List<? extends Number> numbers){
        result = 0d;
        String typeName = "";
        String superClassName = "";
        for (Number n : numbers) {
            result += n.doubleValue();
            typeName = n.getClass().getSimpleName();
            superClassName = n.getClass().getSuperclass().getSimpleName();
        }
        System.out.println("合計は" + result);
        System.out.println("使用されている型は" + typeName + ", スーパークラスは" + superClassName);
    }

    @Test
    public void upperBoundedWildCardsTest(){
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        sum(integerList);

        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3, 4.4, 5.5);
        sum(doubleList);

        // ObjectはNumberよりも上の型なのでコンパイルエラーとなります
//        List<Object> objectList = Arrays.asList(1, 2, 3, 4, 5);
//        sum(objectList);
    }
    /* -------------------------------------------- */


    /* -----------------------下限付きワイルドカード--------------------- */
    /**
     * Lower Bounded Wild Cards
     * 下限付きワイルドカード
     * <? super T>
     */

    private class FishClass extends AnimalBaseClass {
        public FishClass(String name) {
            super(name);
        }
    }

    private class BirdClass extends AnimalBaseClass {
        public BirdClass(String name) {
            super(name);
        }
    }

    private class SeaFishClass extends FishClass{
        public SeaFishClass(String name) {
            super(name);
        }
    }

    // 下限付きワイルドカードを使用したメソッド
    public void addFish(List<? super FishClass> fishList, String name) {
        fishList.add(new FishClass(name));
        System.out.println(name + "が追加されたよ");
        System.out.println("使用されている型は" + fishList.get(0).getClass().getSimpleName());
    }

    @Test
    public void lowerBoundedWildCardsTest(){
        List<AnimalBaseClass> animals = new ArrayList<>();
        List<FishClass> fishes = new ArrayList<>();
        List<BirdClass> birds = new ArrayList<>();
        List<SeaFishClass> seaFishes = new ArrayList<>();

        addFish(animals, "動物");
        addFish(fishes, "メダカ");

        // これはAnimalBaseClassを継承していてもFishClassの継承ツリーにはないのでコンパイルエラーとなります
        // addFish(birds, "ハト");

        // FishClassよりもサブクラスなので、これはコンパイルエラーとなります
//        addFish(seaFishes, "いわし");
    }
    /* -------------------------------------------- */
}
