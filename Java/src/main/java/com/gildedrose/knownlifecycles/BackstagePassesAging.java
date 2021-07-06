package com.gildedrose.knownlifecycles;

import com.gildedrose.AgingProcess;
import com.gildedrose.Item;

public class BackstagePassesAging implements AgingProcess {

    @Override
    public void updateQuality(Item item) {
        if(item.sellIn<0){
            item.quality=0;
            return;
        }
        if(item.sellIn<5){
            item.quality+=3;
            return;
        }
        if(item.sellIn<10){
            item.quality+=2;
            return;
        }
        item.quality++;
    }

    @Override
    public boolean hasMaximumQuality() {
        return true;
    }
}
