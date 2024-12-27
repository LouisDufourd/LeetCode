package com.plaglefleau

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SolutionTest {

    @Test
    fun twoSum() {
        assert(Solution.twoSum(intArrayOf(2, 7, 11, 15), 9).equalsToOtherIntArray(intArrayOf(0, 1)))
        assert(Solution.twoSum(intArrayOf(3, 2, 4), 6).equalsToOtherIntArray(intArrayOf(1, 2)))
        assert(Solution.twoSum(intArrayOf(3, 3), 6).equalsToOtherIntArray(intArrayOf(0, 1)))
    }

    @Test
    fun hashTwoSum() {
        assert(Solution.hashTwoSum(intArrayOf(2, 7, 11, 15), 9).equalsToOtherIntArray(intArrayOf(0, 1)))
        assert(Solution.hashTwoSum(intArrayOf(3, 2, 4), 6).equalsToOtherIntArray(intArrayOf(1, 2)))
        assert(Solution.hashTwoSum(intArrayOf(3, 3), 6).equalsToOtherIntArray(intArrayOf(0, 1)))
    }

    @Test
    fun addTwoNumbers() {
        assertEquals(
            Solution.addTwoNumbers(ListNode.createListNode(342), ListNode.createListNode(465)),
            ListNode.createListNode(807)
        )
        assertEquals(
            Solution.addTwoNumbers(
                ListNode.createListNode(intArrayOf(9, 9, 9, 9, 9, 9, 9)),
                ListNode.createListNode(intArrayOf(9, 9, 9, 9))
            ),
            ListNode.createListNode(10009998)
        )
    }

    @Test
    fun lengthOfLongestSubstring() {
        assertEquals(Solution.lengthOfLongestSubstring("abcabcbb"), 3)
        assertEquals(Solution.lengthOfLongestSubstring("bbbbb"), 1)
        assertEquals(Solution.lengthOfLongestSubstring("pwwkew"), 3)
    }

    @Test
    fun findMedianSortedArrays() {
        assertEquals(Solution.findMedianSortedArrays(intArrayOf(1), intArrayOf(3)), 2.0)
        assertEquals(Solution.findMedianSortedArrays(intArrayOf(1, 2), intArrayOf(3, 4)), 2.5)
    }

    @Test
    fun romanToInt() {
        assertEquals(Solution.romanToInt("III"), 3)
        assertEquals(Solution.romanToInt("LVIII"), 58)
        assertEquals(Solution.romanToInt("MCMXCIV"), 1994)
    }

    @Test
    fun intToRoman() {
        assertEquals(Solution.intToRoman(3749), "MMMDCCXLIX")
        assertEquals(Solution.intToRoman(58), "LVIII")
        assertEquals(Solution.intToRoman(1994), "MCMXCIV")
    }

    @Test
    fun isValid() {
        assertEquals(Solution.isValid("()"), true)
        assertEquals(Solution.isValid("()[]{}"), true)
        assertEquals(Solution.isValid("(]"), false)
        assertEquals(Solution.isValid("([])"), true)
        assertEquals(Solution.isValid("([)]"), false)
        assertEquals(Solution.isValid("["), false)
        assertEquals(Solution.isValid("]"), false)
    }

    @Test
    fun multiply() {
        assertEquals(Solution.multiply("2", "3"), "6")
        assertEquals(Solution.multiply("123", "456"), "56088")
        assertEquals(Solution.multiply("123456789", "987654321"), "121932631112635269")
        assertEquals(Solution.multiply("0", "0"), "0")
        assertEquals(Solution.multiply("498828660196", "840477629533"), "419254329864656431168468")
    }

    @Test
    fun add() {
        assertEquals(Solution.add("1", "1"), "2")
        assertEquals(Solution.add("9", "9"), "18")
        assertEquals(Solution.add("99", "1"), "100")
        assertEquals(Solution.add("56", "38"), "94")
    }

    @Test
    fun plusOne() {
        assert(Solution.plusOne(intArrayOf(1,2,3)).equalsToOtherIntArray(intArrayOf(1,2,4)))
        assert(Solution.plusOne(intArrayOf(4,3,2,1)).equalsToOtherIntArray(intArrayOf(4,3,2,2)))
        assert(Solution.plusOne(intArrayOf(9)).equalsToOtherIntArray(intArrayOf(1,0)))
    }
}

fun IntArray.equalsToOtherIntArray(other: IntArray?): Boolean {
    if (other == null) {
        return false
    }

    if (other.size != this.size) {
        return false
    }

    for (i in other.indices) {
        if (other[i] != this[i]) {
            return false
        }
    }

    return true
}