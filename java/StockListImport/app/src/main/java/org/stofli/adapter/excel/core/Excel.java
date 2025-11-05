package org.stofli.adapter.excel.core;

import java.io.IOException;
import java.util.List;


public interface Excel<T> {
  List<T> read(String filePath) throws IOException;
  void write(String filePath, List<T> data) throws IOException;
}
