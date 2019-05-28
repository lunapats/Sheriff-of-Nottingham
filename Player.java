package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Player {
    public boolean isSheriff;
    private String strategy;
    main.ProductLine products = new main.ProductLine();
    private int money = 50;
    private int position;
    private int ProductOfChoice;
    main.ProductLine MerchantSack = new main.ProductLine();
    main.ProductLine ClaimedSack = new main.ProductLine();
    main.ProductLine MerchantStand = new main.ProductLine();
    main.ProductLine ConfiscatedGoods = new main.ProductLine();
    main.ProductLine AcceptedGoods = new main.ProductLine();
    main.ProductLine Profit = new main.ProductLine();
    main.ProductLine Penalty = new main.ProductLine();
    //ArrayList<Integer> Profit = new ArrayList<Integer>(Arrays.asList((2, 2, 3, 4, 9, 8, 7)));
    //Profit.as
    private List<Integer> ExpectedSack;
    private int GoodDeclared;
    private int GoodsInSack;
    private boolean liar;
    private boolean AcceptBribe;
    private int bribe;
    private int score;
    public Player() {
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public main.ProductLine getProfit() {
        return Profit;
    }

    public main.ProductLine getPenalty() {
        return Penalty;
    }

    public main.ProductLine getMerchantStand() {
        return MerchantStand;
    }

    public void setMerchantStand(main.ProductLine merchantStand) {
        MerchantStand = merchantStand;
    }

    public int getBribe() {
        return bribe;
    }

    public boolean isLiar() {
        return liar;
    }

    public void setLiar(boolean liar) {
        this.liar = liar;
    }

    public Player(String strategy, int money) {
        this.strategy = strategy;
        this.money = money;
    }

    public void setGoodsInSack(int goodsInSack) {
        GoodsInSack = goodsInSack;
    }

    public main.ProductLine getMerchantSack() {
        return MerchantSack;
    }

    public void setMerchantSack(main.ProductLine merchantSack) {
        MerchantSack = merchantSack;
    }

    public int getGoodDeclared() {
        return GoodDeclared;
    }

    public void setGoodDeclared(int goodDeclared) {
        GoodDeclared = goodDeclared;
    }

    public int getProductOfChoice() {
        return ProductOfChoice;
    }

    public void setProductOfChoice(int productOfChoice) {
        ProductOfChoice = productOfChoice;
    }

    public Player(String strategy, int money, int position) {
        this.strategy = strategy;
        this.money = money;
        this.position = position;
    }

    public String getStrategy() {
        return strategy;
    }

    public main.ProductLine getProducts() {
        return products;
    }

    public int getMoney() {
        return money;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void PrintPlayer() {
        System.out.println(strategy + position);
        System.out.println(products);
    }

    public List<Integer> getExpectedSack() {
        ExpectedSack = new ArrayList<Integer>(7);
        for (int ProductIterator = 0; ProductIterator < 7; ProductIterator++) {
            //ExpectedSack = new ArrayList<Integer>(7);
            if (ProductIterator == GoodDeclared) {
                ExpectedSack.add(GoodsInSack);
            }
            else {
                ExpectedSack.add(0);
            }
        }
        return ExpectedSack;
    }

    public boolean checkBag(int GoodDeclared, int PlayerInspected, boolean AcceptBribe) {
        for (int ProductIterator = 0; ProductIterator < 7; ProductIterator++) {
            if (MerchantSack == ExpectedSack) {
                return false;
            }
        }
        return true;
    }

    public void setExpectedSack(List<Integer> expectedSack) {
        ExpectedSack = expectedSack;
    }
    public int CardsRecompletion(List<Integer> ProductsOrder, int CardIterator) {
        while (CardIterator < ProductsOrder.size() && products.getSum() != 6) {
            products.getProduct(ProductsOrder.get(CardIterator));
            CardIterator++;
            System.out.println(CardIterator);
        }
        return CardIterator;
    }
    public List<Integer> MerchantSackCreation(){
        return null;
    }

    public int BribeChoice() {
        return 0;
    }

    public boolean AcceptBribe(){
        return false;
    };

    public List<Integer> ClaimedSackCreation() {
        return null;
    }


    public boolean ChooseToInspect(Player player) {
        return true;
    }

}
