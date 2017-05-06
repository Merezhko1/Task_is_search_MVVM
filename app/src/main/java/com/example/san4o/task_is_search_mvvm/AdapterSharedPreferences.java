package com.example.san4o.task_is_search_mvvm;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.san4o.task_is_search_mvvm.model.Person;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by san4o on 11.03.17.
 */

public class AdapterSharedPreferences {
    public static final String PREFS_NAME = "CRUD_APP";//Название бд
    public static final String PERSON_CONSTANT = "Person Constant";


    public void savePerson(Context context, List<Person> persons) {
        SharedPreferences preferences;
        SharedPreferences.Editor editor;
        //Позволяет работать с данными, считывать и записывать их
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        //PREFS_NAME - название базы данных
        //MODE_PRIVATE - используется для настройки доступа, это значит после сохранения данные будут видны только этому приложению
        editor = preferences.edit();//необходим для редактирования данных

        Gson gson = new Gson();//надо подключить эту библиотеку
        String jsonPerson = gson.toJson(persons);//преобразовываем persons в строку
        editor.putString(PERSON_CONSTANT, jsonPerson);//по ключу ложим строку
        editor.commit();//необходим что бы данные сохранились
    }

    public void addPerson(Context context, Person person) {
        List<Person> personList = getPersons(context);
        if (personList == null) {
            personList = new ArrayList<Person>();
        }
        personList.add(person);
        savePerson(context, personList);
    }

    public void removePerson(Context context, Person person) {
        List<Person> personList = getPersons(context);
        if (personList != null) {
            personList.remove(person);
            savePerson(context, personList);
        }
    }

    public ArrayList<Person> getPersons(Context context) {
        SharedPreferences preferences;
        List<Person> personList;

        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (preferences.contains(PERSON_CONSTANT)) {//проверяет есть ли в обьекте, такая строка
            String jsonPerson = preferences.getString(PERSON_CONSTANT, null);//читаем с помощью getString, PERSON_CONSTANT - ключ, null - значение по кмолчанию
            Gson gson = new Gson();
            Person[] personsItems = gson.fromJson(jsonPerson, Person[].class);//преобразовываем из строки в массив

            personList = Arrays.asList(personsItems);//формирует список на основе массива
            personList = new ArrayList<Person>(personList);//создается новый аррэй лист который заполняется personList
        } else
            return null;

        return (ArrayList<Person>) personList;
    }

    public void updatePerson(Context context, Person newPerson){
        ArrayList<Person> personList = getPersons(context);
        if (personList != null) {
            for (Person person: personList) {
                if(person.equals(newPerson)) person= newPerson;
            }
            savePerson(context, personList);
        }

    }
}
