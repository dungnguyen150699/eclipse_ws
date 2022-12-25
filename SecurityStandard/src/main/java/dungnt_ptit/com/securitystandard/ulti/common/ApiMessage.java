/*******************************************************************************
 * (C) Copyright Global CyberSoft (GCS) 2019. All rights reserved. Proprietary
 * and confidential.
 ******************************************************************************/
package dungnt_ptit.com.securitystandard.ulti.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The Class ApiMessage.
 *
 * @author <a href="mailto:huykq.hq@hitachiconsulting.com">huykq.hq</a>
 */

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiMessage implements Serializable {
  /**
   * The Constant serialVersionUID.
   */
  private static final long serialVersionUID = 61269086266661427L;

  /**
   * The code.
   */
  private String code;

  /**
   * The title.
   */
  private String title;

  /**
   * The message.
   */
  private String message;

  /**
   * The data.
   */
  private Object data;

  /**
   * The success.
   */
  private boolean success;
}
