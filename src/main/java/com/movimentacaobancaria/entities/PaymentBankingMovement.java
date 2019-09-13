package com.movimentacaobancaria.entities;

import java.time.LocalDate;

public class PaymentBankingMovement extends BankingMovement {
    private String categoria;
    private LocalDate sortableDate;

    private PaymentBankingMovement(String data, String descricao, String valor, String categoria, String moeda, LocalDate sortableDate) {
        super(data, descricao, valor, moeda, sortableDate);
        this.categoria = categoria;
        this.sortableDate = sortableDate;
    }

    public String getCategoria() {
        return categoria;
    }

    public static class Builder {
        private static final String MOEDA_PT_BR = "R$";
        private String data;
        private String descricao;
        private String valor;
        private String categoria;
        private String moeda;
        private LocalDate sortableDate;

        public Builder(String data) {
            this.data = data;
        }

        public Builder withDescription(String description) {
            this.descricao = description;
            return this;
        }

        public Builder withMoeda() {
            this.moeda = MOEDA_PT_BR;
            return this;
        }

        public Builder withValue(String value) {
            this.valor = value;
            return this;
        }

        public Builder withCategoria(String categoria) {
            this.categoria = categoria;
            return this;
        }


        public Builder withSortableDate(LocalDate sortableDate) {
            this.sortableDate = sortableDate;
            return this;
        }

        public PaymentBankingMovement build() {
            return new PaymentBankingMovement(this.data, this.descricao, this.valor, this.categoria, this.moeda, this.sortableDate);
        }
    }
    @Override
    public String toString() {
        return  "{\"id\":\"" + this.getId() + "\"" +
                ",\"data\":\"" + this.getData() + "\""+
                ", \"descricao\":\"" + this.getDescricao()+ "\"" +
                ", \"moeda\":\"" + this.getMoeda()+ "\"" +
                ", \"valor\":\"" + this.getValor() + "\"" +
                ", \"categoria\":\"" + this.categoria + "\"" +
                "}";
    }
}
