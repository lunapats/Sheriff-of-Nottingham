package main;
import main.ProductLine;

import java.util.ArrayList;
import java.util.List;

public class BasicPlayer extends main.Player {
    private final boolean AcceptBribe = false;
    public BasicPlayer(String strategy, int money, int position) {
        super(strategy, money, position);
    }

    public BasicPlayer(String strategy, int money) {
        super(strategy, money);
    }

    public BasicPlayer(ProductLine products) {
        this.products = products;
    }

    /*public void SackCreation() {
        int ProductOfChoice = 0, number = 0;
        if (products.getLegalSum() == 0) {
            ProductOfChoice = products.PickBestIllegal();
            number = 1;
        }
        else {
            for (int AssetsIterator = 0; AssetsIterator <= 3; AssetsIterator++) {
                if (products.getProductVector(AssetsIterator) > ProductOfChoice) {
                    ProductOfChoice = AssetsIterator;
                    number = products.getProductVector(AssetsIterator);
                }
            }
        }
        setProductOfChoice(ProductOfChoice);
        ArrayList<Integer> newSack = new ArrayList<Integer>();
        for (int ProductIterator = 0; ProductIterator < 7; ProductIterator++) {
            if (ProductIterator == getProductOfChoice()) {
                newSack.add(number);
            } else {
                newSack.add(0);
            }
        }
        setMerchantSack(newSack);
    }*/

    public List<Integer> MerchantSackCreation(){
        //System.out.println(products.getProductVector());
        int ProductOfChoice = 0, number = 0;
        if (products.getLegalSum() == 0) {
            ProductOfChoice = products.PickBestIllegal();
            number = 1;
            //System.out.println("nu ajunge in else");
        }
        else {
            for (int AssetsIterator = 0; AssetsIterator < 4; AssetsIterator++) {
                //System.out.println(products.getProductVector(AssetsIterator));
                if (products.getProductVector(AssetsIterator) >= products.getProductVector(ProductOfChoice)) {
                    ProductOfChoice = AssetsIterator;
                    number = products.getProductVector(AssetsIterator);
                    //System.out.println(number);
                }
            }
            if (ProductOfChoice == 3 && products.getProductVector(3) == products.getProductVector(2)) {
                ProductOfChoice = 2;
            }
        }
        setProductOfChoice(ProductOfChoice);
        ProductLine newSack = new ProductLine();
        for (int ProductIterator = 0; ProductIterator < 7; ProductIterator++) {
            if (ProductIterator == getProductOfChoice()) {
                newSack.getProductVector().set(ProductIterator, number);
            } else {
                newSack.getProductVector().set(ProductIterator, 0);
            }
        }
        //System.out.println(newSack.getProductVector());
        return newSack.getProductVector();
    }
    public List<Integer> ClaimedSackCreation(){
        int ProductOfChoice = 0, number = 0;
        if (products.getLegalSum() == 0) {
            ProductOfChoice = 0;
            number = 1;
        }
        else {
            for (int AssetsIterator = 0; AssetsIterator < 4; AssetsIterator++) {
                if (products.getProductVector(AssetsIterator) >= products.getProductVector(ProductOfChoice)) {
                    ProductOfChoice = AssetsIterator;
                    number = products.getProductVector(AssetsIterator);
                }
            }
            if (ProductOfChoice == 3 && products.getProductVector(3) == products.getProductVector(2)) {
                ProductOfChoice = 2;
            }
        }
        ProductLine newSack = new ProductLine();
        for (int ProductIterator = 0; ProductIterator < 7; ProductIterator++) {
            if (ProductIterator == getProductOfChoice()) {
                newSack.getProductVector().set(ProductIterator, number);
            } else {
                newSack.getProductVector().set(ProductIterator, 0);
            }
        }
        return newSack.getProductVector();
    }
    /*
        //players.set(PlayerIterator) = new BasicPlayer(products);
        int ProductOfChoice = 0, number = 0;
        if (products.getLegalSum() == 0) {
            ProductOfChoice = products.PickBestIllegal();
            number = 1;
            setGoodDeclared(0);
            setGoodsInSack(1);
        }
        else {
            for (int AssetsIterator = 0; AssetsIterator <= 3; AssetsIterator++) {
                if (products.getProductVector(AssetsIterator) > ProductOfChoice) {
                    ProductOfChoice = AssetsIterator;
                    number = products.getProductVector(AssetsIterator);
                    setGoodDeclared(ProductOfChoice);
                    setGoodsInSack(number);
                }
            }
        }
        setProductOfChoice(ProductOfChoice);
        ArrayList<Integer> newSack = new ArrayList<Integer>();
        for (int ProductIterator = 0; ProductIterator < 7; ProductIterator++) {
            if (ProductIterator == getProductOfChoice()) {
                newSack.add(number);
            } else {
                newSack.add(0);
            }
        }
        setMerchantSack(newSack);

        System.out.println(getMerchantSack());
    */
    public int BribeChoice() {
        return 0;
    }
    @Override
    public boolean ChooseToInspect(main.Player player) {
        return true;
    }
}
