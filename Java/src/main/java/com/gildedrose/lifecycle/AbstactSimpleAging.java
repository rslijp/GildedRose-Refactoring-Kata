package com.gildedrose.lifecycle;

import com.gildedrose.Item;

public class AbstactSimpleAging implements AgingProcess {

    private final int qualityDegredation;

    AbstactSimpleAging(int qualityDegredation) {
        this.qualityDegredation = qualityDegredation;
    }

    @Override
    public void updateQuality(Item item) {
        item.sellIn--;
        item.quality-=qualityDegredation;
        if(item.sellIn<0){
            item.quality-=qualityDegredation;
        }
    }
}
