package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RegularItemTest {

    @Test
    void regular_should_decrease_in_quality_after_a_day() {
        //Given
        Item regular = new Item("Regular item", 10, 20);
        GildedRose inn = new GildedRose(new Item[] { regular });
        assertEquals( 20,regular.quality);

        //When
        inn.updateQuality();

        //Then
        assertEquals(19,regular.quality);
    }

    @Test
    void regular_should_decrease_with_one_up_until_sell_in_date() {
        //Given
        Item regular = new Item("Regular item", 10, 20);
        GildedRose inn = new GildedRose(new Item[] { regular });
        int currentQuality = regular.quality;

        //When
        while(regular.sellIn>0) {
            inn.updateQuality();
            assertEquals(currentQuality-1,regular.quality);
            currentQuality=regular.quality;
        }

        //Then
        assertEquals(10, regular.quality);
        assertEquals(0, regular.sellIn);
    }

    @Test
    void regular_should_decrease_twice_as_fast_in_quality_after_sell_day() {
        //Given
        Item regular = new Item("Regular item", 10, 20);
        GildedRose inn = new GildedRose(new Item[] { regular });

        while(regular.sellIn>0) {
            inn.updateQuality();
        }
        assertEquals(10, regular.quality);
        assertEquals(0, regular.sellIn);

        //When
        inn.updateQuality();

        //Then
        assertEquals(8, regular.quality);
        assertEquals(-1, regular.sellIn);
    }

    @Test
    void regular_quality_is_never_negavtive() {
        //Given
        Item regular = new Item("Regular item", 1, 0);
        GildedRose inn = new GildedRose(new Item[] { regular });
        assertEquals( 0,regular.quality);

        //When
        inn.updateQuality();

        //Then
        assertEquals(0,regular.quality);
    }
}
