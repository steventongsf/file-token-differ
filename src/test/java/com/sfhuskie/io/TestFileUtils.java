package com.sfhuskie.io;
/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * @author Steven Tong
 * 
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;

public class TestFileUtils {
    @Test
    public void testReadFileAsString() throws IOException {
        String s = FileUtils.readFileAsString(System.getProperty("user.dir")+"/src/test/resources/original/Leadingspaces.java");
        assertTrue(s.contains("class Leadingspaces {"));
    }
    @Test
    public void testRemoveAllWhitespace() {
        assertEquals("stevenwashere.",FileUtils.removeAllTabsAndSpaces("\tsteven was\there. "));
    }
    @Test
    public void testRemoveAllWhitespaceWithNewLines() {
        assertEquals("stevenwashere.",FileUtils.removeAllTabsAndSpaces("\tsteven \nwas\t \nhere. \n\t"));
        
    }
    @Test
    public void shouldBeAbleToReturnTheKeyOfTheFirstMatchingValue() throws Exception {
        Map<String, String> names = new LinkedHashMap<>();
        names.put("John", "Doe");
        names.put("Fred", "Flintstone");
        names.put("Jane", "Doe");
        String keyOfTheFirst = names.entrySet().stream().filter(e -> e.getValue().equals("Doe")).findFirst().get().getKey();
        assertEquals("John", keyOfTheFirst);

        try {
            names.entrySet().stream().filter(e -> e.getValue().equals("Donkey")).findFirst().get();
        } catch (NoSuchElementException e){
            // Expected
        }

        Optional<Map.Entry<String, String>> optionalEntry = names.entrySet().stream().filter(e -> e.getValue().equals("Donkey")).findFirst();
        keyOfTheFirst = optionalEntry.isPresent() ? optionalEntry.get().getKey() : null;

        assertNull(keyOfTheFirst);
    }
}
