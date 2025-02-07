package org.github.jandin88.mygl.controller.exception;



import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.Instant;

public record StandardError(    @JsonFormat(
        pattern = "dd/MM/yyyy HH:mm:ss",
        timezone = "GMT-3") Instant timestamp, Integer status, String message,
                            String path) implements Serializable {}
