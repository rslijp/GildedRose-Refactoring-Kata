package com.gildedrose;

import com.gildedrose.Item;

public interface AgingProcess {
    void updateQuality(Item item);
    boolean hasMaximumQuality();
}
