package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AgedBrieTest {

    @Test
    void aged_brie_should_increase_in_quality_after_a_day() {
        //Given
        Item brie = new Item("Aged Brie", 2, 0);
        GildedRose inn = new GildedRose(new Item[] { brie });
        assertEquals(brie.quality, 0);

        //When
        inn.updateQuality();

        //Then
        assertEquals(1,brie.quality);
    }

    @Test
    void aged_brie_should_increase_up_until_sell_in_date() {
        //Given
        Item brie = new Item("Aged Brie", 2, 0);
        GildedRose inn = new GildedRose(new Item[] { brie });
        int currentQuality = brie.quality;

        //When
        while(brie.sellIn>0) {
            inn.updateQuality();
            assertEquals(currentQuality+1,brie.quality);
            currentQuality=brie.quality;
        }

        //Then
        assertEquals(2, brie.quality);
        assertEquals(0, brie.sellIn);
    }

    @Test
    void aged_brie_should_doubles_in_quality_after_sell_day() {
        //Given
        Item brie = new Item("Aged Brie", 2, 0);
        GildedRose inn = new GildedRose(new Item[] { brie });

        while(brie.sellIn>0) {
            inn.updateQuality();
        }
        assertEquals(2, brie.quality);
        assertEquals(0, brie.sellIn);

        //When
        inn.updateQuality();

        //Then
        assertEquals(4, brie.quality);
        assertEquals(-1, brie.sellIn);
    }
}
