package com.stucdy.fcm.global.util;

public class RegexpUtil {

    private static final String[] firstConsonant = {"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ",
            "ㅁ", "ㅂ", "ㅃ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};

    public static String koreanSearchRegexp(String searchQuery) {

        char uniVal = searchQuery.charAt(searchQuery.length() - 1);

        String substring = searchQuery.substring(0, searchQuery.length() - 1);
        if (uniVal >= 0xAC00) {

            uniVal = (char) (uniVal - 0xAC00);

            int firstIdx = uniVal / 28 / 21;
            int middleIdx = (uniVal) / 28 % 21;
            int lastIdx = uniVal % 28;

            if (lastIdx != 0) { //종성까지 다 있는 단어
                return searchQuery;
            } else { //초성, 중성만 있는 단어
                char lastUniVal = (char) ((firstIdx * 21 + middleIdx) * 28 + 27 + (char) 0xAC00);
                return substring + "[" + searchQuery.charAt(searchQuery.length()-1) + "-" + lastUniVal + "]";
            }
        } else {

            //초성만 있는 단어
            int firstIdx = -1;

            for (int i = 0; i < firstConsonant.length; i++) {
                if (firstConsonant[i].equals(String.valueOf(uniVal))) {
                    firstIdx = i;
                    break;
                }
            }

            if (firstIdx == -1) return searchQuery; //초성으로 올 수 없는 자음

            char lastUniVal = (char) ((firstIdx * 21 + 20) * 28 + 27 + (char) 0xAC00);
            return substring + "[" + searchQuery.charAt(searchQuery.length()-1) + "-" + lastUniVal + "]";
        }
    }
}