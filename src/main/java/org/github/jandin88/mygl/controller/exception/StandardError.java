package org.github.jandin88.mygl.controller.exception;



import java.io.Serializable;
import java.time.Instant;

public record StandardError(Instant timestamp, Integer status, String message,
                            String path) implements Serializable {}
