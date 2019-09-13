package com.movimentacaobancaria.command;


public abstract class BankingMovementCommand {
    public abstract void execute() throws Exception;
}
