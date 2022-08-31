package edu.fudan.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author fdse
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CoverageMessage<T> {
    String uuid;
    Integer spanId;
    String service;
    T data;
}
