package com.gildedrose.knownlifecycles;

import com.gildedrose.AgingProcess;
import com.gildedrose.Item;

public abstract class AbstractSimpleAging implements AgingProcess {

    private final int qualityDegredation;

    AbstractSimpleAging(int qualityDegredation) {
        this.qualityDegredation = qualityDegredation;
    }

    @Override
    public void updateQuality(Item item) {
        item.quality-=qualityDegredation;
        if(item.sellIn<0){
            item.quality-=qualityDegredation;
        }
    }

    @Override
    public boolean hasMaximumQuality() {
        return true;
    }
}
