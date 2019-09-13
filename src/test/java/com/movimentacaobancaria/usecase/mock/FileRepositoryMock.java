package com.movimentacaobancaria.usecase.mock;

import com.movimentacaobancaria.repository.IFileRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRepositoryMock implements IFileRepository {
    private String separatorPattern = "(\\s){3,}";
    @Override
    public List<String[]> readFile() throws IOException {
        List<String[]> result = new ArrayList<>();

        String[] header = "Data              Descricao                   Valor               Categoria".split(separatorPattern);
        String[] line1 = "01-Mar\t\t  INGRESSO.COM                -159,53             diversao".split(separatorPattern);
        String[] line2 = "01-Jun\t\t  Uber Do Brasil Tecnolog     -7,04               TRANSPORTE".split(separatorPattern);
        String[] line3 = "02-Jan\t\t  uara     -7,04               TRANSPORTE".split(separatorPattern);
        String[] line4 = "17-Feb            TAM SITE                    -430,49             viagem".split(separatorPattern);
        String[] line5 = "21-May\t\t  Antonio Coutinho            120,00".split(separatorPattern);
        String[] line6 = "02-Jun\t\t  Jose Mota                   35".split(separatorPattern);
        result.add(header);
        result.add(line1);
        result.add(line2);
        result.add(line3);
        result.add(line4);
        result.add(line5);
        result.add(line6);
        return result;
    }
}
