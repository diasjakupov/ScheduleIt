package com.example.scheduleit.data.models

object NotificationDelay {
    const val BEFORE_10 = 600000
    const val BEFORE_10_STR = "10 minutes before"

    const val BEFORE_15 = 900000
    const val BEFORE_15_STR = "15 minutes before"

    val NOTIFICATION_DELAY =
        arrayOf<Pair<String, Int>>(
            NotificationDelay.BEFORE_10_STR to NotificationDelay.BEFORE_10,
            NotificationDelay.BEFORE_15_STR to NotificationDelay.BEFORE_15
        )
}