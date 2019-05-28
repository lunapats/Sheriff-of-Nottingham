package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;;
import java.util.List;


public final class Main {

    private static final class GameInputLoader {
        private final String mInputPath;

        private GameInputLoader(final String path) {
            mInputPath = path;
        }

        public main.GameInput load() {
            List<Integer> assetsIds = new ArrayList<>();
            List<String> playerOrder = new ArrayList<>();

            try {
                BufferedReader inStream = new BufferedReader(new FileReader(mInputPath));
                String assetIdsLine = inStream.readLine().replaceAll("[\\[\\] ']", "");
                String playerOrderLine = inStream.readLine().replaceAll("[\\[\\] ']", "");

                for (String strAssetId : assetIdsLine.split(",")) {
                    assetsIds.add(Integer.parseInt(strAssetId));
                }

                for (String strPlayer : playerOrderLine.split(",")) {
                    playerOrder.add(strPlayer);
                }
                inStream.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
            return new main.GameInput(assetsIds, playerOrder);
        }
    }

    public static void main(final String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0]);
        main.GameInput gameInput = gameInputLoader.load();
        // TODO Implement the game logic.
        List<String> PlayersOrder;
        PlayersOrder = gameInput.getPlayerNames();
        List<Integer> ProductsOrder;
        ProductsOrder = gameInput.getAssetIds();
        ArrayList<main.Player> players = new ArrayList<>(3);
        main.BasicPlayer basic = new main.BasicPlayer("basic", 50);
        main.BribePlayer bribe = new main.BribePlayer("bribed", 50);
        main.GreedyPlayer greedy = new main.GreedyPlayer("greedy", 50);
        /*ArrayList<Integer> Profit = new ArrayList<Integer>();
            Profit.add(2);
            Profit.add(3);
            Profit.add(4);
            Profit.add(4);
            Profit.add(9);
            Profit.add(8);
            Profit.add(7);
        ArrayList<Integer> Penalty = new ArrayList<Integer>();
            Penalty.add(2);
            Penalty.add(2);
            Penalty.add(2);
            Penalty.add(2);
            Penalty.add(4);
            Penalty.add(4);
            Penalty.add(4);*/

        for (int PlayerIterator = 0; PlayerIterator < PlayersOrder.size(); PlayerIterator++) {
            if (PlayersOrder.get(PlayerIterator).equals("greedy")) {
                greedy.setPosition(PlayerIterator);
                players.add(greedy);
                greedy.products = new main.ProductLine();
            }
            if (PlayersOrder.get(PlayerIterator).equals("bribed")) {
                bribe.setPosition(PlayerIterator);
                players.add(bribe);
                bribe.products = new main.ProductLine();
            }
            if (PlayersOrder.get(PlayerIterator).equals("basic")) {
                basic.setPosition(PlayerIterator);
                players.add(basic);
                basic.products = new main.ProductLine();
            }
        }
        int CardIterator = 0;
        greedy.setRound(0);
        for (int round = 0; round < 2 * PlayersOrder.size(); round++) {
            //int PlayerIterator = round % players.size();
            //System.out.println();
            //System.out.println("Runda " + round);
            for (int PlayerIterator = 0; PlayerIterator < players.size(); PlayerIterator++) {
                CardIterator = CardsCompletion(players.get(PlayerIterator), ProductsOrder, CardIterator);
            }
            CardIterator = Round(players, ProductsOrder, round, CardIterator);
            /*if (basic.isSheriff ){
                if (players.get(PlayerIterator).equals(basic)) {

                }
            }*/
        }
        CalculateScore(players);
        WriteOutput(players);
        /*for (int PlayerIterator = 0; PlayerIterator < players.size(); PlayerIterator++) {
            System.out.println(PlayersOrder.get(PlayerIterator).toUpperCase() + ": " + players.get(PlayerIterator).getMoney());
        }*/
    }
    public static int CardsCompletion(main.Player player, List<Integer> ProductsOrder, int CardIterator) {
        while (CardIterator < ProductsOrder.size() && player.products.getSum() != 6) {
            player.products.getProduct(ProductsOrder.get(CardIterator));
            CardIterator++;
        }
        return CardIterator;
    }
    private static void SortPlayers(ArrayList<main.Player> players) {
        main.Player aux = new main.Player();
        for (int PlayerIterator = 1; PlayerIterator < players.size(); PlayerIterator++) {
            if (players.get(PlayerIterator).getMoney() > players.get(PlayerIterator - 1).getMoney()) {
                aux.setStrategy(players.get(PlayerIterator).getStrategy());
                players.get(PlayerIterator).setStrategy(players.get(PlayerIterator - 1).getStrategy());
                players.get(PlayerIterator - 1).setStrategy(aux.getStrategy());
                aux.setMoney(players.get(PlayerIterator).getMoney());
                players.get(PlayerIterator).setMoney(players.get(PlayerIterator - 1).getMoney());
                players.get(PlayerIterator - 1).setMoney(aux.getMoney());
            }
        }
        if (players.get(1).getMoney() > players.get(0).getMoney()) {
            aux.setStrategy(players.get(1).getStrategy());
            players.get(1).setStrategy(players.get(0).getStrategy());
            players.get(0).setStrategy(aux.getStrategy());
            aux.setMoney(players.get(1).getMoney());
            players.get(1).setMoney(players.get(0).getMoney());
            players.get(0).setMoney(aux.getMoney());
        }
    }

    private static void WriteOutput(ArrayList<main.Player> players){
        SortPlayers(players);
        for (int PlayerIterator = 0; PlayerIterator < players.size(); PlayerIterator++) {
            System.out.println(players.get(PlayerIterator).getStrategy().toUpperCase() + ": " + players.get(PlayerIterator).getMoney());
        }
    }

    private static void CalculateScore(ArrayList<main.Player> players) {
        AddBonuses(players);
        ArrayList<Integer> Profit = new ArrayList<Integer>();
        Profit.add(2);
        Profit.add(3);
        Profit.add(4);
        Profit.add(4);
        Profit.add(9);
        Profit.add(8);
        Profit.add(7);
        for (int PlayerIterator = 0; PlayerIterator < players.size(); PlayerIterator++) {
            //System.out.println(players.get(PlayerIterator).getMoney());
            for (int ProductIterator = 0; ProductIterator < 7; ProductIterator++) {
                players.get(PlayerIterator).setMoney(players.get(PlayerIterator).getMoney() +
                        (Integer)players.get(PlayerIterator).MerchantStand.getProductVector().get(ProductIterator) * Profit.get(ProductIterator));
            }
        }
    }

    private static void AddBonuses(ArrayList<main.Player> players) {
        AddIllegalBonuses(players);
        AddKingAndQueenBonuses(players);
        for (int PlayerIterator = 0; PlayerIterator < players.size(); PlayerIterator++) {
            //AddIllegalBonuses(players);
            /*for (int ProductIterator = 0; ProductIterator < 4; ProductIterator++) {
                for (int ProductIterator = 0; ProductIterator < 4; ProductIterator++) {

                }
            }*/
        }
    }
    private static void AddIllegalBonuses(ArrayList<main.Player> players) {
        for (int PlayerIterator = 0; PlayerIterator < players.size(); PlayerIterator++) {
            players.get(PlayerIterator).MerchantStand.getProductVector().set(1,(
                    (Integer)players.get(PlayerIterator).MerchantStand.getProductVector().get(1) +
                            3* (Integer)players.get(PlayerIterator).MerchantStand.getProductVector().get(4)));
            players.get(PlayerIterator).MerchantStand.getProductVector().set(2,(
                    (Integer)players.get(PlayerIterator).MerchantStand.getProductVector().get(2) +
                            2* (Integer)players.get(PlayerIterator).MerchantStand.getProductVector().get(6)));

            players.get(PlayerIterator).MerchantStand.getProductVector().set(3,(
                    (Integer)players.get(PlayerIterator).MerchantStand.getProductVector().get(3) +
                            2* (Integer)players.get(PlayerIterator).MerchantStand.getProductVector().get(5)));
        }
    }

    private static void AddKingAndQueenBonuses(ArrayList<main.Player> players) {
        ArrayList<Integer> KingBonus = new ArrayList<>(4);
        ArrayList<Integer> QueenBonus = new ArrayList<>(4);
        ArrayList<Integer> KingBonusAmount = new ArrayList<>(4);
        ArrayList<Integer> QueenBonusAmount = new ArrayList<>(4);
        for (int ProductIterator = 0; ProductIterator < 4; ProductIterator++) {
            KingBonus.add(0);
            QueenBonus.add(0);
        }
        KingBonusAmount.add(20);
        KingBonusAmount.add(15);
        KingBonusAmount.add(15);
        KingBonusAmount.add(10);
        QueenBonusAmount.add(10);
        QueenBonusAmount.add(10);
        QueenBonusAmount.add(10);
        QueenBonusAmount.add(5);
        for (int ProductIterator = 0; ProductIterator < 4; ProductIterator++) {
            for (int PlayerIterator = 0; PlayerIterator < players.size(); PlayerIterator++) {
                int amount = (Integer)players.get(PlayerIterator).MerchantStand.getProductVector().get(ProductIterator);
                if(amount >= KingBonus.get(ProductIterator)) {
                    KingBonus.set(ProductIterator, amount);
                }
                if(amount > QueenBonus.get(ProductIterator) && amount < KingBonus.get(ProductIterator)) {
                    QueenBonus.set(ProductIterator, amount);
                }
            }
            for (int PlayerIterator = 0; PlayerIterator < players.size(); PlayerIterator++) {
                int amount = (Integer)players.get(PlayerIterator).MerchantStand.getProductVector().get(ProductIterator);
                if(amount > QueenBonus.get(ProductIterator) && amount < KingBonus.get(ProductIterator)) {
                    QueenBonus.set(ProductIterator, amount);
                }
            }
            for (int PlayerIterator = 0; PlayerIterator < players.size(); PlayerIterator++) {
                int amount = (Integer)players.get(PlayerIterator).MerchantStand.getProductVector().get(ProductIterator);
                //System.out.println(players.get(PlayerIterator).getStrategy() + " are " + amount);
                if(amount == KingBonus.get(ProductIterator)) {
                    players.get(PlayerIterator).setMoney(players.get(PlayerIterator).getMoney() +
                            KingBonusAmount.get(ProductIterator));
                    //System.out.println(players.get(PlayerIterator).getStrategy() + " is the king of " + ProductIterator);
                }
                if(amount == QueenBonus.get(ProductIterator) && amount < KingBonus.get(ProductIterator)) {
                    players.get(PlayerIterator).setMoney(players.get(PlayerIterator).getMoney() +
                            QueenBonusAmount.get(ProductIterator));
                    //System.out.println(players.get(PlayerIterator).getStrategy() + " is the queen of " + ProductIterator);
                }
            }
        }
    }

    private static main.Player ChooseSheriff(int round, ArrayList<main.Player> players) {
        int SheriffNumber = 0;
        for (int PlayerIterator = 0; PlayerIterator < players.size(); PlayerIterator++) {
            if ((PlayerIterator - round) % players.size() == 0) {
                players.get(PlayerIterator).isSheriff = true;
                SheriffNumber = PlayerIterator;
            }
            else {
                players.get(PlayerIterator).isSheriff = false;
            }
        }
        return players.get(SheriffNumber);
    }
    private static void TransferToMerchantStand(main.Player player) {
        for (int ProductIterator = 0; ProductIterator < 7; ProductIterator++) {
            player.MerchantStand.getProductVector().set(ProductIterator,((Integer)player.MerchantStand.getProductVector().get(ProductIterator) + (Integer)player.MerchantSack.getProductVector().get(ProductIterator)));
            player.products.getProductVector().set(ProductIterator,((Integer)player.products.getProductVector().get(ProductIterator) - (Integer)player.MerchantSack.getProductVector().get(ProductIterator)));
        }
    }
    private static int Round(ArrayList<main.Player> players, List<Integer> ProductsOrder, int round, int CardIterator) {
        main.Player Sheriff = ChooseSheriff(round, players);
        for (int PlayerIterator = 0; PlayerIterator < players.size(); PlayerIterator++) {
            //System.out.println(players.get(PlayerIterator).products.getProductVector());
            CardIterator = players.get(PlayerIterator).CardsRecompletion(ProductsOrder, CardIterator);
            //System.out.println("    " + players.get(PlayerIterator).getStrategy() + players.get(PlayerIterator).products.getProductVector() + players.get(PlayerIterator).isSheriff);
            if (!players.get(PlayerIterator).equals(Sheriff)) {
                players.get(PlayerIterator).MerchantSack.setProductVector(players.get(PlayerIterator).MerchantSackCreation());
                players.get(PlayerIterator).ClaimedSack.setProductVector(players.get(PlayerIterator).ClaimedSackCreation());
                //System.out.println("Merchant Sack: " + players.get(PlayerIterator).MerchantSack.getProductVector());
                //System.out.println("Claimed Sack: " + players.get(PlayerIterator).ClaimedSack.getProductVector());
                /*ConfiscatedGoods(players.get(PlayerIterator));
                AcceptedGoods(players.get(PlayerIterator));
                System.out.println("MerchantSack: " + players.get(PlayerIterator).MerchantSack.getProductVector());*/
                //players.get(PlayerIterator).setLiar(Sheriff.confiscate(players.get(PlayerIterator)));
                if(Sheriff.ChooseToInspect(players.get(PlayerIterator))) {
                    boolean Liar = ConfiscatedGoods(Sheriff, players.get(PlayerIterator));
                    AcceptedGoods(Sheriff, players.get(PlayerIterator), Liar);
                }
                TransferToMerchantStand(players.get(PlayerIterator));
            }
            //System.out.println(players.get(PlayerIterator).MerchantStand.getProductVector());
            //System.out.println(players.get(PlayerIterator).getMoney());
        }
        /*for (int PlayerIterator = 0; PlayerIterator < players.size(); PlayerIterator++) {
            if(players.get(PlayerIterator).isLiar() == false) {
                TransferToMerchantStand(players.get(PlayerIterator));
            }
            if(players.get(PlayerIterator).isLiar() == true) {
                ConfiscatedGoods(players.get(PlayerIterator));
                //CorrectGoods(players.get(PlayerIterator))
            }
            TransferToMerchantStand(players.get(PlayerIterator));
        }*/
    return CardIterator;
    }

    private static boolean ConfiscatedGoods(main.Player Sheriff, main.Player player) {
        boolean isClean = true;
        ArrayList<Integer> Penalty = new ArrayList<Integer>();
        Penalty.add(2);
        Penalty.add(2);
        Penalty.add(2);
        Penalty.add(2);
        Penalty.add(4);
        Penalty.add(4);
        Penalty.add(4);
        for (int ProductIterator = 0; ProductIterator < 7; ProductIterator++) {
            player.ConfiscatedGoods.getProductVector().set(ProductIterator,max(0, ((Integer)player.MerchantSack.getProductVector().get(ProductIterator) - (Integer)player.ClaimedSack.getProductVector().get(ProductIterator))));
            player.products.getProductVector().set(ProductIterator, (Integer)player.products.getProductVector().get(ProductIterator) - (Integer)player.ConfiscatedGoods.getProductVector().get(ProductIterator));
            player.setMoney(player.getMoney() - Penalty.get(ProductIterator) * (Integer)player.ConfiscatedGoods.getProductVector().get(ProductIterator));
            Sheriff.setMoney(Sheriff.getMoney() + Penalty.get(ProductIterator) * (Integer)player.ConfiscatedGoods.getProductVector().get(ProductIterator));
            if ((Integer)player.MerchantSack.getProductVector().get(ProductIterator) != (Integer)player.ClaimedSack.getProductVector().get(ProductIterator)) {
                isClean = false;
            }
        }
        //System.out.println("Confiscated Goods: " + player.ConfiscatedGoods.getProductVector());
        if (isClean == true) {
            return false;
        }
        else {
            return true;
        }
    }
    private static void AcceptedGoods(main.Player Sheriff, main.Player player, boolean liar) {
        //ArrayList<Integer> Penalty = new ArrayList<Integer>();
        for (int ProductIterator = 0; ProductIterator < 7; ProductIterator++) {
            player.AcceptedGoods.getProductVector().set(ProductIterator, ((Integer)player.MerchantSack.getProductVector().get(ProductIterator) - (Integer)player.ConfiscatedGoods.getProductVector().get(ProductIterator)));
            player.MerchantSack.getProductVector().set(ProductIterator, player.AcceptedGoods.getProductVector().get(ProductIterator));
            if (liar == false) {
                player.setMoney(player.getMoney() + 2 * (Integer)player.AcceptedGoods.getProductVector().get(ProductIterator));
                Sheriff.setMoney(Sheriff.getMoney() - 2 * (Integer)player.AcceptedGoods.getProductVector().get(ProductIterator));
            }
            //player.MerchantStand.setProductVector(player.AcceptedGoods.getProductVector());
        }
        //player.MerchantStand.setProductVector(player.AcceptedGoods.getProductVector());
        //System.out.println("Accepted Goods: " + player.AcceptedGoods.getProductVector());
    }
    public static int max(int a, int b) {
        if (a > b) {
            return a;
        }
        else {
            return b;
        }
    }
}