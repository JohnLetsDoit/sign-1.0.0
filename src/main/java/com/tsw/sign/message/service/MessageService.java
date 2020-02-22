package com.tsw.sign.message.service;

import com.tsw.sign.commons.BaseResult;
import com.tsw.sign.commons.PageInfo;
import com.tsw.sign.message.modle.Message;
import com.tsw.sign.message.modle.MessageQuery;

import java.text.ParseException;
import java.util.List;

public interface MessageService {
    List<Message> selectAll();

    PageInfo<Message> page(int draw, int start, int length, Message message );

    int count(Message message);

    Message getById(Long id);

    void deleteMulit(String[] ids);

    void delete(Long id);

    void insert(Message message);

    void update(Message message);

    BaseResult save(Message message) throws ParseException;

    int ishave(Message message);

    List<Message> selectData(MessageQuery messageQuery);

}
