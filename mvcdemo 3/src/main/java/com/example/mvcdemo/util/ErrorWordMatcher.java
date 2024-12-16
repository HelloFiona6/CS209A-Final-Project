package com.example.mvcdemo.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorWordMatcher {
    private static final Pattern pattern = Pattern.compile(
            "\\b(\\w+(error|exception)\\w*)\\b", Pattern.CASE_INSENSITIVE);

    public static Map<String, Integer> countErrorWords(String text,int num) {
        Map<String, Integer> errorCounter = new HashMap<>();
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String match = matcher.group(1).toLowerCase();;
            errorCounter.put(match, errorCounter.getOrDefault(match, 0) + 1);
        }

        // 使用ArrayList来存储条目，然后排序
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(errorCounter.entrySet());
        Collections.sort(entryList, (o1, o2) -> o2.getValue().compareTo(o1.getValue())); // 降序排序

        // 将排序后的条目放回LinkedHashMap以保持顺序

        Map<String, Integer> sortedErrorCounter = new LinkedHashMap<>();
        for (int i = 0; i < Math.min(num,entryList.size()); i++) {
            sortedErrorCounter.put(entryList.get(i).getKey(), entryList.get(i).getValue());
        }

        // sortedErrorCounter.forEach((word, count) -> System.out.println(word + ": " + count));
        return sortedErrorCounter;
    }
    public static int countSpecificErrorWords(String text, String specificError) {
        Map<String, Integer> errorCounter = new HashMap<>();
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String match = matcher.group(1).toLowerCase();
            if (match.contains(specificError.toLowerCase())) {
                errorCounter.put(match, errorCounter.getOrDefault(match, 0) + 1);
            }
        }
        return errorCounter.getOrDefault(specificError.toLowerCase(), 0);
    }
}