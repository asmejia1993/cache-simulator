package com.asmejia1993.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CacheTest {
    
    Cache cache;
    private final int SIZE_CACHE = 3;
    private HashMap<String, String> mockCache;


    @BeforeEach
    void setUp() {
        mockCache = new HashMap<>();
        mockCache.put("User1", "Andy");
        mockCache.put("User2", "Jorge");
        mockCache.put("User3", "Carlos");
        cache = new Cache(SIZE_CACHE);
    }

    @Test
    @DisplayName("When add more that size it should remove the first")
    void shouldRemoveThatFirsElementWhenAddOneMoreThatSize() {
        cache.addNewKey("User1", "Andy");
        cache.addNewKey("User2", "Jorge");
        cache.addNewKey("User3", "Carlos");
        cache.addNewKey("User4", "Henry");
        String firstElement = cache.getValue("User1");

        assertEquals(SIZE_CACHE, cache.getSizeCache());
        assertEquals(firstElement, cache.getValue("User1"));
    }

    @Test
    @DisplayName("When get a User1 Key should Remove next key")
    void shouldRemoveTheNextKeyIfTheKeyHasIndexZero() {
        List<String> keys = Arrays.asList("User1", "User3");
        cache.addNewKey("User1", "Andy");
        cache.addNewKey("User2", "Jorge");
        cache.addNewKey("User3", "Carlos");

        cache.getValue("User1");

        assertEquals(keys.get(0), cache.printLessUsed().get(0));
        assertEquals(keys.get(1), cache.printLessUsed().get(1));
    }

    @Test
    @DisplayName("When get a any User Key should Remove any key")
    void shouldRemoveTheOldestKeyIfTheKeyHasIndexNoZero() {
        List<String> keys = Arrays.asList("User3");
        cache.addNewKey("User1", "Andy");
        cache.addNewKey("User2", "Jorge");
        cache.addNewKey("User3", "Carlos");

        cache.getValue("User2");
        String valueUser3 = cache.getValue("User3");

        assertEquals(keys, cache.printLessUsed());
        assertEquals(valueUser3, mockCache.get("User3"));
    }

    @Test
    @DisplayName("When get a any Key without save one")
    void shouldReturnNothingwhenNotSavedAnyKey() {

        String valueUser3 = cache.getValue("User3");
        String expectedValued = "";
        assertEquals(expectedValued, valueUser3);
        
    }
}
