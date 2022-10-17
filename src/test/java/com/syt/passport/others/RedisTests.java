package com.syt.passport.others;

import com.syt.passport.pojo.entity.Admin;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/17 15:50
 */
@Slf4j
@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Test
    void setValue() {
        String key = "name";
        String value = "lingnian";

        //只要对字符串类型的Value进行操作,必须调用opsForValue()
        ValueOperations<String, Serializable> ops = redisTemplate.opsForValue();
        ops.set(key, value);
        log.debug("向redis中写入Key :{} = value :{} 的数据", key, value);
    }

    @Test
    void getValue() {
        String key = "name";

        ValueOperations<String, Serializable> ops = redisTemplate.opsForValue();
        Serializable value = ops.get(key);
        log.debug("从redis中获取Key :{} = value :{} 的数据", key, value);

    }

    @Test
    void setObjectValue() {
        String key = "Admin1";
        Admin admin = new Admin();
        admin.setId(89L);
        admin.setUsername("testName");
        admin.setNickname("Q.");


        ValueOperations<String, Serializable> ops = redisTemplate.opsForValue();
        ops.set(key, admin);
        log.debug("向redis中写入Key :{} = value :{} 的数据", key, admin);
    }

    @Test
    void getObjectValue() {
        String key = "Admin1";

        ValueOperations<String, Serializable> ops = redisTemplate.opsForValue();
        Serializable value = ops.get(key);
        log.debug("从redis中获取Key :{} = value :{} 的数据", key, value);
        log.debug("取出的数据类型是: {}", value.getClass().getSimpleName());

    }

    @Test
    void getNull() {
        String key = "qweqweq";

        ValueOperations<String, Serializable> ops = redisTemplate.opsForValue();
        Serializable value = ops.get(key);
        log.debug("从redis中获取Key :{} = value :{} 的数据", key, value);
    }

    @Test
    void getKeys() {
        String keyPattern = "*";
        Set<String> keys = redisTemplate.keys(keyPattern);

        log.debug("Keys的大小: {}", keys.size());
        for (String key : keys) {
            log.debug("key = {}", key);
        }
    }

    @Test
    void delete() {
        String key = "name";
        Boolean result = redisTemplate.delete(key);
        log.debug("删除key={},结果: {}", key, result);
    }

    @Test
    void deleteBatch() {
        String keyPattern = "*";
        Set<String> keys = redisTemplate.keys(keyPattern);
        Long count = redisTemplate.delete(keys);
        log.debug("删除多条key={},结果: {}", keys, count);
    }

    @Test
    void setList() {
        List<Admin> adminList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Admin admin = new Admin();
            admin.setId(i + 0L);
            admin.setUsername("testName" + i);
            adminList.add(admin);
        }

        String key = "admins";
        ListOperations<String, Serializable> ops = redisTemplate.opsForList();
        for (Admin admin : adminList) {
            ops.rightPush(key, admin);
        }
        log.debug("向redis中写入列表数据完成.key为: {},写入的列表为: {}", key, adminList);
    }

    @Test
    void listSize() {
        String key = "admins";
        ListOperations<String, Serializable> ops = redisTemplate.opsForList();
        Long size = ops.size(key);
        log.debug("key: {} , size: {}", key, size);
    }

    @Test
    void listRange() {
        String key = "admins";
        long start = 0;
        long end = -1;
        ListOperations<String, Serializable> ops = redisTemplate.opsForList();
        List<Serializable> list = ops.range(key, start, end);
        log.debug("从Redis中读取Key为【{}】的列表，start={}，end={}，获取到的列表长度为：{}",
                key, start, end, list.size());
        for (Serializable item : list) {
            log.debug("{}", item);
        }
    }

}
