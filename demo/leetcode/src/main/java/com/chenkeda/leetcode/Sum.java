package com.chenkeda.leetcode;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sum {

    @Test
    public void SumOfTwoNum() {
        int[] answer = twoSum(new int[]{2, 7, 11, 15}, 9);
        assertEquals(0, answer[0]);
        assertEquals(1, answer[1]);
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

    @Test
    public void AddOfTowNum() {
        {
            ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
            ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
            ListNode result = new ListNode(7, new ListNode(0, new ListNode(8)));
            assertEquals(result, addTwoNumbers(l1, l2));
        }
        {
            ListNode l1 = new ListNode(1, new ListNode(8));
            ListNode l2 = new ListNode(0);
            ListNode result = new ListNode(1, new ListNode(8));
            assertEquals(result, addTwoNumbers(l1, l2));
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean needAddOne = false;
        ListNode first = new ListNode(-1);
        ListNode prefix = first;
        do {
            int v1 = 0, v2 = 0;
            if (l1 != null) {
                v1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                v2 = l2.val;
                l2 = l2.next;
            }
            int sum = v1 + v2;
            sum = needAddOne ? sum + 1 : sum;
            if (sum >= 10) {
                sum -= 10;
                needAddOne = true;
            } else {
                needAddOne = false;
            }
            prefix.next = new ListNode(sum);
            prefix = prefix.next;
        } while (l1 != null || l2 != null);
        if (needAddOne) {
            prefix.next = new ListNode(1);
        }
        return first.next;
    }

    @Data
    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        ListNode(int x) {
            val = x;
        }
    }
}
