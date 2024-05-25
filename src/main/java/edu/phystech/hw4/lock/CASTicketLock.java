package edu.phystech.hw4.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kzlv4natoly
 */
public class CASTicketLock {
    private final AtomicInteger nextTicket = new AtomicInteger(0);
    private final AtomicInteger currentTicket = new AtomicInteger(0);

    public void lock() {
        int myTicket = nextTicket.getAndAdd(1);
        while (currentTicket.get() != myTicket) {};
    }

    public void unlock() {
        currentTicket.getAndAdd(1);
    }
}
