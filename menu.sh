#!/usr/bin/env bash

function _printAllOrdered(){
    java -jar movimentacao_bancaria-1.0-SNAPSHOT.jar 1
}

function _printAllGroupedByCategory(){
    java -jar movimentacao_bancaria-1.0-SNAPSHOT.jar 2
}

function _printMoreExpensiveCategory(){
    java -jar movimentacao_bancaria-1.0-SNAPSHOT.jar 3
}

function _printMoreExpensiveMonth(){
    java -jar movimentacao_bancaria-1.0-SNAPSHOT.jar 4
}

function _printTotalExpended(){
    java -jar movimentacao_bancaria-1.0-SNAPSHOT.jar 5
}



function _showOptions () {

    
    while true 
    do
        clear
        echo "|||||||||||||||||||||||||||||                        |||||||||||||||||||||||||||||||||"
        echo "                                  movimentacoes "
        echo "|||||||||||||||||||||||||||||                        |||||||||||||||||||||||||||||||||"
        echo ""
        echo "1 - Imprimir movimentacoes ordenadas por data"
        echo "2 - Imprimir movimentacoses agrupadas por categoria"
        echo "3 - Imprimir categoria com mais gastos"
        echo "4 - Imprimir mes com mais gastos"
        echo "5 - Imprimir total de gastos"
        echo "6 - Sair"
        echo ""
        echo "Digite a opção: "
        read option
        case ${option} in

            1) _printAllOrdered; exit 0 ;;
            2) _printAllGroupedByCategory; exit 0 ;;
            3) _printMoreExpensiveCategory; exit 0 ;;
            4) _printMoreExpensiveMonth; exit 0 ;;
            5) _printTotalExpended; exit 0 ;;
            6)  echo "Fechou"; exit 0;;
            *)  echo "Fechou"; exit 10;;
        esac
    done
}

# Executa o metodo principal
_showOptions
