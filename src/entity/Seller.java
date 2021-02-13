package entity;

import domain.npc.Merchant;

public interface Seller {
    String sell(Merchant.Goods goods);
}
