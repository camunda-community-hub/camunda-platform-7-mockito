package org.camunda.bpm.extension.test.util;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.String.format;

/**
 * TODO: this is needed on various locations and should move to central lib.
 *
 * @author Jan Galinski
 * @author martin.schimak@plexiti.com
 */
public final class ProcessVariableMaps {

  private ProcessVariableMaps() {
    // hide
  }

  public static Map<String, Object> parseMap(final Object... keyValuePairs) {
    checkArgument(keyValuePairs != null, "keyValuePairs must not be null!");
    checkArgument(keyValuePairs.length % 2 == 0, format("parseMap() must have an even number of arguments, was length='%s'", keyValuePairs.length));

    final Map<String, Object> map = new HashMap<String, Object>();

    for (int i = 0; keyValuePairs != null && i < keyValuePairs.length; i += 2) {
      map.put((String) keyValuePairs[i], keyValuePairs[i + 1]);
    }
    return map;
  }

}
