package com.gildedrose;

import com.gildedrose.knownlifecycles.AgedBrieLifeCycle;
import com.gildedrose.knownlifecycles.BackstagePassesAging;
import com.gildedrose.knownlifecycles.ConjuredItemAging;
import com.gildedrose.knownlifecycles.RegularItemAging;
import com.gildedrose.knownlifecycles.LegendaryItemAging;
import java.util.HashMap;
import java.util.Map;

class GildedRose {

    private static final Map<String, AgingProcess> AGING = new HashMap<>();

    static {
        AGING.put("Aged Brie", new AgedBrieLifeCycle());
        AGING.put("Sulfuras, Hand of Ragnaros", new LegendaryItemAging());
        AGING.put("Backstage passes to a TAFKAL80ETC concert", new BackstagePassesAging());
        AGING.put("Conjured Mana Cake", new ConjuredItemAging());
    }

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            AgingProcess aging = AGING.get(item.name);
            if(aging==null) {
                aging=new RegularItemAging();
            }
            item.sellIn--;
            aging.updateQuality(item);
            item.quality=Math.max(item.quality, 0);
            if(aging.hasMaximumQuality()) {
                item.quality = Math.min(item.quality, 50);
            }

        }
    }
}
