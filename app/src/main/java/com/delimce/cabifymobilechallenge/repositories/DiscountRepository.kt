package com.delimce.cabifymobilechallenge.repositories

import com.delimce.cabifymobilechallenge.data.Discount
import com.delimce.cabifymobilechallenge.data.DiscountSeason
import com.delimce.cabifymobilechallenge.data.OrderDetail
import com.delimce.cabifymobilechallenge.data.ProductCode
import kotlin.math.floor


class DiscountRepository {


    companion object {

        /**
         * discount available list
         */
        private fun getDiscounts(): List<Discount> {
            return listOf(
                Discount("3+ SHIRTS", DiscountSeason.WINTER.name, ProductCode.TSHIRT.name, 0.05),
                Discount("2x1 VOUCHER", DiscountSeason.WINTER.name, ProductCode.VOUCHER.name, 0.50)
            )
        }

        /**
         * get discount by code
         */
        fun getDiscountByCode(code: String): Discount {
            return getDiscounts().first { it.productCode == code }
        }


        /**
         * is 2x1 voucher discount applicable?
         */
        fun is2x1VoucherDiscountApplicable(details: List<OrderDetail>): Boolean {
            val voucher = details.first { it.code == ProductCode.VOUCHER.name }
            if (voucher.quantity > 1) {
                return true
            }
            return false
        }

        /**
         * apply 2x1 voucher discount
         */
        fun applyDiscount2x1Voucher(details: List<OrderDetail>): Double {
            if (is2x1VoucherDiscountApplicable(details)) {
                val voucher = details.first { it.code == ProductCode.VOUCHER.name }
                val price = voucher.total / voucher.quantity
                return (floor(voucher.quantity.toDouble() * getDiscounts().first {
                    it.productCode == ProductCode.VOUCHER.name
                            && it.season == DiscountSeason.WINTER.name
                }.value) * price)
            }
            return 0.0
        }


        /**
         * is 3+ shirt discount applicable?
         */
        fun is3tshirtsDiscountApplicable(details: List<OrderDetail>): Boolean {
            val tshirts = details.first { it.code == ProductCode.TSHIRT.name }
            if (tshirts.quantity > 2) {
                return true
            }
            return false
        }

        /**
         * apply 3+ shirt discount
         */
        fun applyDiscount3tshirts(details: List<OrderDetail>): Double {
            if (is3tshirtsDiscountApplicable(details)) {
                val tshirts = details.first { it.code == ProductCode.TSHIRT.name }
                return (tshirts.total * getDiscounts().first {
                    it.productCode == ProductCode.TSHIRT.name
                            && it.season == DiscountSeason.WINTER.name
                }.value)
            }
            return 0.0
        }

    }


}