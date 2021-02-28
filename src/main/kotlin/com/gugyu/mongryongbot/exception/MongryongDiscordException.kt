package com.gugyu.mongryongbot.exception

open class MongryongDiscordException (
        val code: ErrorCode,
        override val message: String? = null
) : RuntimeException(
        message ?: code.message
)