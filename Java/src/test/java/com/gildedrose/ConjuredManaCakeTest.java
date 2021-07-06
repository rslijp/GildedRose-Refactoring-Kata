package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ConjuredManaCakeTest {

    @Test
    void mana_cake_should_decrease_in_quality_with_two_points_after_a_day() {
        //Given
        Item manaCake = new Item("Conjured Mana Cake", 3, 6);
        GildedRose inn = new GildedRose(new Item[] { manaCake });
        assertEquals( 6,manaCake.quality);

        //When
        inn.updateQuality();

        //Then
        assertEquals(4,manaCake.quality);
    }

    @Test
    void mana_cake_should_decrease_with_one_up_until_sell_in_date() {
        //Given
        Item manaCake = new Item("Conjured Mana Cake", 4, 16);
        GildedRose inn = new GildedRose(new Item[] { manaCake });
        int currentQuality = manaCake.quality;

        //When
        while(manaCake.sellIn>0) {
            inn.updateQuality();
            assertEquals(currentQuality-2,manaCake.quality);
            currentQuality=manaCake.quality;
        }

        //Then
        assertEquals(8, manaCake.quality);
        assertEquals(0, manaCake.sellIn);
    }

    @Test
    void mana_cake_should_decrease_twice_as_fast_in_quality_after_sell_day() {
        //Given
        Item manaCake = new Item("Conjured Mana Cake", 4, 16);
        GildedRose inn = new GildedRose(new Item[] { manaCake });

        while(manaCake.sellIn>0) {
            inn.updateQuality();
        }
        assertEquals(8, manaCake.quality);
        assertEquals(0, manaCake.sellIn);

        //When
        inn.updateQuality();

        //Then
        assertEquals(4, manaCake.quality);
        assertEquals(-1, manaCake.sellIn);
    }

    @Test
    void mana_cake_quality_is_never_negative() {
        //Given
        Item manaCake = new Item("Conjured Mana Cake", 4, 0);
        GildedRose inn = new GildedRose(new Item[] { manaCake });
        assertEquals( 0,manaCake.quality);

        //When
        inn.updateQuality();

        //Then
        assertEquals(0,manaCake.quality);
    }
}
