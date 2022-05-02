package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.commands.CommandHandler;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CommandHandler commandHandler = context.getBean(CommandHandler.class);
        commandHandler.handleCommands();
    }
}
