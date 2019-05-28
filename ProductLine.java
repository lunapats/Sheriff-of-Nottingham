package main;

import java.sql.SQLOutput;
import java.util.*;

public class ProductLine<ProductVector> {
    /*String[] products = {"apple", "cheese", "bread", "chicken", "silk", "pepper", "barrel"};
    Map<String, Integer> countProducts = new HashMap<String, Integer>();

    for (String product : products) {
        countProducts.put(product, countProducts.getOrDefault(product, 0) + 1);
    }

    for (String key : countProducts.keySet()) {
        System.out.println(key + ": " + countProducts.get(key));
    }*/
    /* private int apple = 0;
    private int bread = 0;
    private int cheese = 0;
    private int chicken = 0;
    private int barrel = 0;
    private int pepper = 0;
    private int silk = 0;*/
    private int legalSum = 0;
    private int value;

    private List<Integer> ProductVector = new ArrayList<>(Collections.nCopies(7, 0));
    public ProductLine() {
    }

    private ArrayList<Integer> CardsInHand = new ArrayList<>(7);

    public ArrayList<Integer> getCardsInHand() {
        return CardsInHand;
    }

    public void setCardsInHand(ArrayList<Integer> cardsInHand) {
        CardsInHand = cardsInHand;
    }

    //private List<Integer> ProductVector = Arrays.asList(apple, cheese, chicken, bread, silk, pepper, barrel);

    int getProductVector(int i) {
        return ProductVector.get(i);
    }

    public void setProductVector(List<Integer> productVector) {
        ProductVector = productVector;
    }

    public List<Integer> getProductVector() {
        //ProductVector = Arrays.asList(apple, cheese, chicken, bread, silk, pepper, barrel);
        return ProductVector;
    }
   /* public int getElement(int index) {
        return ProductVector[index];
    }*/
    public int getSum() {
        int sum = 0;
        for (int ProductIterator = 0; ProductIterator < 7; ProductIterator++) {
            sum += getProductVector(ProductIterator);
    }
        return sum;
    }

    public int getLegalSum() {
        int sum = 0;
        for (int ProductIterator = 0; ProductIterator < 4; ProductIterator++) {
            sum += ProductVector.get(ProductIterator);
            //System.out.println(ProductVector);
        }
        return sum;
    }
    public int getIllegalSum() {
        int sum = 0;
        for (int ProductIterator = 4; ProductIterator < 7; ProductIterator++) {
            sum += ProductVector.get(ProductIterator);
            //System.out.println(ProductVector);
        }
        return sum;
    }

    public void getProduct(int index) {

        switch (index) {
            case 0:
                value = ProductVector.get(0);
                value++;
                ProductVector.set(0, value);
                break;
            case 1:
                value = ProductVector.get(1);
                value++;
                ProductVector.set(1, value);
                break;
            case 2:
                value = ProductVector.get(2);
                value++;
                ProductVector.set(2, value);
                break;
            case 3:
                value = ProductVector.get(3);
                value++;
                ProductVector.set(3, value);
                break;
            case 10:
                value = ProductVector.get(4);
                value++;
                ProductVector.set(4, value);
                break;
            case 11:
                value = ProductVector.get(5);
                value++;
                ProductVector.set(5, value);
                break;
            case 12:
                value = ProductVector.get(6);
                value++;
                ProductVector.set(6, value);
                break;
        }
    }
    public int PickBestIllegal() {
        if (ProductVector.get(4) != 0) {
            return 4;
        }
        else if (ProductVector.get(5)  != 0) {
            return 5;
        }
        else if (ProductVector.get(6)  != 0) {
            return 6;
        }
        else {
            return 0;
        }
    }
}
