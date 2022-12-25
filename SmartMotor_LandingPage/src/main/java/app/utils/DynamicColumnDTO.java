package vn.viettel.app.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DynamicColumnDTO {

  private String header;
  private String property;
  private int percentWidth;
}
