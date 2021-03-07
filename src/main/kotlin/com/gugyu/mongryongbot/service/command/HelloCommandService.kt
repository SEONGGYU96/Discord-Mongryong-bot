package com.gugyu.mongryongbot.service.command

import com.gugyu.mongryongbot.service.SimpleResponseCommandService
import org.springframework.stereotype.Service

private const val command = "몽룡아 안녕"
private const val response = "냥냥냐!"

@Service
class HelloCommandService : SimpleResponseCommandService(command) {

    override fun getResponse(contents: Map<String, String>?): String {
        return response
    }
}