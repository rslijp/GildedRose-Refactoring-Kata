package com.gildedrose;

import com.gildedrose.knownlifecycles.AgedBrieLifeCycle;
import com.gildedrose.knownlifecycles.BackstagePassesAging;
import com.gildedrose.knownlifecycles.DefaultAging;
import com.gildedrose.knownlifecycles.LegendaryItemAging;
import java.util.HashMap;
import java.util.Map;

class GildedRose {

    private static final Map<String, AgingProcess> AGING = new HashMap<>();

    static {
        AGING.put("Aged Brie", new AgedBrieLifeCycle());
        AGING.put("Sulfuras, Hand of Ragnaros", new LegendaryItemAging());
        AGING.put("Backstage passes to a TAFKAL80ETC concert", new BackstagePassesAging());
        //Will be removed later
        AGING.put("+5 Dexterity Vest", new DefaultAging());
        AGING.put("Elixir of the Mongoose", new DefaultAging());
        AGING.put("Regular item", new DefaultAging());
    }

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            AgingProcess aging = AGING.get(item.name);
            if(aging!=null){
                item.sellIn--;
                aging.updateQuality(item);
                item.quality=Math.max(item.quality, 0);
                if(aging.hasMaximumQuality()) {
                    item.quality = Math.min(item.quality, 50);
                }
                continue;
            }
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
}
