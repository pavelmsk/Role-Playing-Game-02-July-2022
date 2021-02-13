package domain.npc;

import entity.Seller;

public class Merchant implements Seller {
    private int goldMineQuantity = 1;

    @Override
    public String sell(Goods goods) {
        String result = "";
        switch (goods) {
            case GOLD_MINE: {
                if (goldMineQuantity != 0) {
                    goldMineQuantity--;
                    result = "gold mine";
                }
                else result = "Больше шахт нет";
            }
            break;
            case POTION:
                result = "potion";
            break;
        }
        return result;
    }

    public enum Goods {
        POTION,
        GOLD_MINE
    }
}
