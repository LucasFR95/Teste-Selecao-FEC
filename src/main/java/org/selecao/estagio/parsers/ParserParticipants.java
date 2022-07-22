package org.selecao.estagio.parsers;

import com.opencsv.CSVReader;
import org.selecao.estagio.interfaces.Parser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ParserParticipants implements Parser {


    String arquivoRespostas = "C:\\Desafio\\respostas.csv";
    @Override
    public List<String[]> extract() {
        List<String[]> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(arquivoRespostas));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(values);
            }
        } catch(Exception e)  {
            System.out.println(e);
        }
        return records;
    }


    @Override
    public int count(List<String[]> list) {
        int count = 1;
        String day = "Dia 1";
        String registration = list.get(1)[2];
        for (int i = 1 ; i < list.size() ; i++){

            if(!registration.equals(list.get(i)[2]) && day.equals(list.get(i)[0])){
                count += 1;
                registration = list.get(i)[2];
            }

        }
        return count;
    }

    public int count2(List<String[]> list) {
        int count = 1;
        String day = "Dia 2";
        String registration = list.get(1)[2];
        for (int i = 1 ; i < list.size() ; i++){
            if(!registration.equals(list.get(i)[2]) && day.equals(list.get(i)[0])){
                count += 1;
                registration = list.get(i)[2];
            }

        }
        return count;
    }
}
