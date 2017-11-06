package cn.yfjz.core.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang.SerializationException;
import org.springframework.data.redis.serializer.RedisSerializer;

public class RedisValueSerializer implements RedisSerializer<Object>{
	static final byte[] EMPTY_ARRAY = new byte[0];
	
	@Override
    public byte[] serialize(Object source)
        throws SerializationException
    {
        if (source == null){
        	return EMPTY_ARRAY;
        }
        // 打印类型以供反序列化
        return JSON.toJSONBytes(source, SerializerFeature.WriteClassName);
         
    }
     
    @Override
    public Object deserialize(byte[] source)
        throws SerializationException {
        if (source == null || source.length == 0){
        	return null;
        }
         
        return JSON.parse(source);
         
    }
}
