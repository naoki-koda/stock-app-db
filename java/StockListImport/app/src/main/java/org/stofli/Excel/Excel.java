package org.stofli.excel;

import java.io.IOException;
import java.util.List;


public interface Excel<T> {
  List<T> read() throws IOException;
}
