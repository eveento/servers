package com.mgr.server.services;

import com.mgr.server.entity.Memory;
import com.mgr.server.entity.Server;
import com.mgr.server.exceptions.NotFoundHandler;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@CommonsLog
@Service
public class ServerService {

    @Autowired
    private Server server;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbit.input.queue.name}")
    private String rabbitInputName;

    @Value("${rabbit.output.queue.name}")
    private String rabbitOutputName;


@RabbitListener(queues = "${rabbit.output.queue.name}")
    public void rabbitReader(Memory _memory){
        Memory task = findTask(_memory.getName());
        task.setPercent(_memory.getPercent());
    }


    @Async
    public void updateProgressBar(Memory memory){
        rabbitTemplate.convertAndSend(rabbitInputName,memory);
    }

    public Memory findTask(String _uuid) {
        for (Iterator iter = server.getMap().entrySet().iterator(); iter.hasNext(); ) {
            if (server.getMap().containsKey(_uuid)) {
                return server.getMap().get(_uuid);
            }
        }
        throw new NotFoundHandler(_uuid);
    }

}
