package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SulfurasTest {

    @Test
    void sulfuras_never_decreases_in_quality() {
        //Given
        Item sulfuras =  new Item("Sulfuras, Hand of Ragnaros", 0, 80);

        GildedRose inn = new GildedRose(new Item[] { sulfuras });
        assertEquals(80,sulfuras.quality);

        //When
        inn.updateQuality();

        //Then
        assertEquals(80,sulfuras.quality);
    }

    @Test
    void sulfuras_also_doesnt_decreases_in_quality_after_sellin_date() {
        //Given
        Item sulfuras =  new Item("Sulfuras, Hand of Ragnaros", -1, 80);

        GildedRose inn = new GildedRose(new Item[] { sulfuras });
        assertEquals(80,sulfuras.quality);

        //When
        inn.updateQuality();

        //Then
        assertEquals(80,sulfuras.quality);
    }


}
