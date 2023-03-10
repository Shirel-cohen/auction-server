package com.dev.controllers;



import com.dev.objects.User;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.*;



@Controller
public class LiveUpdatesController {

    @Autowired
    private Persist persist;

    private List<SseEmitter> emitterList = new ArrayList<>();
    private Map<String, SseEmitter> emitterMap = new HashMap<>();


    @RequestMapping (value = "/sse-handler", method = RequestMethod.GET)
    public SseEmitter handle (String token, int recipientId) {
        User user = persist.getUserByToken(token);
        SseEmitter sseEmitter = null;
        if (user != null) {
      //      sseEmitter = new SseEmitter(10L * MINUTE);
            String key = createKey(user.getId(), recipientId);
            this.emitterMap.put(key, sseEmitter);
        }
        return sseEmitter;
    }

    private String createKey (int senderId, int recipientId) {
        return String.format("%d_%d", senderId, recipientId);
    }








}
