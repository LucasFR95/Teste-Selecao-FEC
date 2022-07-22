package org.selecao.estagio.controller;

import org.selecao.estagio.interfaces.Parser;
import org.selecao.estagio.parsers.ParserCourse;
import org.selecao.estagio.parsers.ParserParticipants;
import org.selecao.estagio.parsers.ParserQuestions;
import org.selecao.estagio.results.QuizResult;

import java.util.List;

public class Main {


    public static void main(String[] args) {

        ParserParticipants participants = new ParserParticipants();
        Parser courses = new ParserCourse();
        Parser questions = new ParserQuestions();
        QuizResult quizResult = new QuizResult();
        List<String[]> extractQuestions = questions.extract();
        List<String[]> extractCourses = courses.extract();
        List<String[]> extractParticipants = participants.extract();


        System.out.println("Total de Cursos participantes: "+ courses.count(extractCourses));
        System.out.println("Total de Perguntas: "+ questions.count(extractQuestions));
        System.out.println("Total de Participantes-Dia 1: " + participants.count(extractParticipants) );
        System.out.println("Total de Participantes-Dia 2: " + participants.count2(extractParticipants) );

        // ===== Below show the results by questions =====

        System.out.println("=========== Resultados da pesquisa por perguntas============");

        List<String[]> list = quizResult.extract();

        quizResult.countStatics(list);



    }

}
