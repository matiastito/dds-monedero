package dds.monedero.model;

import java.time.LocalDate;

public class Extraccion implements Movimiento {
  private LocalDate fecha;
  private double monto;

  public Extraccion(LocalDate fecha, double monto) {
    this.fecha = fecha;
    this.monto = monto;
  }

  @Override
  public double getMonto() {
    return monto;
  }

  @Override
  public LocalDate getFecha() {
    return fecha;
  }

  @Override
  public boolean fueDepositado(LocalDate fecha) {
    return false;
  }

  @Override
  public boolean fueExtraido(LocalDate fecha) {
    return esDeLaFecha(fecha);
  }

  @Override
  public boolean esDeLaFecha(LocalDate fecha) {
    return this.fecha.equals(fecha);
  }

  @Override
  public boolean isDeposito() {
    return false;
  }

  @Override
  public boolean isExtraccion() {
    return true;
  }

  @Override
  public void agregateA(Cuenta cuenta) {
    cuenta.setSaldo(calcularValor(cuenta));
    cuenta.agregarMovimiento(this);
  }

  @Override
  public double calcularValor(Cuenta cuenta) {
    return cuenta.getSaldo() - getMonto();
  }

}
