package edu.phystech.hw5.service;

import edu.phystech.hw5.annotation.validation.NotBlank;
import edu.phystech.hw5.annotation.validation.Size;

/**
 * @author kzlv4natoly
 */
public interface Validator {
    void validate(Object object);
}
