package ru.finnetrolle.smlr.controllers

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import ru.finnetrolle.smlr.SmlrApplication

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootApplication
@WebAppConfiguration
public class RedirectControllerTest {

    private val NOT_FOUND: Int = 404
    private val BAD_PATH = "/ololo"
    private val HEADER_VALUE = "http://www.eveonline.com"
    private val HEADER_NAME = "Location"
    private val REDIRECT_STATUS: Int = 302
    private val PATH = "/Test"
    @Autowired lateinit var webApplicationContext: WebApplicationContext

    lateinit var mockMvc: MockMvc

    @Before
    fun init() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()
    }

    @Test fun controllerMustRedircetUsWhenRequestIsSuccessful(){
        mockMvc.perform(MockMvcRequestBuilders.get(PATH))
                .andExpect(MockMvcResultMatchers.status().`is`(REDIRECT_STATUS))
                .andExpect(MockMvcResultMatchers.header().string(HEADER_NAME,HEADER_VALUE))
    }

    @Test fun controllerMustReturn404IfBadKey(){
        mockMvc.perform(MockMvcRequestBuilders.get(BAD_PATH))
                .andExpect(MockMvcResultMatchers.status().`is`(NOT_FOUND))


    }
}