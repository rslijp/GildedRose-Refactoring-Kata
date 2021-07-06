package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BackstagePassTest {

    @Test
    void backstage_passes_should_increase_in_quality_after_a_day() {
        //Given
        Item passes = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        GildedRose inn = new GildedRose(new Item[] { passes });
        assertEquals(20,passes.quality);

        //When
        inn.updateQuality();

        //Then
        assertEquals(21,passes.quality);
    }

    @Test
    void backstage_passes_should_increase_with_one_up_until_10_days_before_sell_in_date() {
        //Given
        Item passes = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        GildedRose inn = new GildedRose(new Item[] { passes });
        int currentQuality = passes.quality;

        //When
        while(passes.sellIn>10) {
            inn.updateQuality();
            assertEquals(currentQuality+1,passes.quality);
            currentQuality=passes.quality;
        }

        //Then
        assertEquals(25, passes.quality);
        assertEquals(10, passes.sellIn);
    }

    @Test
    void backstage_passes_should_increase_with_two_up_until_5_days_before_sell_in_date() {
        //Given
        Item passes = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20);
        GildedRose inn = new GildedRose(new Item[] { passes });
        int currentQuality = passes.quality;

        //When
        while(passes.sellIn>5) {
            inn.updateQuality();
            assertEquals(currentQuality+2,passes.quality);
            currentQuality=passes.quality;
        }

        //Then
        assertEquals(30, passes.quality);
        assertEquals(5, passes.sellIn);
    }

    @Test
    void backstage_passes_should_increase_with_three_up_sell_in_date() {
        //Given
        Item passes = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20);
        GildedRose inn = new GildedRose(new Item[] { passes });
        int currentQuality = passes.quality;

        //When
        while(passes.sellIn>0) {
            inn.updateQuality();
            assertEquals(currentQuality+3,passes.quality);
            currentQuality=passes.quality;
        }

        //Then
        assertEquals(35, passes.quality);
        assertEquals(0, passes.sellIn);
    }

    @Test
    void backstage_passes_should_doubles_in_quality_after_sell_day() {
        //Given
        Item passes = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20);
        GildedRose inn = new GildedRose(new Item[] { passes });

        while(passes.sellIn>0) {
            inn.updateQuality();
        }
        assertEquals(35, passes.quality);
        assertEquals(0, passes.sellIn);

        //When
        inn.updateQuality();

        //Then
        assertEquals(0, passes.quality);
        assertEquals(-1, passes.sellIn);
    }

    @Test
    void backstage_passes_cant_have_a_quality_above_50() {
        //Given
        Item passes = new Item("Aged Brie", 2, 60);
        GildedRose inn = new GildedRose(new Item[] { passes });
        assertEquals(60,passes.quality);

        //When
        inn.updateQuality();

        //Then
        assertEquals(50,passes.quality);
    }

}
