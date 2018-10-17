package edu.otus.hw15.entity.command;

/**
 * Created by Nik Bespalov on 15/10/2018.
 *
 * @author Nik Bespalov.
 */
public abstract class Command {
    protected CommandType type;

    public abstract <T> T get();
}
