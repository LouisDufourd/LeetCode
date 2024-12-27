package com.plaglefleau

import kotlin.math.pow

object Solution {
    /**
     * This function has the same result as hasTwoSum but slower
     * @see com.plaglefleau.Solution.hashTwoSum
     * @param nums an IntArray with all number
     * @param target the target int that will be return from the addition of two number in nums
     * @return the two number which ounce added together give the target parameter
     */
    @Deprecated("this function is slower than the newer version hasTwoSum")
    fun twoSum(nums: IntArray, target: Int): IntArray {
        for (i in 0..<nums.size - 1) {
            for (j in i + 1..<nums.size) {
                if (nums[i] + nums[j] == target) return intArrayOf(i, j)
            }
        }
        return intArrayOf()
    }

    /**
     * This function has the same result as twoSum but does it faster
     * @see com.plaglefleau.Solution.twoSum
     *
     * @param nums an IntArray with all number
     * @param target the target int that will be return from the addition of two number in nums
     * @return the two number which ounce added together give the target parameter
     */
    fun hashTwoSum(nums: IntArray, target: Int): IntArray {
        val hash = HashMap<Int, Int>()
        for (i in nums.indices) {
            if (hash[nums[i]] != null) {
                return intArrayOf(hash[nums[i]]!!, i)
            }
            hash[target - nums[i]] = i
        }
        return intArrayOf()
    }

    /**
     * Adds two numbers represented as linked lists in reverse order and returns the sum as a new linked list.
     *
     * @param l1 the first linked list representing a number, where each node contains a single digit.
     * @param l2 the second linked list representing a number, where each node contains a single digit.
     * @return a linked list representing the sum of the two numbers, where each node contains a single digit.
     */
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val resultArray = ArrayList<Int>()
        var currentNumber1Node: ListNode? = l1
        var currentNumber2Node: ListNode? = l2
        var carryIn = 0

        while (true) {
            val res = (currentNumber1Node?.`val` ?: 0) + (currentNumber2Node?.`val` ?: 0) + carryIn

            resultArray.add(res % 10)
            carryIn = if (res >= 10) {
                1
            } else {
                0
            }

            currentNumber1Node = currentNumber1Node?.next
            currentNumber2Node = currentNumber2Node?.next

            if (!(currentNumber1Node != null || currentNumber2Node != null || carryIn > 0)) {
                break
            }
        }


        val finalResult = ListNode(resultArray[0])
        var currentResultNode: ListNode? = finalResult
        for (i in 1..<resultArray.size) {
            currentResultNode!!.next = ListNode(resultArray[i])
            currentResultNode = currentResultNode.next
        }

        return finalResult
    }

    /**
     * Finds the length of the longest substring without repeating characters.
     *
     * @param s the input string to analyze for the longest substring without repeated characters
     * @return the length of the longest substring without repeating characters
     */
    fun lengthOfLongestSubstring(s: String): Int {
        val checking = ArrayList<Char>()

        var biggest = 0

        for (i in s.indices) {
            for (j in i..<s.length) {
                if (checking.contains(s[j])) {
                    if (biggest < checking.size) {
                        biggest = checking.size
                    }
                    checking.clear()
                    break
                } else {
                    checking.add(s[j])
                }
            }
        }

        return biggest
    }

    /**
     * Finds the median of two sorted arrays by merging them and calculating their median value.
     *
     * @param nums1 the first sorted integer array
     * @param nums2 the second sorted integer array
     * @return the median value of the merged sorted arrays
     */
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val completedArray = ArrayList<Int>()

        for (num in nums1) {
            completedArray.add(num)
        }

        for (num in nums2) {
            completedArray.add(num)
        }

        completedArray.sort()

        if (completedArray.size % 2 != 0) {
            return completedArray[Math.floorDiv(completedArray.size, 2)].toDouble()
        } else {
            val index = completedArray.size / 2
            return (completedArray[index] + completedArray[index - 1]) / 2.0
        }
    }

    /**
     * Converts a Roman numeral string to its integer representation.
     *
     * @param s the Roman numeral string to convert
     * @*/
    fun romanToInt(s: String): Int {
        var res = 0

        for (i in s.indices) {
            res += if (i == s.length - 1) {
                romanToInt(s[i])
            } else if (romanToInt(s[i]) < romanToInt(s[i + 1])) {
                romanToInt(s[i]) * -1
            } else {
                romanToInt(s[i])
            }
        }

        return res
    }

    /**
     * Converts an integer to its Roman numeral representation.
     *
     * @param num the integer to convert to a Roman numeral
     * @return the Roman numeral representation of the given integer
     */
    fun intToRoman(num: Int): String {
        val longArray = intToLongArray(num)
        var result = ""
        for (long in longArray) {
            result += intToRomanChar(long.toInt())
        }
        return result
    }

    /**
     * Validates whether the given string `s` contains correctly nested and matched brackets.
     *
     * Nested brackets include round `()`, square `[]`, and curly `{}` braces.
     * The string is considered valid if all brackets are matched and properly nested.
     *
     * @param s The input string to be validated.
     * @return Returns true if the string contains valid and properly nested brackets, false otherwise.
     */
    fun isValid(s: String): Boolean {
        val openPosition = ArrayList<Int>()

        for (index in s.indices) {
            when (s[index]) {
                '(', '[', '{' -> openPosition.add(index)
                ')', ']', '}' -> {
                    if (openPosition.isEmpty()) {
                        return false
                    }

                    val remove = if (s[index] == ')') {
                        1
                    } else {
                        2
                    }

                    if (s[index] - remove == s[openPosition.last]) {
                        openPosition.removeLast()
                        continue
                    } else {
                        return false
                    }
                }
            }
        }

        return openPosition.size == 0
    }

    /**
     * Multiplies two non-negative integers represented as strings and returns the result as a string.
     *
     * This function computes the product of two large integers without converting them to numeric types,
     * making it suitable for very large numbers that may exceed the range of standard numeric types.
     *
     * @param num1 A string representing the first non-negative integer to be multiplied.
     * @param num2 A string representing the second non-negative integer to be multiplied.
     * @return A string representing the product of the two input numbers.
     */
    fun multiply(num1: String, num2: String): String {
        val numberOne = ArrayList<Int>()
        val numberTwo = ArrayList<Int>()

        for (index in num1.length-1 downTo 0) {
            numberOne.add(num1[index].digitToInt())
        }

        for (index in num2.length-1 downTo 0) {
            numberTwo.add(num2[index].digitToInt())
        }

        val resultAdder = ArrayList<String>()

        for (indexNumberOne in 0..<numberOne.size) {
            var carryIn = 0
            val result = ArrayList<Int>()
            for (indexNumberTwo in 0..<numberTwo.size) {
                val calculResult = (numberOne[indexNumberOne] * numberTwo[indexNumberTwo]) + carryIn

                carryIn = Math.floorDiv(calculResult, 10)
                result.add(calculResult%10)
            }

            var resultForAdder = "$carryIn"

            for (index in result.size-1 downTo 0) {
                resultForAdder += result[index]
            }

            resultAdder.add(resultForAdder)
        }

        for (index in 0..<resultAdder.size) {
            resultAdder[index] = resultAdder[index] + "0".repeat(index)
        }

        var sum = "0"
        for (number in resultAdder) {
            sum = add(sum, number.toString())
        }

        return sum
    }

    /**
     * Increments the given integer represented as an array of digits by one.
     *
     * This function takes an array of integers where each element represents a single digit of a number,
     * increments the number by one, and returns the result as an array of digits.
     *
     * @param digits An array of integers where each element represents a single digit of the number.
     *               The most significant digit is at the start of the array.
     * @return An array of integers representing the result of incrementing the input number by one.
     */
    fun plusOne(digits: IntArray): IntArray {
        var string = ""

        for (digit in digits) {
            string += digit
        }

        val stringResult = add(string, "1")

        val result = IntArray(stringResult.length)

        for (index in 0..<stringResult.length) {
            result[index] = stringResult[index].digitToInt()
        }

        return result
    }

    /**
     * Adds two non-negative integers represented as strings and returns their sum as a string.
     *
     * This method handles arbitrarily large numbers, ensuring accurate computation even for numbers
     * that exceed the range of standard numeric types.
     *
     * @param number1 A string representing the first non-negative integer to be added.
     * @param number2 A string representing the second non-negative integer to be added.
     * @return A string representing the sum of the two input numbers.
     */
    fun add(number1: String, number2: String) : String {
        val numberOne = ArrayList<Int>()
        val numberTwo = ArrayList<Int>()

        for (index in number1.length-1 downTo 0) {
            numberOne.add(number1[index].digitToInt())
        }

        for (index in number2.length-1 downTo 0) {
            numberTwo.add(number2[index].digitToInt())
        }

        val resultAdder = ArrayList<Int>()
        var carryIn = 0
        var index = 0
        while (true) {
            if(numberOne.size >= numberTwo.size && numberOne.size <= index) {
                resultAdder.add(carryIn)
                break
            } else if (numberOne.size <= numberTwo.size && numberTwo.size <= index) {
                resultAdder.add(carryIn)
                break
            }

            if(numberOne.size <= index && numberTwo.size > index) {
                val res = numberTwo[index] + carryIn
                resultAdder.add(res%10)
                carryIn = Math.floorDiv(res, 10)
                index++
                continue
            } else if (numberOne.size > index && numberTwo.size <= index) {
                val res = numberOne[index] + carryIn
                resultAdder.add(res%10)
                carryIn = Math.floorDiv(res, 10)
                index++
                continue
            }

            val res = numberOne[index] + numberTwo[index] + carryIn

            carryIn = Math.floorDiv(res, 10)
            resultAdder.add(res%10)

            index++
        }

        var finalString = StringBuilder()
        for (index in resultAdder.size -1 downTo 0) {
            finalString.append(resultAdder[index])
        }

        if(finalString.isEmpty() || finalString.count { it == '0' } == finalString.length) {
            return "0"
        }

        while (finalString.first() == '0') {
            finalString.deleteCharAt(0)
        }

        return finalString.toString()
    }

    /**
     * Converts an integer into a LongArray where each element corresponds to a digit
     * of the integer multiplied by 10 raised to the power of its position.
     *
     * @param integer the integer to be converted into a LongArray
     * @return a LongArray representing the processed digits of the input integer
     */
    private fun intToLongArray(integer: Int): LongArray {
        val string = integer.toString()
        val array = LongArray(string.length)
        val size = array.size - 1

        for (index in size downTo 0) {
            array[index] = Math.round(string[index].digitToInt() * 10.0.pow((size - index)))
        }

        return array
    }

    /**
     * Converts a Roman numeral character to its integer equivalent.
     *
     * @param c the Roman numeral character to convert
     * @return the integer value corresponding to the given Roman numeral character
     */
    private fun romanToInt(c: Char): Int {
        return when (c) {
            'I' -> 1
            'V' -> 5
            'X' -> 10
            'L' -> 50
            'C' -> 100
            'D' -> 500
            'M' -> 1000
            else -> 0
        }
    }

    /**
     * Converts an integer value representing a specific range or unit into its Roman numeral equivalent.
     *
     * @param i the integer value to convert into a Roman numeral representation
     * @return the Roman numeral string corresponding to the given integer value
     */
    private fun intToRomanChar(i: Int): String {
        return when (i) {
            4 -> "IV"
            9 -> "IX"
            40 -> "XL"
            90 -> "XC"
            400 -> "CD"
            900 -> "CM"
            else -> {
                if (i < 5) {
                    "I".repeat(i)
                } else if (i < 10) {
                    "V" + "I".repeat(i - 5)
                } else if (i < 50) {
                    "X".repeat(i / 10)
                } else if (i < 100) {
                    "L" + "X".repeat((i / 10) - 5)
                } else if (i < 500) {
                    "C".repeat(i / 100)
                } else if (i < 1000) {
                    "D" + "C".repeat((i / 100) - 5)
                } else {
                    "M".repeat(i / 1000)
                }
            }
        }
    }
}