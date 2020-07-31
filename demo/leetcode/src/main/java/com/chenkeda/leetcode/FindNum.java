package com.chenkeda.leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FindNum {

    @Test
    public void testFindMedianSortedArrays() {
        assertEquals(2.0, findMedianSortedArrays(new int[]{1, 3}, new int[]{2}), 0);
        assertEquals(2.5, findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}), 0);
    }

    /*
    给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
    请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
    你可以假设 nums1 和 nums2 不会同时为空。
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] ints = new int[nums1.length + nums2.length];
        int i = 0;
        int n1 = 0;
        int n2 = 0;
        while (i < ints.length) {
            if (n1 < nums1.length && (n2 >= nums2.length || nums1[n1] <= nums2[n2])) {
                ints[i++] = nums1[n1++];
            } else {
                ints[i++] = nums2[n2++];
            }
        }
        return ints.length % 2 == 0 ?  (ints[ints.length / 2] + ints[ints.length / 2 - 1]) / 2d : ints[ints.length / 2] ;
    }
}
