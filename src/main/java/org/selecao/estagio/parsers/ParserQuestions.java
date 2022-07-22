package org.selecao.estagio.parsers;

import com.opencsv.CSVReader;
import org.selecao.estagio.interfaces.Parser;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ParserQuestions implements Parser {

    String arquivoPerguntas = "C:\\Desafio\\perguntas.csv";
    @Override
    public List<String[]> extract() {
        List<String[]> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(arquivoPerguntas));) {
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
        for (int i = 1 ; i < list.size() ; i++){

            if(!String.valueOf(count).equals(list.get(i)[2])){
                count += 1;
            }
        }
        return count;
    }

    public List<String> showQuestions(List<String[]> list){
        List<String> questions = new ArrayList<>();
        for (int i = 1 ; i < list.size() ; i++){
            questions.add(list.get(i)[3]);
        }

        return questions;
    }
}
