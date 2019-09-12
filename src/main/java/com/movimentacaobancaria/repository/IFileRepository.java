package com.movimentacaobancaria.repository;

import java.io.IOException;
import java.util.List;

public interface IFileRepository {
    List<String[]> readFile() throws IOException;
}
