package com.gildedrose.knownlifecycles;

import com.gildedrose.AgingProcess;
import com.gildedrose.Item;

public class LegendaryItemAging implements AgingProcess {

    @Override
    public void updateQuality(Item item) {
        //Always keep shining as bright as the day it was made
    }

    @Override
    public boolean hasMaximumQuality() {
        return false;
    }
}
