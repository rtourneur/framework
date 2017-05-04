package com.raf.fwk.util.aop;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

/**
 * Service for testing logs (implementation).
 * 
 * @author RAF
 */
@Slf4j
@Repository
public final class ServiceExampleImpl implements ServiceExample {

    /**
     * Constructor.
     */
    public ServiceExampleImpl() {
        super();
    }

    /**
     * Method for testing logs (implementation).
     * 
     * @param ident
     *            the identifier
     * @see ServiceExample#getById(Integer)
     */
    @Override
    @Loggable
    public void getById(Integer ident) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.error("Excetption " + e.getMessage(), e);
        }

    }

}
