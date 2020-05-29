package com.lm.flowlablesearchhistory.eventbus;

/**
 * Created by liming on 2018/3/1.
 */

public class ConcreteFactory extends EventFactory {
    @Override
    public <T extends BaseEvent> T createEvent(Class<T> clz) {
        BaseEvent event = null;
        try {
            event = (BaseEvent) Class.forName(clz.getName()).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (T)event;
    }
}
