#!/usr/bin/env bash

function _printAllGroupedByCategory(){
    java -jar movimentacao_bancaria-1.0-SNAPSHOT.jar 2
}

function _printAllOrdered(){
    java -jar movimentacao_bancaria-1.0-SNAPSHOT.jar 1
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
        echo "2 - Sair"
        echo ""
        echo "Digite a opção: "
        read option
        case ${option} in

            1) _printAllOrdered; exit 0 ;;
            2) _printAllGroupedByCategory; exit 0 ;;
            5)  echo "Fechou"; exit 0;;
            *)  echo "Fechou"; exit 10;;
        esac
    done
}

# Executa o metodo principal
_showOptions
