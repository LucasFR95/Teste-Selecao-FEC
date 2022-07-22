package org.selecao.estagio.interfaces;

import java.util.List;

public interface Parser {
    public  List<String[]> extract();
    public  int count(List<String[]> list);

}
