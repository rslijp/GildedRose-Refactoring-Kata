package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AgedBrieTest {

    @Test
    void aged_brie_should_increase_in_quality_after_a_day() {
        //Given
        Item brie = new Item("Aged Brie", 2, 0);
        GildedRose inn = new GildedRose(new Item[] { brie });
        assertEquals(0,brie.quality);

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

    @Test
    void aged_brie_cant_have_a_quality_above_50() {
        //Given
        Item brie = new Item("Aged Brie", 2, 60);
        GildedRose inn = new GildedRose(new Item[] { brie });
        assertEquals(60,brie.quality);

        //When
        inn.updateQuality();

        //Then
        assertEquals(50,brie.quality);
    }

}
