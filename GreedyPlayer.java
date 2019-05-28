package main;

import java.util.ArrayList;
import java.util.List;

public class GreedyPlayer extends main.Player {
    private int money;
    public GreedyPlayer(String strategy, int money, int position) {
        super(strategy, money, position);
    }
    private int round;

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public GreedyPlayer(String strategy, int money) {
        super(strategy, money);
    }
    public List<Integer> MerchantSackCreation() {
        int IllegalProduct = 0, ProductOfChoice = 0, number = 0;
        setRound(this.getRound() + 1);
        if (products.getLegalSum() == 0) {
            ProductOfChoice = products.PickBestIllegal();
            number = 1;
        }
        else {
            for (int AssetsIterator = 0; AssetsIterator <= 3; AssetsIterator++) {
                if (products.getProductVector(AssetsIterator) >= number) {
                    ProductOfChoice = AssetsIterator;
                    number = products.getProductVector(AssetsIterator);
                }
            }
        }
        IllegalProduct = products.PickBestIllegal();
        setProductOfChoice(ProductOfChoice);
        main.ProductLine newSack = new main.ProductLine();
        for (int ProductIterator = 0; ProductIterator < 7; ProductIterator++) {
            if (ProductIterator == getProductOfChoice()) {
                newSack.getProductVector().set(ProductIterator, number);
            } else {
                newSack.getProductVector().set(ProductIterator, 0);
            }
        }
        if (getRound() % 2 == 0 && products.getIllegalSum() != 0) {
            newSack.getProductVector().set(IllegalProduct, 1);
        }
        return newSack.getProductVector();
    }
    public int BribeChoice() {
        return 0;
    }
    public boolean AcceptBribe(main.Player player){
        //System.out.println("Va mitui cu: " + player.BribeChoice());
        if (player.BribeChoice() != 0) {
            return true;
        }
        return false;
    }

    public List<Integer> ClaimedSackCreation(){
        int ProductOfChoice = 0, number = 0;
        if (products.getLegalSum() == 0) {
            number = 1;
        }
        else {
            for (int AssetsIterator = 0; AssetsIterator < 4; AssetsIterator++) {
                if (products.getProductVector(AssetsIterator) >= products.getProductVector(ProductOfChoice)) {
                    ProductOfChoice = AssetsIterator;
                    //System.out.println(ProductOfChoice);
                    number = products.getProductVector(AssetsIterator);
                }
            }
        }
        main.ProductLine newSack = new main.ProductLine();
        for (int ProductIterator = 0; ProductIterator < 7; ProductIterator++) {
            if (ProductIterator == ProductOfChoice) {
                newSack.getProductVector().set(ProductIterator, number);
            } else {
                newSack.getProductVector().set(ProductIterator, 0);
            }
        }
        if (getRound() % 2 == 0 && products.getIllegalSum() != 0) {
            //System.out.println(ProductOfChoice);
            newSack.getProductVector().set(ProductOfChoice, number + 1);
        }
        return newSack.getProductVector();
    }
    @Override
    public boolean ChooseToInspect(main.Player player) {
        if (player.BribeChoice() != 0) {
            //System.out.println(player.getMoney() + "  " + player.BribeChoice());
            player.setMoney(player.getMoney() - player.BribeChoice());
            this.setMoney(this.getMoney() + player.BribeChoice());
            //System.out.println(money + "  " + player.getMoney());
            return false;
        }
        //System.out.println(money);
        return true;
    }
}
