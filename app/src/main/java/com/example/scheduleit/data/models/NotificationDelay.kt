package com.example.scheduleit.data.models

object NotificationDelay {
    private const val BEFORE_10 = 600_000
    private const val BEFORE_10_STR = "10 minutes before"

    private const val BEFORE_15 = 900_000
    private const val BEFORE_15_STR = "15 minutes before"

    private const val BEFORE_5 = 300_000
    private const val BEFORE_5_STR = "5 minutes before"

    private const val BEFORE_20 = 1_200_000
    private const val BEFORE_20_STR = "20 minutes before"

    val NOTIFICATION_DELAY =
        arrayOf<Pair<String, Int>>(
            BEFORE_5_STR to BEFORE_5,
            BEFORE_10_STR to BEFORE_10,
            BEFORE_15_STR to BEFORE_15,
            BEFORE_20_STR to BEFORE_20
        )
}