package com.hehanpeng.framework.cloudhhp.module.transport.mq.handler;

import com.hehanpeng.framework.cloudhhp.common.mq.MqMessage;
import com.hehanpeng.framework.cloudhhp.module.transport.configuration.MqBusiServiceContainer;
import com.hehanpeng.framework.cloudhhp.module.transport.mq.MqBusiService;
import com.hehanpeng.framework.cloudhhp.module.transport.mq.MqBusiServiceCallback;
import com.hehanpeng.framework.cloudhhp.module.transport.mq.consumer.AbstractMqConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * created with Intellij IDEA 2017.2
 *
 * @author: hehanpeng
 * Email: 287737281@qq.com
 * Date: 2019/6/12
 * Time: 17:50
 */
@Component
@Slf4j
public class TransportHandler extends AbstractMqConsumer<MqMessage> {

    @Autowired
    Executor asyncServiceExecutor;

    @Override
    public void handler(MqMessage msg) throws Exception {

        MqBusiService messageHandler = MqBusiServiceContainer.getMessageHandler(msg.getTransnetId());

        //异步调用 consumer核心线程20 lambda Runnable
        asyncServiceExecutor.execute(() -> {
            messageHandler.asyncExecuteFunc(msg, new MqBusiServiceCallback<MqMessage>() {
                @Override
                public void onSuccess(MqMessage mqMessage) {
                    log.info("transport TransportHandler asyncService success: {}", mqMessage);
                }

                @Override
                public void onException(Throwable e) {
                    log.error("transport TransportHandler asyncService error", e);
                }
            });
        });
    }
}
