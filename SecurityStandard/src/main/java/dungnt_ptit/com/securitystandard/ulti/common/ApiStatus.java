/*******************************************************************************
 * (C) Copyright Global CyberSoft (GCS) 2019. All rights reserved. Proprietary
 * and confidential.
 ******************************************************************************/
package dungnt_ptit.com.securitystandard.ulti.common;

import lombok.Getter;

/**
 * Gets the code.
 *
 * @return the code
 */
@Getter
public enum ApiStatus {

  /**
   * The ok.
   */
  OK("OK"),
  /**
   * The created.
   */
  CREATED("CREATED"),
  /**
   * The deleted.
   */
  DELETED("DELETED"),
  /**
   * The updated.
   */
  UPDATED("UPDATED");

  /**
   * The code.
   */
  private String code;

  /**
   * Instantiates a new api status.
   *
   * @param code the code
   */
  ApiStatus(String code) {
    this.code = code;
  }
}
