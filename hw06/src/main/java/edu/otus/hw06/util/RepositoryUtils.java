package edu.otus.hw06.util;

import java.util.List;

/**
 * Created by Nik Bespalov on 22/07/2018.
 *
 * @author Nik Bespalov.
 */
public class RepositoryUtils {

    public static<T> T getOneResultFromList(List<T> list) {
        return list.isEmpty() || list.size() > 1 ? null : list.get(0);
    }
}
