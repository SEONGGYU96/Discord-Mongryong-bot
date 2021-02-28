package com.gugyu.mongryongbot.service.command

import com.gugyu.mongryongbot.constant.Commands
import discord4j.core.event.domain.message.MessageCreateEvent
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

private const val helloResponse = "냥냥냐!"

@Service
class HelloCommandService : Command {

    @PostConstruct
    fun init() {
        val command = "!안녕"
        Commands.commands[command] = this
    }

    override fun execute(event: MessageCreateEvent) {
        val channel = event.message.channel.block()

        if(valid(event.message.content)) {
            channel?.let { it.createMessage(getResponse(null)).block() }
        }
    }

    override fun getResponse(contents: Map<String, String>?): String {
        return helloResponse
    }

    override fun valid(message: String): Boolean {
        return true
    }
}