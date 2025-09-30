package com.sana.rules.executes.action;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

/**
 * @author LON
 * @data 2025/9/27
 */
@Component
public class ActionDispatcher {

    @Resource(name = "ruleActionTaskExecutor")
    private ExecutorService ruleActionExecutor;

    public void dispatch(Runnable action) {
        ruleActionExecutor.submit(() -> {
            try {
                action.run();
            } catch (Exception e) {
                System.out.println("规则动作执行失败" + e);
            }
        });
    }

}
