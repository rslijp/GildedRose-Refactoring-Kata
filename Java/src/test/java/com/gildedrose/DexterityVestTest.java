package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DexterityVestTest {

    @Test
    void dexterity_vest_should_decrease_in_quality_after_a_day() {
        //Given
        Item vest = new Item("+5 Dexterity Vest", 10, 20);
        GildedRose inn = new GildedRose(new Item[] { vest });
        assertEquals( 20,vest.quality);

        //When
        inn.updateQuality();

        //Then
        assertEquals(19,vest.quality);
    }

    @Test
    void dexterity_vest_should_decrease_with_one_up_until_sell_in_date() {
        //Given
        Item vest = new Item("+5 Dexterity Vest", 10, 20);
        GildedRose inn = new GildedRose(new Item[] { vest });
        int currentQuality = vest.quality;

        //When
        while(vest.sellIn>0) {
            inn.updateQuality();
            assertEquals(currentQuality-1,vest.quality);
            currentQuality=vest.quality;
        }

        //Then
        assertEquals(10, vest.quality);
        assertEquals(0, vest.sellIn);
    }

    @Test
    void dexterity_vest_should_decrease_twice_as_fast_in_quality_after_sell_day() {
        //Given
        Item vest = new Item("+5 Dexterity Vest", 10, 20);
        GildedRose inn = new GildedRose(new Item[] { vest });

        while(vest.sellIn>0) {
            inn.updateQuality();
        }
        assertEquals(10, vest.quality);
        assertEquals(0, vest.sellIn);

        //When
        inn.updateQuality();

        //Then
        assertEquals(8, vest.quality);
        assertEquals(-1, vest.sellIn);


    }
}
