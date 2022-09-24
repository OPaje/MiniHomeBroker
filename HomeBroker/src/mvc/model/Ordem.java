/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author jeanc
 */
public class Ordem {
    int id;
    int quantidade;
    int tipoOrdem;
    int estadoOrdem;
    double valor;
    double valorTotal;
    ContaCorrente conta;
    Ativo ticker;
    LocalDate dataCriacao;
    LocalDate dataModificacao;
}
