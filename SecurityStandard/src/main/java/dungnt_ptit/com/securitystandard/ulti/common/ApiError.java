/*******************************************************************************
 * (C) Copyright Global CyberSoft (GCS) 2019. All rights reserved. Proprietary
 * and confidential.
 ******************************************************************************/
package dungnt_ptit.com.securitystandard.ulti.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Gets the status.
 *
 * @return the status
 */

/**
 * Gets the code.
 *
 * @return the code
 */
@Getter
public enum ApiError {

    /**
     * The tenant not found.
     */
    INTERNAL_SERVER_ERROR("500", HttpStatus.INTERNAL_SERVER_ERROR),

    BAD_REQUEST("400", HttpStatus.BAD_REQUEST),

    NOT_FOUND("404", HttpStatus.NOT_FOUND),

    ACCESS_DENY("401", HttpStatus.FORBIDDEN);


    /**
     * The status.
     */
    private HttpStatus status;

    /**
     * The code.
     */
    private String code;

    /**
     * Instantiates a new api error.
     *
     * @param code   the code
     * @param status the status
     */
    ApiError(String code, HttpStatus status) {
        this.status = status;
        this.code = code;
    }

}
