package com.chenkeda.leetcode;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class Str {

    /*
    给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     */
    @Test
    public void testLengthOfLongestSubstring() {
        assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, lengthOfLongestSubstring("pwwkew"));
        assertEquals(2, lengthOfLongestSubstring("au"));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int maxLength = 0;
        int start = 0;
        for (int i = 0; i < chars.length; i++) {
            int length = -1;
            char charToCompareSame = chars[i];
            if (i != 0) {
                for (int j = i - 1; j >= start; j--) {
                    if (chars[j] == charToCompareSame) {
                        start = j + 1;
                        length = i - j;
                        break;
                    }
                }
            } else {
                length = 1;
            }
            if (length == -1) {
                length = i + 1 - start;
            }
            maxLength = Math.max(maxLength, length);
        }
        return maxLength;
    }


    /*
    暴力
     */
    @Test
    public void testLongestPalindrome1() {
        testLongestPalindrome(this::longestPalindrome1);
    }

    public void testLongestPalindrome(Function<String, String> f) {
        assertEquals("bab", f.apply("babad"));
        assertEquals("bb", f.apply("cbbd"));
        assertEquals("bbb", f.apply("bbb"));
        assertEquals("bbbb", f.apply("bbbb"));
        assertEquals("", f.apply(""));
        assertEquals("a", f.apply("a"));
        assertEquals("a", f.apply("ac"));
        assertEquals("bb", f.apply("bb"));
        assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", f.apply("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    /*
    动态规划
     */
    @Test
    public void testLongestPalindrome2() {
        testLongestPalindrome(this::longestPalindrome2);
    }

    private String longestPalindrome2(String s) {
        if (s != null && s.length() < 2) {
            return s;
        }
        String maxPalindrome = "";
        Boolean[][] isPalindrome = new Boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j >=i; j--) {
                if (isPalindrome(s, i, j, isPalindrome)) {
                    String palindrome = s.substring(i, j + 1);
                    if (palindrome.length() > maxPalindrome.length()) {
                        maxPalindrome = palindrome;
                    }
                }
            }
        }
        return maxPalindrome;
    }

    private boolean isPalindrome(String str, int start, int end, Boolean[][] isPalindrome) {
        if (isPalindrome[start][end] != null && !isPalindrome[start][end]) {
            return false;
        }
        if (start >= end) {
            return true;
        }
        if (str.charAt(start) == str.charAt(end)) {
            if (isPalindrome(str, start + 1, end - 1, isPalindrome)) {
                isPalindrome[start][end] = true;
                return true;
            }
        }
        isPalindrome[start][end] = false;
        return false;
    }

    /*
    给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     */
    public String longestPalindrome1(String s) {
        if (s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int max = 0;
        String maxPalindrome = null;
        for (int i = 0; i < chars.length; i++) {
            String tempMax = calculateMax1(i, chars);
            if (tempMax.length() > max) {
                maxPalindrome = tempMax;
                max = tempMax.length();
            }
            tempMax = calculateMax2(i, chars);
            if (tempMax.length() > max) {
                maxPalindrome = tempMax;
                max = tempMax.length();
            }
        }
        return maxPalindrome;
    }

    private String calculateMax1(int i, char[] chars) {
        int left = i;
        int right = i;
        while (left <= right && left >= 0 && right < chars.length) {
            if (chars[left] == chars[right]) {
                left--;
                right++;
            } else {
                break;
            }
        }
        left++;
        right--;
        return new String(chars).substring(left, right + 1);
    }

    private String calculateMax2(int i, char[] chars) {
        int left = i - 1;
        int right = i;
        while (left <= right && left >= 0 && right < chars.length) {
            if (chars[left] == chars[right]) {
                left--;
                right++;
            } else {
                break;
            }
        }
        left++;
        right--;
        return new String(chars).substring(left, right + 1);
    }
}
