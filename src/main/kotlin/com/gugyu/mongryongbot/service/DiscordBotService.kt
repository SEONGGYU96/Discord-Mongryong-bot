package com.gugyu.mongryongbot.service

import com.gugyu.mongryongbot.constant.Commands
import com.gugyu.mongryongbot.exception.MongryongDiscordLoginFailedException
import discord4j.core.DiscordClientBuilder
import discord4j.core.event.domain.lifecycle.ReadyEvent
import discord4j.core.event.domain.message.MessageCreateEvent
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class DiscordBotService(
        @Value("\${token}") private val discordToken: String
) {

    init {
        this.createBot()
    }

    fun createBot() {
        val discordClient = DiscordClientBuilder.create(discordToken)
                .build()
                .login()
                .onErrorMap { throw MongryongDiscordLoginFailedException() }
                .block()!!

        discordClient.eventDispatcher.on(ReadyEvent::class.java)
                .subscribe { event ->
                    val user = event.self
                    println("Logging in as ${user.username} ${user.discriminator}")
                }

        discordClient.eventDispatcher.on(MessageCreateEvent::class.java)
                .subscribe{
                    val content = it.message.content
                    for(entry in Commands.commands.entries) {
                        if(content.startsWith(entry.key) || content == entry.key) {
                            entry.value.execute(it)
                            break
                        }
                    }
                }

        discordClient.onDisconnect()
    }
}