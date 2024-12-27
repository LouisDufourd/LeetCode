package com.plaglefleau

class ListNode(var `val`: Int) {
    var next: ListNode? = null
    override fun toString(): String {
        var currentNode: ListNode? = this
        var string = ""
        while (currentNode != null) {
            string += currentNode.`val`
            currentNode = currentNode.next
        }

        while (string.first() == '0') {
            string.removePrefix("0")
        }
        return string
    }

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is ListNode) {
            return false
        }

        val otherListNode = other

        return otherListNode.toString() == this.toString()
    }

    companion object {
        fun createListNode(integer: Int): ListNode {
            val number = integer.toString()

            val finalListNode:ListNode = ListNode(number[number.length-1].digitToInt())
            var currentListNode = finalListNode
            for(index in number.length-2 downTo 0) {
                currentListNode.next = ListNode(number[index].digitToInt())
                currentListNode = currentListNode.next!!
            }

            return finalListNode
        }

        fun createListNode(intArray: IntArray): ListNode? {
            if(intArray.isEmpty()) {
                return null
            }

            val finalListNode = ListNode(intArray[0])
            var currentListNode = finalListNode
            for(index in 1..<intArray.size) {
                currentListNode.next = ListNode(intArray[index])
                currentListNode = currentListNode.next!!
            }

            return finalListNode
        }
    }
}