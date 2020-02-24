package com.cabifychallenge.angelesvazquez

import org.junit.Assert
import org.junit.Test

class ShoppingOperationTest {

    /**
     * Items: VOUCHER, TSHIRT, MUG
     * Total: 32.50€
     * */
    @Test
    fun addition_isCorrect1() {
        Assert.assertEquals(4, 2 + 2)
    }

    /**
     * Items: VOUCHER, TSHIRT, VOUCHER
     * Total: 25.00€
     * */
    @Test
    fun addition_isCorrect2() {
        Assert.assertEquals(4, 2 + 2)
    }

    /**
     * Items: TSHIRT, TSHIRT, TSHIRT, VOUCHER, TSHIRT
     * Total: 81.00€
     * */
    @Test
    fun addition_isCorrect3() {
        Assert.assertEquals(4, 2 + 2)
    }

    //
    /**
     * Items: VOUCHER, TSHIRT, VOUCHER, VOUCHER, MUG, TSHIRT, TSHIRT
     * Total: 74.50€
     * */
    @Test
    fun addition_isCorrect4() {
        Assert.assertEquals(4, 2 + 2)
    }

}