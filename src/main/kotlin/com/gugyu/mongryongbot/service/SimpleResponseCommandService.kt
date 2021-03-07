package com.gugyu.mongryongbot.service

import com.gugyu.mongryongbot.constant.Commands
import com.gugyu.mongryongbot.service.command.Command
import discord4j.core.event.domain.message.MessageCreateEvent
import javax.annotation.PostConstruct

abstract class SimpleResponseCommandService(private val command: String): Command {

    @PostConstruct
    fun init() {
        Commands.commands[command] = this
    }

    override fun execute(event: MessageCreateEvent) {
        val channel = event.message.channel.block()

        if(valid(event.message.content)) {
            channel?.let { it.createMessage(getResponse(null)).block() }
        }
    }

    override fun valid(message: String): Boolean {
        return true
    }
}