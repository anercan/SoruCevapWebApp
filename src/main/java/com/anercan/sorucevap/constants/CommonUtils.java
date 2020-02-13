package com.anercan.sorucevap.constants;

import java.util.*;

public class CommonUtils {

    public static HashMap<Long, String> sortHashMapByValues(HashMap<Long, String> passedMap) {
        List<Long> mapKeys = new ArrayList<>(passedMap.keySet());
        List<String> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        HashMap<Long, String> sortedMap =
                new HashMap<>();

        Iterator<String> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            String val = valueIt.next();
            Iterator<Long> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Long key = keyIt.next();
                String comp1 = passedMap.get(key);
                String comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }
}
