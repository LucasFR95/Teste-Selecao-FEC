package org.selecao.estagio.results;

import com.opencsv.CSVReader;
import org.selecao.estagio.CustomComparator;
import org.selecao.estagio.interfaces.Parser;
import org.selecao.estagio.parsers.ParserQuestions;

import java.io.FileReader;
import java.util.*;

public class QuizResult implements Parser {

    ParserQuestions questions = new ParserQuestions();
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
        return 0;
    }

    public void countStatics(List<String[]> list) {
        List<List<String>> results = new ArrayList<>();
        List<List<String>> results2 = new ArrayList<>();

        for(int i = 1 ;  i < list.size() ; i++){

            results.add(Arrays.asList(list.get(i)[1],list.get(i)[4]));
        }

        CustomComparator comparator = new CustomComparator();

        Collections.sort(results, new CustomComparator());

        List<List<String>> nova = new ArrayList<>();
        double countA = 0 ;
        double countB = 0;
        double countC = 0 ;
        double countD = 0;
        double countN = 0;
        double total = 0;
        List<List<Double>> estaticas = new ArrayList<>() ;

        String numero = "1";
        for (List result: results ) {

          if(numero.equals(result.get(0))) {
              if(result.get(1).equals("A")){
                  countA += 1;
              } else if(result.get(1).equals("B")){
                  countB += 1;
              } else if(result.get(1).equals("C")){
                  countC += 1;
              } else if(result.get(1).equals("D")){
                  countD += 1;
              } else if(result.get(1).equals("Não Respondida")){
                  countN += 1;
              }

          } else {
              total = countA + countB + countC + countD + countN;
              estaticas.add(Arrays.asList(Double.parseDouble(numero),countA,countB,countC,countD,countN,total)) ;
              countA = 0 ;
              countB = 0;
              countC = 0 ;
              countD = 0;
              countN = 0;
              total = 0;
              numero = (String)result.get(0);
              if(numero.equals(result.get(0))){
                  if (result.get(1).equals("A")) {
                      countA += 1;
                  } else if (result.get(1).equals("B")) {
                      countB += 1;
                  } else if (result.get(1).equals("C")) {
                      countC += 1;
                  } else if (result.get(1).equals("D")) {
                      countD += 1;
                  } else if (result.get(1).equals("Não Respondida")) {
                      countN += 1;
                  }
              }

          }


        }

        for (int i = 0; i < estaticas.size() ; i++){
            double Código = estaticas.get(i).get(0);
            double A = estaticas.get(i).get(1).doubleValue();
            double B = estaticas.get(i).get(2).doubleValue();
            double C = estaticas.get(i).get(3).doubleValue();
            double D = estaticas.get(i).get(4).doubleValue();
            double N = estaticas.get(i).get(5).doubleValue();
            double T = estaticas.get(i).get(6).doubleValue();

            System.out.println("===="+"Código "+(int)Código+"=======");
            System.out.println("A: "+String.format("%.2f",(A/T)*100)+"%");
            System.out.println("B: "+String.format("%.2f",(B/T)*100)+"%");
            System.out.println("C: "+String.format("%.2f",(C/T)*100)+"%");
            System.out.println("D: "+String.format("%.2f",(D/T)*100)+"%");
            System.out.println("Não Respondida: "+String.format("%.2f",(N/T)*100)+"%");
            System.out.println("Total de participantes: "+(int)T);
            System.out.println("====================");
        }




        return ;
    }

    public static void main(String[] args) {
        QuizResult quiz = new QuizResult();

        quiz.countStatics(quiz.extract());

    }
}
