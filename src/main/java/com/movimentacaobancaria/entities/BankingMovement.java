package com.movimentacaobancaria.entities;

import java.time.LocalDate;

public class BankingMovement implements Comparable<BankingMovement> {
    private String data;
    private String descricao;
    private String moeda;
    private Double valor;
    private LocalDate sortableDate;
    //Needed when sending to api
    private Integer id;

    public String getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getMoeda() { return this.moeda; }

    public Double getValor() {
        return valor;
    }

    public LocalDate getSortableDate() {
        return sortableDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BankingMovement(String data, String descricao, String valor, String moeda, LocalDate sortableDate) {
        super();
        this.data = data;
        this.descricao = descricao;
        this.valor = Double.parseDouble(valor.replace(".","").replace(",", "."));
        this.moeda = moeda;
        this.sortableDate = sortableDate;
    }

    @Override
    public String toString() {
        return "{\"id\":\"" + this.getId() + "\"" +
                ",\"data\":\"" + this.getData() + "\""+
                ", \"descricao\":\"" + this.getDescricao()+ "\"" +
                ", \"moeda\":\"" + this.getMoeda()+ "\"" +
                ", \"valor\":\"" + this.getValor() + "\"" +
                "}";
    }

    @Override
    public int compareTo(BankingMovement other) {
        return this.getSortableDate().compareTo(other.getSortableDate());
    }

    public static class Builder {
        private static final String MOEDA_PT_BR = "R$";
        private String data;
        private String descricao;
        private String valor;
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

        public Builder withSortableDate(LocalDate sortableDate) {
            this.sortableDate = sortableDate;
            return this;
        }

        public BankingMovement build() {
            return new BankingMovement(this.data, this.descricao, this.valor, this.moeda, this.sortableDate);
        }
    }
}
