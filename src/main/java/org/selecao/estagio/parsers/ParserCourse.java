package org.selecao.estagio.parsers;

import com.opencsv.CSVReader;
import org.selecao.estagio.interfaces.Parser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ParserCourse implements Parser {

    String arquivoPerguntas = "C:\\Desafio\\perguntas.csv";
    public  List<String[]> extract(){
       // This method parses the "perguntas.csv" file and return a list of Array String the distinct courses


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

    public int count (List<String[]> list) {

        int count = 1 ;
        String course = list.get(1)[1];

        for (int i = 1; i < list.size() ; i++){
            for (int k = 0 ; k < list.get(1).length ; k++){
                if(!course.equals(list.get(i)[1])){
                    count = count + 1;
                    course = list.get(i)[1];
                }
            }

        }

        return count;
    }

}
