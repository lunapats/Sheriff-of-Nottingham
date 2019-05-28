package main;

import java.util.ArrayList;
import java.util.List;

public class BribePlayer extends main.Player {
    public BribePlayer(String strategy, int money, int position) {
        super(strategy, money, position);
    }
    public BribePlayer(String strategy, int money) {
        super(strategy, money);
    }
    public List<Integer> MerchantSackCreation(){
        int IllegalProduct = 0, ProductOfChoice = 0, number = 0;
        IllegalProduct = products.PickBestIllegal();
        main.ProductLine newSack = new main.ProductLine();
        if (products.getLegalSum() == 6 || getMoney() == 0) {
            for (int AssetsIterator = 0; AssetsIterator < 4; AssetsIterator++) {
                if (products.getProductVector(AssetsIterator) >= number) {
                    ProductOfChoice = AssetsIterator;
                    number = products.getProductVector(AssetsIterator);
                    setGoodDeclared(ProductOfChoice);
                    setGoodsInSack(number);
                }
            }
            for (int ProductIterator = 0; ProductIterator < 7; ProductIterator++) {
                if (ProductIterator == ProductOfChoice) {
                    newSack.getProductVector().set(ProductIterator, number);
                } else {
                    newSack.getProductVector().set(ProductIterator, 0);
                }
            }
        }
        else {
            setGoodDeclared(0);
            for(int ProductIterator = 0; ProductIterator < 4; ProductIterator++) {
                newSack.getProductVector().set(ProductIterator, 0);
            }
            if (products.getLegalSum() != 0) {
                newSack.getProductVector().set(4, products.getProductVector(4));
                newSack.getProductVector().set(5, products.getProductVector(5));
                newSack.getProductVector().set(6, products.getProductVector(6));
                setGoodsInSack(6 - products.getLegalSum());
            }
        }
        setProductOfChoice(ProductOfChoice);
        return newSack.getProductVector();
    }
    public int getBribe() {
        int bribe = BribeChoice();
        return bribe;
    }

    public int BribeChoice() {
        int bribe = 0;
        if (products.getIllegalSum() == 1 || products.getIllegalSum() == 2) {
            bribe = 5;
        }
        if (products.getIllegalSum() > 2) {
            bribe = 10;
        }
        return bribe;
    }
    public List<Integer> ClaimedSackCreation(){
        int IllegalProducts = 0, ProductOfChoice = 0, number = 0;
        //IllegalProducts = products.PickBestIllegal();
        main.ProductLine newSack = new main.ProductLine();
        if (products.getLegalSum() == 6 || getMoney() == 0) {
            for (int AssetsIterator = 0; AssetsIterator < 4; AssetsIterator++) {
                if (products.getProductVector(AssetsIterator) >= number) {
                    ProductOfChoice = AssetsIterator;
                    number = products.getProductVector(AssetsIterator);
                }
            }
        }
        newSack.getProductVector().set(0, products.getIllegalSum());
        if (products.getIllegalSum() == 0) {
            for (int ProductIterator = 0; ProductIterator < 4; ProductIterator++) {
                if (ProductIterator == ProductOfChoice) {
                    newSack.getProductVector().set(ProductIterator, number);
                }
                else {
                    newSack.getProductVector().set(ProductIterator, 0);
                }
            }
        }
        //setProductOfChoice(ProductOfChoice);
        return newSack.getProductVector();
    }
    @Override
    public boolean ChooseToInspect(main.Player player) {
        return true;
    }
}
