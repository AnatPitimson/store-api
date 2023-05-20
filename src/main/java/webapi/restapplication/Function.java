package webapi.restapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Function {

    public static void main(String[] args) {
        java.util.function.Function<String,String> stringFunction=name->name+"!";
        List<String> names=new ArrayList<>();
        names.add("name1");
        names.add("name2");
        names.add("name3");
        names.add("name4");

        List<String> newNames=names.stream().map(stringFunction).collect(Collectors.toList());
        System.out.println(newNames);

        List<Integer> namesLength=names.stream().map(yoyododo->yoyododo.length()).collect(Collectors.toList());
        System.out.println(namesLength);

    }

}
