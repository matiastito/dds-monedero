package dds.monedero.model;

import java.time.LocalDate;

public interface Movimiento {
  double getMonto();

  LocalDate getFecha();

  boolean fueDepositado(LocalDate fecha);

  boolean fueExtraido(LocalDate fecha);

  boolean esDeLaFecha(LocalDate fecha);

  boolean isDeposito();

  boolean isExtraccion();

  void agregateA(Cuenta cuenta);

  double calcularValor(Cuenta cuenta);
}
