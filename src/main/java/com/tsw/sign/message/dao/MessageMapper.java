package com.tsw.sign.message.dao;

import com.tsw.sign.message.modle.Message;
import com.tsw.sign.message.modle.MessageQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MessageMapper {
    List<Message> selectAll();

    int count(Message message);

    List<Message> page(Map<String,Object> params);

    Message getById(Long id);

    void deleteMulit(String[] ids);

    void delete(Long id);

    void insert(Message message);

    void update(Message message);

    int ishave(Message message);

    List<Message> selectData(MessageQuery messageQuery);
}
