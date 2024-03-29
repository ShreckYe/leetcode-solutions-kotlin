class Solution {
    fun fib(N: Int): Int =
        (FIBONACCI_MATRIX pow N).a12AndA21

    data class TwoTimesTwoSymmetricMatrix(val a11: Int, val a12AndA21: Int, val a22: Int) {
        companion object {
            val EYE = TwoTimesTwoSymmetricMatrix(1, 0, 1)
        }

        // The product of two symmetric matrices is a symmetric matrix if and only if they commute.
        infix fun timesCommutatively(other: TwoTimesTwoSymmetricMatrix): TwoTimesTwoSymmetricMatrix =
            TwoTimesTwoSymmetricMatrix(
                a11 * other.a11 + a12AndA21 * other.a12AndA21,
                a11 * other.a12AndA21 + a12AndA21 * other.a22,
                a12AndA21 * other.a12AndA21 + a22 * other.a22
            )

        infix fun pow(exponent: Int): TwoTimesTwoSymmetricMatrix =
            when (exponent) {
                0 -> EYE
                1 -> this
                else -> pow(exponent / 2).let { it timesCommutatively it }.let { if (exponent % 2 == 0) it else it timesCommutatively this }
            }
    }

    companion object {
        val FIBONACCI_MATRIX = TwoTimesTwoSymmetricMatrix(0, 1, 1)
    }
}