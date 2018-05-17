package org.freakz.hokan_ng_springboot.bot.common.cmdpool;

import org.freakz.hokan_ng_springboot.bot.common.jpa.entity.CommandHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: petria
 * Date: 11/5/13
 * Time: 12:09 PM
 *
 * @author Petri Airio <petri.j.airio@gmail.com>
 */

public class CommandRunner implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(CommandRunner.class);

    private long myPid;
    private CommandRunnable runnable;
    private CommandPoolImpl commandPool;
    private Object args;
    private CommandHistory history;

    public CommandRunner(long myPid, CommandRunnable runnable, CommandPoolImpl commandPool, Object args, CommandHistory history) {
        this.myPid = myPid;
        this.runnable = runnable;
        this.commandPool = commandPool;
        this.args = args;
        this.history = history;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("[" + myPid + "] CommandRunner: " + runnable);
        try {
            this.runnable.handleRun(myPid, args);
            this.commandPool.runnerFinished(this, this.history, null);
        } catch (Exception e) {
            log.error("CommandRunner error", e);
            this.commandPool.runnerFinished(this, this.history, e);
        }

    }

    @Override
    public String toString() {
        return String.format("%4d %25s", myPid, runnable.getClass().getSimpleName());
    }

}
