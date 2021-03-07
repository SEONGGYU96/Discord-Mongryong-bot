package com.gugyu.mongryongbot.service.command

import com.gugyu.mongryongbot.constant.Commands
import com.gugyu.mongryongbot.service.SimpleResponseCommandService
import org.springframework.stereotype.Service

private const val command = "몽룡아"

@Service
class HelpCommandService: SimpleResponseCommandService(command) {

    override fun getResponse(contents: Map<String, String>?): String {
        val response = StringBuilder("냥냐냐! 냥냥냐냐냥!").append("\n\n")

        val iterator = Commands.commands.keys.iterator()
        while (iterator.hasNext()) {
            val c = iterator.next()
            if (c != command) {
                response.append("- ").append(c).append("\n")
            }
        }
        return response.toString()
    }
}